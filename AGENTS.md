# HARNESS ENGINEERING GUIDE — TRACKGYM (Android / Kotlin)

> Operational guide and governance system for AI agents operating on the **TrackGym** repository.

---

## 1. IDENTITY AND OBSERVED STACK

- **Product**: TrackGym — Hevy-style native Android gym tracker for exercises, weights, reps, sets, before-and-after session comparisons, and automated Personal Record (PR) detection.
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

---

## 2. DETERMINISTIC SENSORS & VERIFICATION

| Sensor | Linux Command | Scope / Purpose |
| :--- | :--- | :--- |
| **Unit Tests** | `./gradlew testDebugUnitTest` | Verifies pure domain calculations (1RM, PR detection, comparison deltas). Fast and deterministic. |
| **Assemble Debug** | `./gradlew assembleDebug` | Compiles debug APK, validates KSP, Compose compiler, and resource packaging. |
| **Git Status** | `git status` | Read-only check of working tree status and delta isolation. |

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
  - Base strings: `app/src/main/res/values/strings.xml` (English - default).
  - Supported translations: `app/src/main/res/values-es/strings.xml` (Spanish).
  - Open-source contributors can add new languages by submitting pull requests containing `app/src/main/res/values-<locale>/strings.xml` mirroring the keys defined in base English.

