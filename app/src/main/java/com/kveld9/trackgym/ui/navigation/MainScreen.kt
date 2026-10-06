package com.kveld9.trackgym.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
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
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
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
import com.kveld9.trackgym.ui.util.LocalKeepEnglishExerciseNames
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

    LaunchedEffect(selectedTab) {
        if (selectedTab == 0) {
            viewModel.refreshActiveWorkout()
        }
    }

    val keepExerciseNamesInEnglish by viewModel.keepExerciseNamesInEnglish.collectAsState()

    CompositionLocalProvider(LocalKeepEnglishExerciseNames provides keepExerciseNamesInEnglish) {
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
            return@CompositionLocalProvider
        }

        Box(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
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

            // Truly floating Navigation Bar
            Box(
                modifier = Modifier
                    .align(androidx.compose.ui.Alignment.BottomCenter)
                    .fillMaxWidth()
                    .navigationBarsPadding()
                    .padding(start = 16.dp, end = 16.dp, bottom = 12.dp)
            ) {
                Surface(
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.surfaceContainer.copy(alpha = 0.95f),
                    tonalElevation = 6.dp,
                    shadowElevation = 8.dp,
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.25f)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    NavigationBar(
                        containerColor = Color.Transparent,
                        windowInsets = WindowInsets(0, 0, 0, 0),
                        modifier = Modifier.height(64.dp)
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
                                        fontSize = 10.sp,
                                        fontWeight = if (selectedTab == index) FontWeight.Bold else FontWeight.Normal
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
                }
            }
        }
    }
}
