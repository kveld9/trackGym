# DESIGN AND UX ENGINEERING SPECIFICATION — TRACKGYM

This document establishes the UI/UX architecture, interaction physics, and design token contracts for TrackGym. All contributors and automated agents must adhere to these standards.

---

## 1. CORE INTERACTION PHILOSOPHY: GYM-CENTRIC ERGONOMICS

A workout tracking application operates under demanding physical constraints:
- Users operate devices with sweaty hands, elevated heart rates, and muscle fatigue.
- Time between sets is scarce; cognitive friction or delayed animations directly degrade the workout experience.
- Ambient lighting in gyms varies drastically from dim corners to harsh overhead LEDs.

### 1.1 The "You Don't Need Animations" Principle (Emil Kowalski)
- Animations must never block user interaction or delay screen navigation.
- Maximum transition duration: **150ms – 200ms**.
- Motion must be purposeful: provide immediate visual confirmation of completed sets, unlocked records, or state transitions.
- Disable decorative loop animations that waste battery or GPU cycles during active sessions.

### 1.2 GPU Pipeline & Compositor Safety
In Jetpack Compose:
- **Zero Layout-Invalidation Animations**: Avoid animating layout dimensions (`width`, `height`, `padding`) during continuous gestures or rapid events.
- **Compositor Properties Only**: Animations must modify only `graphicsLayer` properties (`alpha`, `scaleX`, `scaleY`, `translationX`, `translationY`).
- Use `derivedStateOf` to prevent redundant recompositions when observing scroll state or active timers.

---

## 2. THE UI STACK (SCOTT HURFF'S 5 SCREEN STATES)

Every feature screen and composable container must explicitly model and handle all five states:

1. **Blank / Empty State**:
   - Shown on first launch or when no data exists (e.g., initial History, empty Records).
   - Must provide an immediate primary call-to-action (CTA) to start a session or create an item.
2. **Loading State**:
   - Sub-second skeleton loaders or subtle indicators for database queries, backups, or exports.
   - Must never shift layout (prevent Cumulative Layout Shift / CLS).
3. **Partial State**:
   - When a session has started but has limited data (e.g., 1 exercise added, 0 sets checked).
   - Must visually guide the user toward the next atomic step (e.g., "Add Set" or "Complete Set").
4. **Error State**:
   - Non-punitive, clear microcopy explaining the exact cause (e.g., invalid decimal input) with instant inline correction.
5. **Ideal State**:
   - Rich, data-dense view with complete metrics, comparison deltas, volume graphs, and badges.

---

## 3. ACCESSIBILITY (A11Y) & ERGONOMIC STANDARDS

### 3.1 Touch Targets
- All primary interactive elements (checkmarks, increment/decrement buttons, menu triggers) must maintain a minimum touch target of **48 × 48 dp**.
- Generous padding around table cells to prevent accidental tap overlaps during high-fatigue training.

### 3.2 Perceptual Contrast & Color Tokens
- True OLED Black (`#0A0A0A` / `#000000`) container background to conserve battery on AMOLED screens and maximize contrast.
- Visual tokens:
  - **GymNeonGreen (`#00E676`)**: Action completion, primary CTA, positive deltas.
  - **GymGold (`#FFD700`)**: Personal record milestones, trophy badges.
  - **GymRed (`#FF5252`)**: Discard actions, negative deltas.
  - **TextWhite (`#FFFFFF`)**: Primary metrics, weights, reps.
  - **TextMuted (`#8E8E93`)**: Secondary labels, units, timestamps.
- Meets WCAG AAA contrast ratio standards (> 7:1) for primary numeric data.

---

## 4. ANTI-SLOP & INFORMATION DENSITY

Following *Stop Slop* engineering principles:
- **Data Dominance**: The metric (weight and reps) must visually dominate the container. Auxiliary labels must be compact.
- **No Decorative Clutter**: Eliminate non-functional gradients, decorative glassmorphism that obscures text, and artificial borders.
- **Direct Microcopy**: Clear and concise labels ("Max Weight", "3 sets completed", "Discard session"). No verbose conversational filler.

---

## 5. PLANNED FEATURE SPECIFICATION (INSPECTION ROADMAP)

Derived from competitive fitness engineering benchmarks:

1. **Rest Timer (Automated Interval Countdown)**:
   - Floating counter triggered automatically when checking a set as complete.
   - Configurable default rest duration per exercise (e.g., 60s, 90s, 120s, 180s) with haptic feedback on completion.
2. **Specialized Set Types**:
   - `NORMAL`: Standard working sets (1, 2, 3...).
   - `WARMUP`: Progressive warmup sets (`W`) excluded from 1RM personal record calculations.
   - `DROP_SET`: Immediate weight reductions (`D`).
   - `FAILURE`: Maximum effort sets pushed to total muscular failure (`F`).
3. **Training Consistency & Calendar Heatmap**:
   - Historical workout streak counter.
   - Daily volume / duration stats aggregation.
4. **Workout Templates & Routine Folders**:
   - Save completed or active sessions as reusable routines (e.g., "Push A", "Pull B", "Legs").
   - Routine organization into custom folders.
