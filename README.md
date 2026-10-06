# TrackGym 🏋️‍♂️

> Native Android gym tracker app built with Kotlin and Jetpack Compose for logging workouts, exercises, weights, reps, and sets in Hevy style, featuring a "Before vs After" session comparison engine and automated Personal Record (PR) detection.

---

## ⚡ Key Features

- **Live Workout Tracking**:
  - Real-time session elapsed timer.
  - Interactive sets table: Set number, Previous performance indicator, Weight (kg), Reps, and Completion checkbox.
  - Support for multiple set types: *Normal*, *Warm-up*, *Drop Set*, and *Failure*.
  - Real-time previous target indicators per set for progressive overload.
- **Custom Exercise Creation**:
  - Pre-seeded base library with 25+ standard exercises categorized by muscle group (*Chest, Back, Legs, Shoulders, Arms, Core*).
  - Manual creation of custom exercises with customizable muscle group, equipment type (*Barbell, Dumbbell, Machine, Cable, Bodyweight*), and notes.
- **Before vs After Workout Comparison**:
  - Direct comparison between the current session and the previous session for the same exercise.
  - E.g., *Last Thursday you performed 2x8 @ 15.0 kg and this Thursday you performed 2x10 @ 15.0 kg (+2 reps) or 2x8 @ 18.0 kg (+3.0 kg)*.
  - Automatic calculation of deltas for weight, repetitions, and total training volume.
- **Automated Personal Record (PR) Detection**:
  - Real-time detection upon checking off each completed set.
  - Tracks milestones: **Max Weight**, **Reps Record at a given weight**, **Best Estimated 1RM (Epley Formula)**, and **Max Set Volume**.
  - Immediate visual badge/banner notification and dedicated PR showcase gallery.
- **Theming & Appearance**:
  - Full theme customization powered by Jetpack DataStore Preferences:
    - Theme Mode: System Default, Light Mode, Dark Mode.
    - Pure AMOLED Black toggle (`#000000` surface and background for OLED battery savings).
    - Dynamic Color (Material You / Monet) support on Android 12+ (API 31+).
- **Backup & Restore**:
  - Offline-first storage with Room Database (SQLite).
  - Storage Access Framework (SAF) JSON export/import with bounded stream parsing (10 MB limit).
  - Duplicate conflict resolution policy (*Skip*, *Overwrite*, or *Duplicate*).
- **Localization (i18n)**:
  - English as base/default language.
  - Full Spanish translation included out of the box.

---

## 🌐 Localization & Contributing Translations

TrackGym is built to be easily localizable by the community. All UI text is strictly modularized into Android string resources without hardcoded strings in Compose screens.

### How to contribute a new language

1. Locate the default string resource file:
   ```
   app/src/main/res/values/strings.xml
   ```
2. Create a new locale resource directory under `app/src/main/res/` named `values-<locale_code>/` (e.g., `values-fr/` for French, `values-pt/` for Portuguese, `values-de/` for German).
3. Copy `strings.xml` into the new folder:
   ```
   app/src/main/res/values-<locale_code>/strings.xml
   ```
4. Translate each string value keeping the XML `name` keys unchanged:
   ```xml
   <!-- Example: values-pt/strings.xml -->
   <string name="tab_train">Treinar</string>
   <string name="action_finish_workout">Terminar Treino</string>
   ```
5. Submit a Pull Request with your translation.

---

## 🛠️ Tech Stack

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

## 🚀 Building & Running

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
