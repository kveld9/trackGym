package com.kveld9.trackgym.domain.calculator

import com.kveld9.trackgym.domain.model.GymEquipmentProfile
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.math.round

/**
 * Engine for parsing, serializing, selecting, and applying gym equipment profiles.
 */
object GymEquipmentProfileEngine {

    private val json = Json {
        ignoreUnknownKeys = true
        encodeDefaults = true
        prettyPrint = false
    }

    /**
     * Parses the JSON string of gym profiles. Falls back to default profiles if null, blank, or invalid.
     */
    fun parseProfiles(jsonString: String?): List<GymEquipmentProfile> {
        if (jsonString.isNullOrBlank()) return GymEquipmentProfile.defaultProfiles()
        return try {
            val list = json.decodeFromString<List<GymEquipmentProfile>>(jsonString)
            if (list.isEmpty()) GymEquipmentProfile.defaultProfiles() else list
        } catch (_: Exception) {
            GymEquipmentProfile.defaultProfiles()
        }
    }

    /**
     * Serializes a list of profiles to a JSON string.
     */
    fun serializeProfiles(profiles: List<GymEquipmentProfile>): String {
        val safeProfiles = if (profiles.isEmpty()) GymEquipmentProfile.defaultProfiles() else profiles
        return json.encodeToString(safeProfiles)
    }

    /**
     * Resolves the active profile given the preferred active ID.
     */
    fun getActiveProfile(
        profiles: List<GymEquipmentProfile>,
        activeId: String?
    ): GymEquipmentProfile {
        if (profiles.isEmpty()) return GymEquipmentProfile.defaultProfiles().first()
        return profiles.find { it.id == activeId }
            ?: profiles.find { it.isDefault }
            ?: profiles.first()
    }

    /**
     * Rounds a target weight to the closest achievable step based on the profile's minimum increment.
     */
    fun roundToIncrement(weight: Double, minIncrement: Double): Double {
        if (!weight.isFinite() || weight <= 0.0) return 0.0
        if (!minIncrement.isFinite() || minIncrement <= 0.0) return weight
        val steps = round(weight / minIncrement)
        val rounded = steps * minIncrement
        return round(rounded * 1000.0) / 1000.0
    }

    /**
     * Adds a new profile or updates an existing one by matching ID.
     */
    fun addOrUpdateProfile(
        profiles: List<GymEquipmentProfile>,
        profile: GymEquipmentProfile
    ): List<GymEquipmentProfile> {
        val mutable = if (profile.isDefault) {
            profiles.map { if (it.id != profile.id) it.copy(isDefault = false) else it }.toMutableList()
        } else {
            profiles.toMutableList()
        }

        val existingIndex = mutable.indexOfFirst { it.id == profile.id }
        if (existingIndex >= 0) {
            mutable[existingIndex] = profile
        } else {
            mutable.add(profile)
        }
        return mutable
    }

    /**
     * Deletes a profile by ID. Prevents deletion if only one profile remains.
     * Reassigns default if the deleted profile was the default.
     */
    fun deleteProfile(
        profiles: List<GymEquipmentProfile>,
        profileIdToDelete: String
    ): List<GymEquipmentProfile> {
        if (profiles.size <= 1) return profiles
        val filtered = profiles.filterNot { it.id == profileIdToDelete }
        if (filtered.none { it.isDefault }) {
            return filtered.mapIndexed { index, p -> if (index == 0) p.copy(isDefault = true) else p }
        }
        return filtered
    }
}
