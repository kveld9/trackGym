package com.kveld9.trackgym.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.kveld9.trackgym.R

/**
 * Navigation tabs preserving the original entry names and order.
 */
enum class NavTab(@get:StringRes val titleRes: Int, val icon: ImageVector) {
    TRAIN(R.string.tab_train, Icons.Default.FitnessCenter),
    HISTORY(R.string.tab_history, Icons.Default.History),
    EXERCISES(R.string.tab_exercises, Icons.AutoMirrored.Filled.List),
    RECORDS(R.string.tab_records, Icons.Default.EmojiEvents),
    SETTINGS(R.string.tab_settings, Icons.Default.Settings)
}
