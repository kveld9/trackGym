package com.kveld9.trackgym.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.kveld9.trackgym.R
import com.kveld9.trackgym.ui.screens.active.ActiveWorkoutScreen
import com.kveld9.trackgym.ui.screens.comparison.WorkoutComparisonScreen
import com.kveld9.trackgym.ui.screens.exercises.ExercisesScreen
import com.kveld9.trackgym.ui.screens.history.HistoryScreen
import com.kveld9.trackgym.ui.screens.records.RecordsScreen
import com.kveld9.trackgym.ui.screens.settings.SettingsScreen
import com.kveld9.trackgym.ui.viewmodel.GymViewModel
import com.kveld9.trackgym.ui.viewmodel.SettingsViewModel

enum class NavTab(@get:StringRes val titleRes: Int, val icon: ImageVector) {
    TRAIN(R.string.tab_train, Icons.Default.FitnessCenter),
    HISTORY(R.string.tab_history, Icons.Default.History),
    EXERCISES(R.string.tab_exercises, Icons.AutoMirrored.Filled.List),
    RECORDS(R.string.tab_records, Icons.Default.EmojiEvents),
    SETTINGS(R.string.tab_settings, Icons.Default.Settings)
}

@Composable
fun MainScreen(
    viewModel: GymViewModel,
    settingsViewModel: SettingsViewModel,
    modifier: Modifier = Modifier
) {
    var selectedTab by rememberSaveable { mutableIntStateOf(0) }
    val lastFinishedComparison by viewModel.lastFinishedComparison.collectAsState()
    val selectedDetailComparison by viewModel.selectedDetailComparison.collectAsState()
    val weightUnit by viewModel.weightUnit.collectAsState()

    // If viewing comparison screen (either after finishing or tapped from history)
    val activeComparison = selectedDetailComparison ?: lastFinishedComparison
    if (activeComparison != null) {
        WorkoutComparisonScreen(
            comparison = activeComparison,
            weightUnit = weightUnit,
            onBackClick = {
                if (selectedDetailComparison != null) {
                    viewModel.clearSelectedDetailComparison()
                } else {
                    viewModel.clearLastFinishedComparison()
                }
            }
        )
        return
    }

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surfaceContainer,
                modifier = Modifier
            ) {
                NavTab.entries.forEachIndexed { index, tab ->
                    val tabTitle = stringResource(tab.titleRes)
                    NavigationBarItem(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        icon = {
                            Icon(
                                imageVector = tab.icon,
                                contentDescription = tabTitle
                            )
                        },
                        label = {
                            Text(
                                tabTitle,
                                fontSize = 10.sp
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            selectedTextColor = MaterialTheme.colorScheme.primary,
                            unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            indicatorColor = MaterialTheme.colorScheme.surfaceContainerHighest
                        )
                    )
                }
            }
        },
        containerColor = MaterialTheme.colorScheme.background,
        modifier = modifier
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background)
        ) {
            when (selectedTab) {
                0 -> ActiveWorkoutScreen(
                    viewModel = viewModel,
                    onWorkoutFinished = {
                        // Handled by activeComparison trigger
                    }
                )
                1 -> HistoryScreen(
                    viewModel = viewModel,
                    weightUnit = weightUnit,
                    onWorkoutClick = { workoutId ->
                        viewModel.viewWorkoutDetail(workoutId)
                    }
                )
                2 -> ExercisesScreen(
                    viewModel = viewModel
                )
                3 -> RecordsScreen(
                    viewModel = viewModel,
                    weightUnit = weightUnit
                )
                4 -> SettingsScreen(
                    viewModel = settingsViewModel
                )
            }
        }
    }
}
