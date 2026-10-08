package com.kveld9.trackgym.ui.util

import androidx.compose.runtime.compositionLocalOf
import com.kveld9.trackgym.domain.model.GymEquipmentProfile

/**
 * CompositionLocal providing the currently active gym equipment profile to composables down the hierarchy.
 */
val LocalActiveGymProfile = compositionLocalOf { GymEquipmentProfile.defaultProfiles().first() }
