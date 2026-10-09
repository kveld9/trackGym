package com.kveld9.trackgym.ui.navigation

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.BoundsTransform
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import com.kveld9.trackgym.ui.theme.GymMotionTokens

/**
 * CompositionLocal providing [SharedTransitionScope] to screens within the main layout.
 */
@OptIn(ExperimentalSharedTransitionApi::class)
val LocalSharedTransitionScope = compositionLocalOf<SharedTransitionScope?> { null }

/**
 * CompositionLocal providing [AnimatedVisibilityScope] to screens within the active transition.
 */
val LocalNavAnimatedVisibilityScope = compositionLocalOf<AnimatedVisibilityScope?> { null }

/**
 * Builds a deterministic shared-element transition key linking a history workout card
 * to its corresponding comparison summary header.
 */
fun workoutSharedElementKey(workoutId: Long): String = "history-card-to-comparison-header-$workoutId"

/**
 * Shared bounds transform adhering to DESIGN.md motion constraints (<= 200ms).
 */
@OptIn(ExperimentalSharedTransitionApi::class)
val GymBoundsTransform: BoundsTransform = BoundsTransform { _, _ ->
    tween(
        durationMillis = GymMotionTokens.DurationDefaultMs,
        easing = GymMotionTokens.EmphasizedDecelerateEasing
    )
}

/**
 * Attaches sharedBounds to a Composable when both [LocalSharedTransitionScope]
 * and [LocalNavAnimatedVisibilityScope] are provided.
 */
@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun Modifier.workoutSharedBounds(
    key: String,
    sharedTransitionScope: SharedTransitionScope? = LocalSharedTransitionScope.current,
    animatedVisibilityScope: AnimatedVisibilityScope? = LocalNavAnimatedVisibilityScope.current
): Modifier {
    if (sharedTransitionScope == null || animatedVisibilityScope == null) {
        return this
    }
    with(sharedTransitionScope) {
        return this@workoutSharedBounds.sharedBounds(
            sharedContentState = rememberSharedContentState(key = key),
            animatedVisibilityScope = animatedVisibilityScope,
            enter = fadeIn(
                animationSpec = tween(
                    durationMillis = GymMotionTokens.DurationFastMs,
                    easing = GymMotionTokens.EmphasizedEasing
                )
            ),
            exit = fadeOut(
                animationSpec = tween(
                    durationMillis = GymMotionTokens.DurationFastMs,
                    easing = GymMotionTokens.EmphasizedAccelerateEasing
                )
            ),
            boundsTransform = GymBoundsTransform
        )
    }
}
