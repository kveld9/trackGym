# PRODUCT SPECIFICATION — TRACKGYM

> Authoritative product vision, ergonomic context, and engineering constraints for TrackGym.
> This document operates alongside `DESIGN.md` (authoritative UI/UX specification) and `AGENTS.md` (governance & engineering guide).

---

## 1. PRODUCT OVERVIEW & MISSION

TrackGym is an offline-first, native Android strength training tracker built with Jetpack Compose and Room SQLite. It is engineered specifically for serious trainees and strength athletes who need frictionless set logging, instantaneous before-and-after workout comparisons, and automated Personal Record (PR) detection without corporate bloat, subscriptions, cloud accounts, or telemetry.

---

## 2. TARGET AUDIENCE & PHYSICAL OPERATING ENVIRONMENT

The application is operated under demanding physiological and environmental constraints:

- **High Physical Fatigue & Tremors**: Athletes log sets between heavy working intervals with elevated heart rates, acute muscle fatigue, and shaking hands. Cognitive overhead must be virtually zero.
- **Sweaty Hands & Chalk Dust**: Screen taps are imprecise. Wet fingers and gym chalk degrade capacitive touch precision. Tiny buttons cause missed taps and training frustration.
- **Scarce Rest Time**: Trainees typically have 60 to 180 seconds between sets. Any delayed animation, modal obstacle, or sluggish input directly sabotages the workout flow.
- **Extreme Ambient Lighting**: Training facilities range from dimly lit powerlifting basements to commercial gyms with glaring overhead LED arrays. High perceptual contrast is mandatory for outdoor and indoor readability.

---

## 3. CORE PRODUCT GOALS

1. **Sub-3-Second Set Logging**: Input weights, reps, and mark sets complete with minimal interaction steps.
2. **Deterministic Session Comparison**: Automatically compare current exercise performance against the preceding session (deltas in weight, reps, and cumulative volume).
3. **Instantaneous PR Detection**: Dynamically detect all-time personal records (Max Weight, 1RM, Max Volume, AMRAP, Max Reps) as sets are checked.
4. **Zero-Telemetry Privacy**: 100% of user telemetry, biometric data, workouts, and notes remain strictly local on the device in Room SQLite.

---

## 4. OPERATING CONTEXT & RUNTIME ARCHITECTURE

- **Operating System & Baseline**: Android SDK (`minSdk 24` / Nougat 7.0 baseline up to `targetSdk 35` / `compileSdk 37`).
- **UI Toolkit**: Modern Jetpack Compose (Material 3) utilizing Compose BOM.
- **Local Persistence**: Offline-first Room Database (`2.8.5`) compiled with KSP (`GymDatabase`, `ExerciseDao`, `WorkoutDao`, `PersonalRecordDao`).
- **Threading Model**: Coroutines and Reactive Flow (`StateFlow`, `Dispatchers.IO` exclusively for persistence and JSON/CSV I/O). Main thread is reserved strictly for 60/120 FPS frame presentation.
- **Display Physics**: Optimized for OLED/AMOLED true black displays (`#000000` / `#0A0A0A`) to maximize power efficiency and provide infinite contrast against luminous accent tokens.
- **Anti-Obsolescence Hardware Support**: Engineered to perform smoothly on low-end budget processors with constrained RAM ($\le 50\,\text{MB}$ runtime heap footprint).

---

## 5. ERGONOMIC & DESIGN CONSTRAINTS (DESIGN.md & AGENTS.md)

All user interface additions, refactorings, and design tokens must strictly adhere to the following invariants:

1. **48 × 48 dp Minimum Touch Targets**:
   - Every interactive component (checkmarks, increment/decrement pills, icon buttons, calendar days, filter chips, dropdowns) must guarantee at least a 48 × 48 dp interactive touch target (`minimumInteractiveComponentSize()`).
2. **150ms – 200ms Motion Cap (Emil Kowalski Principle)**:
   - Screen and component transitions are strictly capped at 150ms–200ms (`GymMotionTokens`).
   - Zero blocking transitions; interactions must never wait for an animation to complete.
3. **Compositor Efficiency (Zero Layout Invalidation)**:
   - Animations must modify only `graphicsLayer` properties (`alpha`, `scaleX`, `scaleY`, `translationX`, `translationY`).
   - Animating layout dimensions (`width`, `height`, `padding`) during continuous gestures or recomposition loops is strictly prohibited.
4. **The 5-State UI Stack (Scott Hurff Framework)**:
   - Every screen and composable container must explicitly model:
     - **Blank / Empty**: Immediate primary call-to-action (CTA) to create/start.
     - **Loading**: Sub-second skeleton loaders; zero layout shift (CLS).
     - **Partial**: Guides user to next atomic step when limited data exists.
     - **Error**: Non-punitive, inline correction with clear technical explanation.
     - **Ideal**: Data-dense view with complete metrics and badges.
5. **Zero-Emoji Architecture**:
   - Emojis are strictly banned across layouts, strings, badges, headers, and code. Visual anchors use official typography, vector symbols (`Icons.Default.*`), or custom theme tokens.
6. **Localization (i18n) Architecture**:
   - Zero hardcoded text strings in UI code.
   - Base language: English (`app/src/main/res/values/strings.xml`).
   - Translations: Spanish (`app/src/main/res/values-es/strings.xml`).
7. **Release Footprint & Resource Discipline**:
   - Release APK size capped at $\le 5\,\text{MB}$ using full R8 minification and resource shrinking.
   - Zero bundled heavy font families or uncompressed static media.
