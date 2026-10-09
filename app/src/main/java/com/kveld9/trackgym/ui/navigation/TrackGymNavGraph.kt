package com.kveld9.trackgym.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.kveld9.trackgym.domain.model.WeightUnit
import com.kveld9.trackgym.ui.screens.active.ActiveWorkoutScreen
import com.kveld9.trackgym.ui.screens.exercises.ExercisesScreen
import com.kveld9.trackgym.ui.screens.history.HistoryScreen
import com.kveld9.trackgym.ui.screens.records.RecordsScreen
import com.kveld9.trackgym.ui.screens.settings.SettingsScreen
import com.kveld9.trackgym.ui.theme.GymMotionTokens
import com.kveld9.trackgym.ui.viewmodel.GymViewModel
import com.kveld9.trackgym.ui.viewmodel.SettingsViewModel

private const val TRANSITION_SCALE = 0.96f

private val TrackGymEnterTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition = {
    fadeIn(
        animationSpec = tween(
            durationMillis = GymMotionTokens.DurationFastMs,
            easing = GymMotionTokens.EmphasizedEasing
        )
    ) + scaleIn(
        initialScale = TRANSITION_SCALE,
        animationSpec = tween(
            durationMillis = GymMotionTokens.DurationDefaultMs,
            easing = GymMotionTokens.EmphasizedDecelerateEasing
        )
    )
}

private val TrackGymExitTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition = {
    fadeOut(
        animationSpec = tween(
            durationMillis = GymMotionTokens.DurationFastMs,
            easing = GymMotionTokens.EmphasizedAccelerateEasing
        )
    ) + scaleOut(
        targetScale = TRANSITION_SCALE,
        animationSpec = tween(
            durationMillis = GymMotionTokens.DurationFastMs,
            easing = GymMotionTokens.EmphasizedAccelerateEasing
        )
    )
}

private val TrackGymPopEnterTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition = {
    fadeIn(
        animationSpec = tween(
            durationMillis = GymMotionTokens.DurationFastMs,
            easing = GymMotionTokens.EmphasizedEasing
        )
    ) + scaleIn(
        initialScale = TRANSITION_SCALE,
        animationSpec = tween(
            durationMillis = GymMotionTokens.DurationDefaultMs,
            easing = GymMotionTokens.EmphasizedDecelerateEasing
        )
    )
}

private val TrackGymPopExitTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition = {
    fadeOut(
        animationSpec = tween(
            durationMillis = GymMotionTokens.DurationFastMs,
            easing = GymMotionTokens.EmphasizedAccelerateEasing
        )
    ) + scaleOut(
        targetScale = TRANSITION_SCALE,
        animationSpec = tween(
            durationMillis = GymMotionTokens.DurationFastMs,
            easing = GymMotionTokens.EmphasizedAccelerateEasing
        )
    )
}

/**
 * Root navigation host managing top-level destinations with non-blocking,
 * graphicsLayer-only (fade + scale) transitions capped at 200ms per DESIGN.md.
 */
@Composable
fun TrackGymNavHost(
    navController: NavHostController,
    viewModel: GymViewModel,
    settingsViewModel: SettingsViewModel,
    weightUnit: WeightUnit,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = NavTab.TRAIN.name,
        modifier = modifier,
        enterTransition = TrackGymEnterTransition,
        exitTransition = TrackGymExitTransition,
        popEnterTransition = TrackGymPopEnterTransition,
        popExitTransition = TrackGymPopExitTransition
    ) {
        composable(NavTab.TRAIN.name) {
            ActiveWorkoutScreen(
                viewModel = viewModel,
                onWorkoutFinished = {}
            )
        }
        composable(NavTab.HISTORY.name) {
            HistoryScreen(
                viewModel = viewModel,
                weightUnit = weightUnit,
                onWorkoutClick = { workoutId ->
                    viewModel.viewWorkoutDetail(workoutId)
                }
            )
        }
        composable(NavTab.EXERCISES.name) {
            ExercisesScreen(
                viewModel = viewModel
            )
        }
        composable(NavTab.RECORDS.name) {
            RecordsScreen(
                viewModel = viewModel,
                weightUnit = weightUnit
            )
        }
        composable(NavTab.SETTINGS.name) {
            SettingsScreen(
                viewModel = settingsViewModel
            )
        }
    }
}
