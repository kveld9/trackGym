# Contributing to TrackGym

Thanks for helping improve TrackGym. This guide covers the workflow and the checks every change must pass.

## Setup

Requirements and build commands are listed in [README.md](README.md#building--running) (JDK 21+, Android SDK `compileSdk 37`).

Enable the repository Git hooks once per clone:

```bash
git config core.hooksPath .githooks
```

`commit-msg` rejects messages that are not Conventional Commits, and `pre-push` runs `testDebugUnitTest` and `detekt`.

## Workflow

1. Open an issue first for features or behavior changes, so the approach can be agreed before you write code. Translations and small fixes can go straight to a pull request.
2. Create a branch from `main`.
3. Keep each commit to one logical change and write the message in [Conventional Commits](https://www.conventionalcommits.org/) format: `feat(scope): ...`, `fix(scope): ...`, `docs: ...`. Releases are versioned from these prefixes (`feat` bumps minor, `fix` and others bump patch, `feat!` or `BREAKING CHANGE` bumps major).
4. Open a pull request against `main` and fill in the template. Pull requests merge only after CI passes.

## Checks

Run these before opening a pull request. On pull requests, CI runs `testDebugUnitTest` and `detekt` and builds the R8-minified release APK (`./gradlew assembleRelease`) instead of the debug APK.

```bash
./gradlew testDebugUnitTest
./gradlew detekt
./gradlew assembleDebug
```

- `detekt` compares findings against `app/detekt-baseline.xml`. Fix new findings instead of adding them to the baseline.
- Logic in `domain/` or `data/` (calculators, engines, parsers, repositories) needs unit tests under `app/src/test/java/`.

## Code and UI rules

- Code, comments, commit messages and documentation are written in English.
- UI text lives in string resources (`stringResource(...)`); never hardcode strings in composables.
- UI work follows [DESIGN.md](DESIGN.md): 48 x 48 dp minimum touch targets, transitions of 200 ms or less, no emojis anywhere in the app.
- The app must keep working on `minSdk 24`. Database access and file I/O run off the main thread.
- No analytics, ad SDKs or network trackers.

## Translations

See [Localization & Contributing Translations](README.md#localization--contributing-translations). Adding `app/src/main/res/values-<locale>/` with translated `strings.xml` and `strings_exercises.xml` is enough; the build picks up the new locale automatically.

## Security issues

Do not open public issues for vulnerabilities. Follow [SECURITY.md](SECURITY.md).

## License

By contributing, you agree that your contributions are licensed under the [GNU General Public License v3.0](LICENSE).
