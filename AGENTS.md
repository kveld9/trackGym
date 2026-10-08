# HARNESS ENGINEERING GUIDE — TRACKGYM (Android / Kotlin)

> Operational guide and governance system for AI agents operating on the **TrackGym** repository.

---

## 1. IDENTITY AND OBSERVED STACK

- **Product**: TrackGym — Native Android gym tracker for exercises, weights, reps, sets, before-and-after session comparisons, and automated Personal Record (PR) detection.
- **Language**: Kotlin 2.2.10 (JVM Target 11 / JVM 21+ compatible).
- **Platform / Runtime**: Android SDK (`minSdk 24`, `targetSdk 35`, `compileSdk 37`).
- **UI Framework**: Jetpack Compose (Material 3), Compose BOM `2026.06.00`.
- **Persistence**: Local SQLite via Room Database `2.8.4` with KSP (`GymDatabase`, `ExerciseDao`, `WorkoutDao`, `PersonalRecordDao`).
- **Concurrency**: Kotlin Coroutines & Flow (`StateFlow`, `Dispatchers.IO`).
- **Architecture**: Clean Architecture / Reactive MVVM (UDF):
  - `domain`: Pure business logic, calculators (`OneRepMaxCalculator.kt`, `PersonalRecordDetector.kt`, `WorkoutComparisonEngine.kt`).
  - `data`: Repositories and Room local persistence (`GymRepository.kt`, entities, DAOs).
  - `ui`: Jetpack Compose screens (`ActiveWorkoutScreen`, `WorkoutComparisonScreen`, `HistoryScreen`, `ExercisesScreen`, `RecordsScreen`), theme, and `GymViewModel.kt`.
- **Build System**: Gradle 9.5 (AGP `9.3.1`, Kotlin DSL: `build.gradle.kts`, `app/build.gradle.kts`, `gradle/libs.versions.toml`).
- **Performance**: AndroidX Baseline Profiles (`:baselineprofile`).

---

## 2. DETERMINISTIC SENSORS & VERIFICATION

| Sensor | Linux Command | Scope / Purpose |
| :--- | :--- | :--- |
| **Unit Tests** | `./gradlew testDebugUnitTest` | Verifies pure domain calculations (1RM, PR detection, comparison deltas). Fast and deterministic. |
| **Assemble Debug** | `./gradlew assembleDebug` | Compiles debug APK, validates KSP, Compose compiler, and resource packaging. |
| **Git Status** | `git status` | Read-only check of working tree status and delta isolation. |

### Quiet Gate Invocation (Agents)

Agents send the full Gradle log to a git-ignored file under `build/` and read only the verdict:

```bash
./gradlew testDebugUnitTest --console=plain > build/gate-unit.log 2>&1; rc=$?; grep -E 'FAILED|error:|BUILD (SUCCESSFUL|FAILED)' build/gate-unit.log | tail -40; echo "exit=$rc"
```

The verdict is the exit code plus the `BUILD` line, never the filtered text alone. On failure, grep or read the saved log for context instead of re-running the gate. The same pattern applies to `assembleDebug`.

---

## 3. LANGUAGE AND GOVERNANCE PROTOCOLS

- **Repository Native Language**: English strictly.
  - All source code, identifiers, comments, KDoc/docstrings, Git commits (Conventional Commits), PR titles, PR descriptions, issues, and documentation (`README.md`, technical docs) must be written exclusively in English.
  - The base/fallback Android string resources reside in `app/src/main/res/values/strings.xml` in English.
- **User Interaction Policy**:
  - The developer / user communicates in Spanish.
  - The agent / assistant MUST always respond to the user in Spanish during conversational turns.
- **Localization (i18n) & PR Contribution Architecture**:
  - The application uses modular Android string resources (`@StringRes` / `stringResource(...)`) for zero-hardcoding UI localization.
  - Base strings: `app/src/main/res/values/strings.xml` (English - default). The base locale is declared in `app/src/main/res/resources.properties` (`unqualifiedResLocale=en`).
  - Supported translations: `app/src/main/res/values-es/strings.xml` (Spanish).
  - Open-source contributors can add new languages by submitting pull requests containing `app/src/main/res/values-<locale>/strings.xml` mirroring the keys defined in base English.
  - The packaged locale set (`localeFilters`) and the per-app language `LocaleConfig` are derived in `app/build.gradle.kts` from those `values-<locale>/strings.xml` directories. Library translations for any other locale are stripped from the APK, so no build change is needed when adding a language.
- **Third-Party Brand & Intellectual Property Isolation**:
  - Never include references, names, trademarks, or comparisons to third-party commercial fitness/gym applications in documentation, source code, comments, KDoc, commit messages, PRs, or repository metadata.

