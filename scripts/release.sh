#!/usr/bin/env bash
# ==============================================================================
# TrackGym Local Release Automation Script
#
# Replicates the release pipeline of .github/workflows/ci.yml locally.
# Computes semantic version and changelog from Git history, validates
# pre-conditions, builds release and debug APKs in a single Gradle pass,
# stages artifacts with multi-hash checksums, tags the commit, and publishes
# the release to GitHub via the GitHub CLI ('gh').
#
# Signing Configuration:
# Reuses the repository's existing resolution in app/build.gradle.kts:
# official release signing requires the keystore to be configured per AGENTS.md §4
# (via KEYSTORE_PATH, KEYSTORE_PASSWORD, KEY_ALIAS, KEY_PASSWORD in environment
# variables, Gradle properties, or local.properties). If not configured, Gradle
# automatically falls back to debug signing configuration so the APK still builds.
# Secrets are never embedded, generated, printed, or documented by this script.
#
# Build Provenance:
# Note: Provenance attestation (actions/attest-build-provenance) is omitted here
# because attest-build-provenance only exists on GitHub runners with GitHub Actions
# OIDC infrastructure. Multi-hash checksums provide local artifact integrity.
# ==============================================================================

set -euo pipefail

DRY_RUN=false

print_usage() {
  cat <<'EOF'
Usage: ./scripts/release.sh [OPTIONS]

Compile, stage, tag, and publish a new GitHub Release for TrackGym.

Options:
  --dry-run       Run build, staging, and validation, but skip Git tag creation,
                  Git tag push, and GitHub release publication. Prints each
                  skipped command.
  -h, --help      Display this help message and exit.

Requirements:
  - Must be run from repository root.
  - Current Git branch must be 'main'.
  - Working tree must be clean (no uncommitted or untracked changes).
  - GitHub CLI ('gh') must be installed and authenticated ('gh auth status').
EOF
}

# ------------------------------------------------------------------------------
# 1. Parse Command-Line Options
# ------------------------------------------------------------------------------
while [ $# -gt 0 ]; do
  case "$1" in
    --dry-run)
      DRY_RUN=true
      shift
      ;;
    -h|--help)
      print_usage
      exit 0
      ;;
    *)
      echo "Error: Unknown option '$1'." >&2
      echo "Run '$0 --help' for usage." >&2
      exit 1
      ;;
  esac
done

# ------------------------------------------------------------------------------
# 2. Environment & Repository Guards
# ------------------------------------------------------------------------------
REPO_ROOT="$(git rev-parse --show-toplevel 2>/dev/null || true)"
if [ -z "$REPO_ROOT" ] || [ "$REPO_ROOT" != "$PWD" ]; then
  echo "Error: This script must be run from the repository root directory." >&2
  exit 1
fi

CURRENT_BRANCH="$(git branch --show-current 2>/dev/null || git rev-parse --abbrev-ref HEAD 2>/dev/null || true)"
if [ "$CURRENT_BRANCH" != "main" ]; then
  echo "Error: Current branch is '${CURRENT_BRANCH}'. Releases must be published from 'main'." >&2
  exit 1
fi

if [ -n "$(git status --porcelain)" ]; then
  echo "Error: Working tree is dirty. Stash, commit, or discard uncommitted changes before releasing." >&2
  exit 1
fi

if ! command -v gh >/dev/null 2>&1; then
  echo "Error: GitHub CLI ('gh') is not installed. Please install 'gh' to continue." >&2
  exit 1
fi

if ! gh auth status >/dev/null 2>&1; then
  echo "Error: GitHub CLI ('gh') is not authenticated. Please run 'gh auth login' to authenticate." >&2
  exit 1
fi

# ------------------------------------------------------------------------------
# 3. Compute Semantic Version & Changelog (Source of Truth: ci.yml)
# ------------------------------------------------------------------------------
LATEST_TAG="$(git tag --list 'v*' --sort=-v:refname | head -n 1 || true)"
if [ -z "$LATEST_TAG" ]; then
  MAJOR=1
  MINOR=0
  PATCH=0
  VERSION="1.0.0"
  PREV_TAG=""
