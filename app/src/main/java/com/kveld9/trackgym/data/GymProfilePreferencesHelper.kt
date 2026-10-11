package com.kveld9.trackgym.data

import com.kveld9.trackgym.domain.calculator.GymEquipmentProfileEngine
import com.kveld9.trackgym.domain.model.GymEquipmentProfile
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

/**
 * Unified helper consolidating equipment profile persistence and flow extraction
 * across ViewModels (GymViewModel and SettingsViewModel).
 */
object GymProfilePreferencesHelper {

    fun getProfilesFlow(themePreferences: ThemePreferences?): Flow<List<GymEquipmentProfile>> {
        return themePreferences?.themeSettings?.map {
            GymEquipmentProfileEngine.parseProfiles(it.gymProfilesJson)
        } ?: flowOf(GymEquipmentProfile.defaultProfiles())
    }

    fun getActiveProfileFlow(themePreferences: ThemePreferences?): Flow<GymEquipmentProfile> {
        return themePreferences?.themeSettings?.map {
            val list = GymEquipmentProfileEngine.parseProfiles(it.gymProfilesJson)
            GymEquipmentProfileEngine.getActiveProfile(list, it.activeGymProfileId)
        } ?: flowOf(GymEquipmentProfile.defaultProfiles().first())
    }

    suspend fun selectProfile(themePreferences: ThemePreferences?, id: String) {
        themePreferences?.setActiveGymProfileId(id)
    }

    suspend fun saveProfile(
        themePreferences: ThemePreferences?,
        currentList: List<GymEquipmentProfile>,
        profile: GymEquipmentProfile
    ) {
        val updated = GymEquipmentProfileEngine.addOrUpdateProfile(currentList, profile)
        val json = GymEquipmentProfileEngine.serializeProfiles(updated)
        themePreferences?.setGymProfilesJson(json)
        if (profile.isDefault || currentList.size == 1) {
            themePreferences?.setActiveGymProfileId(profile.id)
        }
    }

    suspend fun deleteProfile(
        themePreferences: ThemePreferences?,
        currentList: List<GymEquipmentProfile>,
        profileIdToDelete: String,
        activeGymProfileId: String? = null
    ) {
        val updated = GymEquipmentProfileEngine.deleteProfile(currentList, profileIdToDelete)
        val json = GymEquipmentProfileEngine.serializeProfiles(updated)
        themePreferences?.setGymProfilesJson(json)
        val activeId = activeGymProfileId ?: themePreferences?.themeSettings?.firstOrNull()?.activeGymProfileId
        if (activeId == profileIdToDelete) {
            val newActive = updated.firstOrNull { it.isDefault } ?: updated.firstOrNull()
            newActive?.let { themePreferences?.setActiveGymProfileId(it.id) }
        }
    }
}
