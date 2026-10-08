# TrackGym

> Native Android gym tracker app built with Kotlin and Jetpack Compose for logging workouts, exercises, weights, reps, and sets, featuring a comprehensive "Before vs After" session comparison engine and automated Personal Record (PR) detection.

---

## Key Features

- **Live Workout Tracking**:
  - Real-time session elapsed timer with per-exercise custom rest durations.
  - Interactive sets table: Set number, Previous performance indicator, Weight, Reps, and Completion checkbox.
  - Set types: *Normal*, *Warm-up*, *Drop Set*, *Failure*, and *Myo-Reps*.
  - RPE / RIR selector per set.
  - Quick set duplication and swipe-to-dismiss sets with undo.
  - Dynamic exercise reordering and in-workout exercise swap.
  - Pinned exercise notes and live session notes.
  - Automatic warm-up set generation.
  - Optional keep-screen-on mode to keep the display awake during active training.
  - Configurable pre-set "Get Ready" countdown (3s, 5s, 10s, 15s) with audio warning beeps and haptic cues before timed, isometric, or heavy sets.
  - Sticky auto-hiding top bar with dynamic enter-always scrolling to maximize screen real estate during active sets.
  - Session context tags (*Fasted, Low Sleep, Joint Discomfort, Pre-Workout, High Stress, Competition, Caloric Deficit, Deload*) with multi-tag filtering in workout history.
- **Routines**:
  - Reusable workout routines organized in folders.
- **Custom Exercise Creation**:
  - Pre-seeded base library with 25+ standard exercises categorized by muscle group (*Chest, Back, Legs, Shoulders, Arms, Core*).
  - Biomechanical force vector (*Push, Pull, Static*) and technical difficulty level (*Beginner, Intermediate, Expert*) classification with routine Push/Pull balance analysis.
  - User-defined categories and custom tags management with multi-filtering, in-line tag assignment, and cascading renames/deletions.
  - Typo-tolerant fuzzy exercise search with Spanish diacritic normalization, acronym matching, and score ranking.
  - Manual creation of custom exercises with customizable muscle group, equipment type (*Barbell, Dumbbell, Machine, Cable, Bodyweight*), mechanics, force vector, difficulty level, custom tags, and notes.
  - Dedicated exercise history view with lifetime stats, 1RM progression timeline curve (`OneRepMaxProgressionEngine`), session volume, best set volume (`max(weight * reps)`), and isometric time metrics.
- **Before vs After Workout Comparison**:
  - Direct comparison between the current session and the previous session for the same exercise, contextually prioritizing the same routine template variant.
  - E.g., *Last Thursday you performed 2x8 @ 15.0 kg and this Thursday you performed 2x10 @ 15.0 kg (+2 reps) or 2x8 @ 18.0 kg (+3.0 kg)*.
  - Automatic calculation of deltas for weight, repetitions, and total training volume.
- **Training Analysis**:
  - Progressive overload recommendations based on the previous session.
  - Interactive monthly calendar navigation with selectable workout dates and direct day filtering.
  - Work-to-rest time ratio analysis (`1:X` distribution, work vs rest percentages, and progress visualization).
  - Real-time overtraining and junk volume warnings.
  - Weekly muscle split heatmap and continuous week streak engine (`WeekStreakEngine`) tracking active and all-time best consecutive training weeks.
  - Time-bucketed muscle and exercise stimulus distribution (`TimeBucketedDistributionEngine`) comparing sets, tonnage, and reps across overlapping windows (*Last Week, Last Month, Last Year, All Time*).
  - Direct weekly muscle set volume versus hypertrophy landmarks (`HypertrophyVolumeEngine`) comparing direct sets against evidence-based ranges (10–20 weekly sets).
  - Configurable volume metrics: dumbbell volume doubling and optional warm-up set exclusion.
  - Workout export as a structured prompt for LLM analysis.
- **Automated Personal Record (PR) Detection**:
  - Real-time detection upon checking off each completed set.
  - Tracks milestones: **Max Weight**, **Reps Record at a given weight**, **Best Estimated 1RM**, and **Max Set Volume**.
  - Selectable 1RM formula: *Epley*, *Brzycki*, *Wathan*, *Lander*, *Lombardi*, *Mayhew*, or *O'Conner*.
  - Immediate visual badge/banner notification and dedicated PR showcase gallery.
- **Plate Calculator**:
  - Barbell profiles, collar clips, and custom plate inventory.
- **Units**:
  - Weight in kilograms or pounds; distance in kilometers or miles.
- **Theming & Appearance**:
  - Full theme customization powered by Jetpack DataStore Preferences:
    - Theme Mode: System Default, Light Mode, Dark Mode.
    - Pure AMOLED Black toggle (`#000000` surface and background for OLED battery savings).
    - Dynamic Color (Material You / Monet) support on Android 12+ (API 31+).
    - Direct access from Android system app settings (`ACTION_APPLICATION_PREFERENCES`).
    - Android Privacy Dashboard transparency screen (`VIEW_PERMISSION_USAGE`).
    - Global uncaught exception handler with isolated crash recovery screen (`:error_process`).
- **Backup & Restore**:
  - Offline-first storage with Room Database (SQLite).
  - Storage Access Framework (SAF) JSON export/import with bounded stream parsing (10 MB limit).
  - Duplicate conflict resolution policy (*Skip*, *Overwrite*, or *Duplicate*).
  - CSV workout history export and import.
- **Localization (i18n)**:
  - English as base/default language.
  - Full Spanish translation included out of the box.
  - Configurable exercise naming: keep standard English names or translate to the system device language.

---

## Localization & Contributing Translations

TrackGym is built to be easily localizable by the community. All UI text is strictly modularized into Android string resources without hardcoded strings in Compose screens.

### How to contribute a new language

1. Locate the default string resource files:
   ```
   app/src/main/res/values/strings.xml
   app/src/main/res/values/strings_exercises.xml
   ```
2. Create a new locale resource directory under `app/src/main/res/` named `values-<locale_code>/` (e.g., `values-fr/` for French, `values-pt/` for Portuguese, `values-de/` for German).
3. Copy `strings.xml` and `strings_exercises.xml` into the new folder:
   ```
   app/src/main/res/values-<locale_code>/strings.xml
   app/src/main/res/values-<locale_code>/strings_exercises.xml
   ```
4. Translate each string value keeping the XML `name` keys unchanged:
   ```xml
   <!-- Example: values-pt/strings.xml -->
   <string name="tab_train">Treinar</string>
   <string name="action_finish_workout">Terminar Treino</string>
   ```
5. Submit a Pull Request with your translation.

---

## Tech Stack

| Component | Technology |
| :--- | :--- |
| **Language** | Kotlin 2.2.10 |
| **UI Framework** | Jetpack Compose (Material 3), Compose BOM 2026.06.00 |
| **Persistence** | Room Database 2.8.4 with KSP |
| **Preferences** | Jetpack DataStore Preferences 1.1.1 |
| **Serialization** | Kotlinx Serialization JSON 1.11.0 |
| **Concurrency** | Kotlin Coroutines & Flow (StateFlow, Dispatchers.IO) |
| **Architecture** | Clean Architecture / Reactive Unidirectional MVVM |
| **Testing** | JUnit 4, Kotlinx Coroutines Test |

---

## Building & Running

### Prerequisites
- Android SDK (`compileSdk 37`, `targetSdk 35`, `minSdk 24`)
- JDK 21+

### Gradle Commands

```bash
# Run domain unit tests
./gradlew testDebugUnitTest

# Assemble debug APK
./gradlew assembleDebug

# Install on connected device
./gradlew installDebug
```
