package com.kveld9.trackgym.ui.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteItemColors
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.kveld9.trackgym.domain.model.WorkoutComparison
import com.kveld9.trackgym.ui.screens.comparison.WorkoutComparisonScreen
import com.kveld9.trackgym.ui.util.LocalKeepEnglishExerciseNames
import com.kveld9.trackgym.ui.viewmodel.GymViewModel
import com.kveld9.trackgym.ui.viewmodel.SettingsViewModel

private val ExpandedWidthThreshold = 1200.dp
private val MediumWidthThreshold = 600.dp

@Composable
fun MainScreen(
    viewModel: GymViewModel,
    settingsViewModel: SettingsViewModel,
    initialTab: Int = 0,
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val currentTab = NavTab.entries.firstOrNull { it.name == currentRoute }
        ?: NavTab.entries.getOrElse(initialTab) { NavTab.TRAIN }

    LaunchedEffect(initialTab) {
        val targetTab = NavTab.entries.getOrNull(initialTab) ?: NavTab.TRAIN
        if (currentTab != targetTab) {
            navController.navigateToTab(targetTab)
        }
    }

    val lastFinishedComparison by viewModel.lastFinishedComparison.collectAsStateWithLifecycle()
    val selectedDetailComparison by viewModel.selectedDetailComparison.collectAsStateWithLifecycle()
    val weightUnit by viewModel.weightUnit.collectAsStateWithLifecycle()

    LaunchedEffect(currentTab) {
        if (currentTab == NavTab.TRAIN) {
            viewModel.refreshActiveWorkout()
        }
    }

    val keepExerciseNamesInEnglish by viewModel.keepExerciseNamesInEnglish.collectAsStateWithLifecycle()

    CompositionLocalProvider(LocalKeepEnglishExerciseNames provides keepExerciseNamesInEnglish) {
        val activeComparison = selectedDetailComparison ?: lastFinishedComparison
        if (activeComparison != null) {
            val handleDismissComparison = {
                dismissComparison(selectedDetailComparison, viewModel)
            }

            BackHandler(onBack = handleDismissComparison)

            WorkoutComparisonScreen(
                comparison = activeComparison,
                weightUnit = weightUnit,
                onBackClick = handleDismissComparison
            )
            return@CompositionLocalProvider
        }

        BoxWithConstraints(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            val layoutType = resolveLayoutType(maxWidth)
            val navItemColors = rememberNavItemColors()

            NavigationSuiteScaffold(
                layoutType = layoutType,
                navigationSuiteItems = {
                    NavTab.entries.forEach { tab ->
                        val isSelected = currentTab == tab
                        item(
                            selected = isSelected,
                            onClick = {
                                handleTabClick(currentTab, tab, navController, viewModel)
                            },
                            icon = {
                                Icon(
                                    imageVector = tab.icon,
                                    contentDescription = stringResource(tab.titleRes)
                                )
                            },
                            label = {
                                Text(
                                    text = stringResource(tab.titleRes),
                                    fontSize = 10.sp,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                                )
                            },
                            colors = navItemColors,
                            modifier = Modifier.defaultMinSize(minWidth = 48.dp, minHeight = 48.dp)
                        )
                    }
                },
                containerColor = MaterialTheme.colorScheme.background,
                contentColor = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.fillMaxSize()
            ) {
                TrackGymNavHost(
                    navController = navController,
                    viewModel = viewModel,
                    settingsViewModel = settingsViewModel,
                    weightUnit = weightUnit,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

private fun resolveLayoutType(maxWidth: Dp): NavigationSuiteType = when {
    maxWidth >= ExpandedWidthThreshold -> NavigationSuiteType.NavigationDrawer
    maxWidth >= MediumWidthThreshold -> NavigationSuiteType.NavigationRail
    else -> NavigationSuiteType.NavigationBar
}

private fun NavHostController.navigateToTab(tab: NavTab) {
    navigate(tab.name) {
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

private fun handleTabClick(
    currentTab: NavTab,
    clickedTab: NavTab,
    navController: NavHostController,
    viewModel: GymViewModel
) {
    if (currentTab != clickedTab) {
        navController.navigateToTab(clickedTab)
    } else if (clickedTab == NavTab.TRAIN) {
        viewModel.refreshActiveWorkout()
    }
}

private fun dismissComparison(
    selectedDetailComparison: WorkoutComparison?,
    viewModel: GymViewModel
) {
    if (selectedDetailComparison != null) {
        viewModel.clearSelectedDetailComparison()
    } else {
        viewModel.clearLastFinishedComparison()
    }
}

@Composable
private fun rememberNavItemColors(): NavigationSuiteItemColors = NavigationSuiteDefaults.itemColors(
    navigationBarItemColors = NavigationBarItemDefaults.colors(
        selectedIconColor = MaterialTheme.colorScheme.primary,
        selectedTextColor = MaterialTheme.colorScheme.primary,
        unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
        unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
        indicatorColor = MaterialTheme.colorScheme.surfaceContainerHighest
    ),
    navigationRailItemColors = NavigationRailItemDefaults.colors(
        selectedIconColor = MaterialTheme.colorScheme.primary,
        selectedTextColor = MaterialTheme.colorScheme.primary,
        unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
        unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
        indicatorColor = MaterialTheme.colorScheme.surfaceContainerHighest
    ),
    navigationDrawerItemColors = NavigationDrawerItemDefaults.colors(
        selectedIconColor = MaterialTheme.colorScheme.primary,
        selectedTextColor = MaterialTheme.colorScheme.primary,
        unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
        unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
        selectedContainerColor = MaterialTheme.colorScheme.surfaceContainerHighest
    )
)