else
  PREV_TAG="$LATEST_TAG"
  BASE="${LATEST_TAG#v}"
  MAJOR="$(printf '%s' "$BASE" | cut -d. -f1)"
  MINOR="$(printf '%s' "$BASE" | cut -d. -f2)"
  PATCH="$(printf '%s' "$BASE" | cut -d. -f3)"
  RANGE="${LATEST_TAG}..HEAD"
  COMMITS_COUNT="$(git rev-list --count "$RANGE" || true)"
  if [ "$COMMITS_COUNT" -eq 0 ]; then
    echo "No new commits since $LATEST_TAG. Nothing to release."
    exit 0
  fi

  LOG="$(git log "$RANGE" --pretty=format:'%s%n%b%n' || true)"
  if printf '%s' "$LOG" | grep -qiE 'feat(\([^)]+\))?!:|BREAKING[ -]CHANGE'; then
    MAJOR=$((MAJOR+1)); MINOR=0; PATCH=0
  elif printf '%s' "$LOG" | grep -qiE 'feat(\([^)]+\))?:'; then
    MINOR=$((MINOR+1)); PATCH=0
  else
    PATCH=$((PATCH+1))
  fi
  VERSION="${MAJOR}.${MINOR}.${PATCH}"
fi
VERSION_CODE=$((MAJOR * 10000 + MINOR * 100 + PATCH))

if [ -z "$PREV_TAG" ]; then
  BODY="$(git log -n 50 --pretty=format:'- %s (%h)')"
else
  BODY="$(git log "${PREV_TAG}..HEAD" --pretty=format:'- %s (%h)')"
fi

TAG="v${VERSION}"

# Check for existing local or remote tag
if git rev-parse -q --verify "refs/tags/${TAG}" >/dev/null 2>&1; then
  echo "Error: Local tag '${TAG}' already exists." >&2
  exit 1
fi

if git remote get-url origin >/dev/null 2>&1; then
  REMOTE_TAG_CHECK="$(git ls-remote --tags origin "refs/tags/${TAG}" 2>/dev/null || true)"
  if [ -n "$REMOTE_TAG_CHECK" ]; then
    echo "Error: Remote tag '${TAG}' already exists on origin." >&2
    exit 1
  fi
fi

echo "=================================================="
echo " Preparing TrackGym Release ${TAG}"
echo "=================================================="
echo "Version:      ${VERSION}"
echo "Version Code: ${VERSION_CODE}"
echo "Previous Tag: ${PREV_TAG:-none}"
if [ "$DRY_RUN" = true ]; then
  echo "Mode:         DRY RUN (no tags pushed, no GitHub release published)"
fi
echo "=================================================="
echo ""

# ------------------------------------------------------------------------------
# 4. Single-Pass Build & Tests
# ------------------------------------------------------------------------------
echo "Running verification gates and assembling APKs..."
./gradlew testDebugUnitTest detekt assembleRelease assembleDebug \
  -PversionName="${VERSION}" \
  -PversionCode="${VERSION_CODE}"

# ------------------------------------------------------------------------------
# 5. Stage Artifacts & Compute Checksums
# ------------------------------------------------------------------------------
STAGE="build/release-${VERSION}"
rm -rf "${STAGE}"
mkdir -p "${STAGE}"

echo "Staging release artifacts into ${STAGE}/..."

# Copy architecture-specific & universal release APKs
for apk in app/build/outputs/apk/release/app-*-release.apk app/build/outputs/apk/release/app-release.apk; do
  if [ -f "$apk" ]; then
    base=$(basename "$apk")
    if [ "$base" = "app-release.apk" ]; then
      target="trackGym-${VERSION}-release.apk"
    else
      arch="${base#app-}"
      target="trackGym-${VERSION}-${arch}"
    fi
    cp "$apk" "${STAGE}/${target}"
  fi
done

# Copy architecture-specific & universal debug APKs
for apk in app/build/outputs/apk/debug/app-*-debug.apk app/build/outputs/apk/debug/app-debug.apk; do
  if [ -f "$apk" ]; then
    base=$(basename "$apk")
    if [ "$base" = "app-debug.apk" ]; then
      target="trackGym-${VERSION}-debug.apk"
    else
      arch="${base#app-}"
      target="trackGym-${VERSION}-${arch}"
    fi
    cp "$apk" "${STAGE}/${target}"
  fi
done

# Validate required APKs were generated (ci.yml fail_on_unmatched_files equivalent)
if [ ! -s "${STAGE}/trackGym-${VERSION}-release.apk" ]; then
  echo "Error: Release APK '${STAGE}/trackGym-${VERSION}-release.apk' is missing or empty." >&2
  exit 1