---

## 4. CI/CD & AUTOMATED RELEASES PROTOCOL

- **Single-Pass Pipeline**: `.github/workflows/ci.yml` runs on `ubuntu-latest`.
- **Pull Requests**: Runs fast unit tests (`./gradlew testDebugUnitTest`).
- **Main Branch Push**:
  1. Computes Semantic Versioning from Conventional Commits (`feat!:`, `BREAKING CHANGE` -> Major, `feat:` -> Minor, `fix:`/other -> Patch).
  2. Bumps and passes `versionName` and `versionCode` via Gradle `-P` properties (`-PversionName=... -PversionCode=...`).
  3. Single Gradle execution builds release & debug APKs.
  4. Packages APKs into `trackGym-${VERSION}-release.apk` and `trackGym-${VERSION}-debug.apk` with multi-hash integrity file (`checksums-v${VERSION}.txt`) and `SHA256SUMS.txt`.
  5. Automatically creates Git tag `v${VERSION}` and publishes a GitHub Release with changelog and downloads.
- **Keystore Configuration**:
  - Release builds sign with official keystore if GitHub secrets `KEYSTORE_BASE64`, `KEYSTORE_PASSWORD`, `KEY_ALIAS`, `KEY_PASSWORD` are set.
  - Fallback: Uses debug signing config automatically if no custom keystore is configured, guaranteeing successful release APK generation.

---

## 5. UI/UX DESIGN & ERGONOMICS PROTOCOL

- **Authoritative Specification**: All interface work must adhere to [`DESIGN.md`](./DESIGN.md).
- **Core Constraints**:
  - **Gym-Centric Physics**: 48 × 48 dp minimum touch targets on all interactive elements.
  - **No Blocking Animations**: Transitions strictly capped at 150ms–200ms. Never delay workout logging.
  - **Compositor Efficiency**: Animate only `graphicsLayer` properties (`alpha`, `scale`, `translation`). Never trigger layout passes in animations.
  - **The UI Stack**: Every screen must explicitly model Blank/Empty, Loading, Partial, Error, and Ideal states.
  - **Anti-Slop**: Information density over decoration. High perceptual contrast (OLED black + high-luminance neon green/gold tokens).
  - **Zero-Emoji Policy**: Emojis are strictly prohibited throughout the application (source code, Jetpack Compose layouts, badges, headers, labels, and Android string resources). Use formal typography, vector iconography (`androidx.compose.material.icons`), or dedicated design tokens instead.
  - **Release Optimization**: Always maintain `isMinifyEnabled = true` and `isShrinkResources = true` with R8 in release builds to enforce sub-5MB APK sizes.

---

## 6. MANDATORY UNIT TEST POLICY

- **Universal Test Coverage Requirement**:
  - Every calculation, engine, algorithm, parser, converter, and business logic component residing in `domain/` or `data/` MUST have dedicated unit tests in `app/src/test/java/`.
  - When introducing or modifying any engine, calculator, business rule, format parser, or state transformation:
    1. The agent MUST create or update the corresponding unit test suite in the exact same atomic commit.
    2. Tests must be fast, deterministic, offline, and executable via `./gradlew testDebugUnitTest`.
    3. No feature containing business logic may be considered complete or committed without its corresponding unit test suite passing.

---

## 7. LOW-END DEVICE OPTIMIZATION & ANTI-OBSOLESCENCE POLICY

To actively combat planned obsolescence and ensure universal accessibility for users training on budget or legacy devices:
- **Strict Backward Compatibility**: Maintain `minSdk 24` (Android 7.0 Nougat) baseline without regressions.
- **Memory Footprint & Allocation Discipline**:
  - Runtime heap memory must remain strictly contained ($\le 50\,\text{MB}$).
  - Never allocate short-lived objects, heavy collections, or formatters inside hot Compose recomposition loops.
  - Image/bitmap captures (e.g. session summary sharing) must be generated strictly on demand using hardware acceleration and promptly recycled/dereferenced.
- **CPU & Battery Preservation**:
  - 0 unnecessary background daemons, 0 WakeLocks, and 0 redundant alarms.
  - UI state collection must use `collectAsStateWithLifecycle()` to pause rendering pipelines when the screen is locked or backgrounded during a gym rest interval.
  - List items must declare stable keys (`key(exercise.id)`) to minimize recomposition churn on budget processors.
- **Storage & Disk Overhead**:
  - Maintain release APK size $\le 5\,\text{MB}$ using R8 full mode shrinking and resource optimization.
  - All Room queries, file exports, and JSON parsing operations must execute exclusively off the main thread via `Dispatchers.IO`.
  - Zero third-party telemetry, commercial ad SDKs, or background trackers.
