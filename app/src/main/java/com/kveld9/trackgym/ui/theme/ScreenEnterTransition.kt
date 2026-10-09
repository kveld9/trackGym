package com.kveld9.trackgym.ui.theme

import androidx.compose.animation.core.Animatable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import kotlinx.coroutines.launch

private const val SCREEN_ENTER_INITIAL_SCALE = 0.98f
private const val SCREEN_ENTER_INITIAL_ALPHA = 0.92f

/**
 * Screen-level enter transition operating strictly on graphicsLayer properties
 * (fade + scale) reusing GymMotionTokens specs (<= 200ms) without blocking input.
 */
@Composable
fun Modifier.screenEnterTransition(): Modifier {
    val alphaAnim = remember { Animatable(SCREEN_ENTER_INITIAL_ALPHA) }
    val scaleAnim = remember { Animatable(SCREEN_ENTER_INITIAL_SCALE) }

    LaunchedEffect(Unit) {
        launch {
            alphaAnim.animateTo(
                targetValue = 1f,
                animationSpec = GymMotionTokens.FadeEnterSpec
            )
        }
        launch {
            scaleAnim.animateTo(
                targetValue = 1f,
                animationSpec = GymMotionTokens.SpatialEnterSpec
            )
        }
    }

    return this.graphicsLayer {
        alpha = alphaAnim.value
        scaleX = scaleAnim.value
        scaleY = scaleAnim.value
    }
}