fi

if [ ! -s "${STAGE}/trackGym-${VERSION}-debug.apk" ]; then
  echo "Error: Debug APK '${STAGE}/trackGym-${VERSION}-debug.apk' is missing or empty." >&2
  exit 1
fi

# Generate integrity checksums (SHA-256, SHA-512, MD5) and SHA256SUMS.txt
(
  cd "${STAGE}"
  CHECKSUM_FILE="checksums-v${VERSION}.txt"
  echo "==================================================" > "$CHECKSUM_FILE"
  echo " TrackGym v${VERSION} — Integrity Hashes" >> "$CHECKSUM_FILE"
  echo "==================================================" >> "$CHECKSUM_FILE"
  echo "" >> "$CHECKSUM_FILE"

  echo "## SHA-256" >> "$CHECKSUM_FILE"
  sha256sum trackGym-*.apk >> "$CHECKSUM_FILE"
  echo "" >> "$CHECKSUM_FILE"

  echo "## SHA-512" >> "$CHECKSUM_FILE"
  sha512sum trackGym-*.apk >> "$CHECKSUM_FILE"
  echo "" >> "$CHECKSUM_FILE"

  echo "## MD5" >> "$CHECKSUM_FILE"
  md5sum trackGym-*.apk >> "$CHECKSUM_FILE"

  sha256sum trackGym-*.apk > SHA256SUMS.txt
)

# Verify checksum files exist and are non-empty
if [ ! -s "${STAGE}/checksums-v${VERSION}.txt" ] || [ ! -s "${STAGE}/SHA256SUMS.txt" ]; then
  echo "Error: Checksum files were not generated successfully in '${STAGE}'." >&2
  exit 1
fi

# Collect all staged artifacts and verify completeness
STAGED_FILES=()
for f in "${STAGE}"/*; do
  if [ ! -s "$f" ]; then
    echo "Error: Staged artifact '$f' is missing or empty." >&2
    exit 1
  fi
  STAGED_FILES+=("$f")
done

if [ ${#STAGED_FILES[@]} -eq 0 ]; then
  echo "Error: No staged artifacts found in '${STAGE}'." >&2
  exit 1
fi

# Format Release Notes matching ci.yml layout
RELEASE_NOTES="$(cat <<EOF
## Changes

${BODY}

## Downloads

| File | Description |
|------|-------------|
| \`trackGym-${VERSION}-release.apk\` | Production Release Build |
| \`trackGym-${VERSION}-debug.apk\` | Debug Build |
| \`checksums-v${VERSION}.txt\` | Multi-hash Integrity Verification (SHA-256, SHA-512, MD5) |
| \`SHA256SUMS.txt\` | Standard SHA-256 Checksum (\`sha256sum -c\`) |
EOF
)"

# ------------------------------------------------------------------------------
# 6. Summary
# ------------------------------------------------------------------------------
echo ""
echo "=================================================="
echo " Staged Release Artifacts: ${TAG}"
echo "=================================================="
echo "Artifacts directory: ${STAGE}/"
for f in "${STAGED_FILES[@]}"; do
  echo "  - $(basename "$f")"
done
echo "=================================================="
echo ""

# ------------------------------------------------------------------------------
# 7. Publish or Dry-Run
# ------------------------------------------------------------------------------
if [ "$DRY_RUN" = true ]; then
  echo ""
  echo "[DRY RUN] Tag creation skipped: git tag -a \"${TAG}\" -m \"${TAG}\""
  echo "[DRY RUN] Tag push skipped: git push origin \"${TAG}\""
  echo "[DRY RUN] GitHub release creation skipped: gh release create \"${TAG}\" --title \"${TAG}\" --notes \"...\" --target main -- ${STAGED_FILES[*]}"
  echo ""
  echo "[DRY RUN] Dry run completed successfully. All artifacts staged in ${STAGE}/."
  exit 0
fi

echo "Creating annotated tag '${TAG}'..."
git tag -a "${TAG}" -m "${TAG}"

echo "Pushing tag '${TAG}' to origin..."
git push origin "${TAG}"

echo "Publishing release '${TAG}' to GitHub..."
gh release create "${TAG}" \
  --title "${TAG}" \
  --notes "${RELEASE_NOTES}" \
  --target main \
  -- "${STAGED_FILES[@]}"

echo "Release ${TAG} successfully created and published to GitHub!"
