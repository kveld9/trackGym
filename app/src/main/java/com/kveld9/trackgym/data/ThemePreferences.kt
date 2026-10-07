package com.kveld9.trackgym.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

data class ThemeSettings(
    val themeMode: String = ThemePreferences.MODE_AMOLED,
    val weightUnit: String = "KG", // "KG", "LB"
    val distanceUnit: String = "KM", // "KM", "MI"
    val autoRestTimer: Boolean = true,
    val defaultRestSeconds: Int = 90,
    val exerciseLanguage: String = ThemePreferences.EXERCISE_LANG_SYSTEM, // "SYSTEM", "ENGLISH"
    val ormFormula: String = "EPLEY",
    val doubleDumbbellVolume: Boolean = true,
    val excludeWarmupFromVolume: Boolean = false
)

class ThemePreferences(
    private val dataStore: DataStore<Preferences>
) {
    constructor(context: Context) : this(context.dataStore)

    companion object {
        const val MODE_LIGHT = "LIGHT"
        const val MODE_DARK = "DARK"
        const val MODE_AMOLED = "AMOLED"

        const val EXERCISE_LANG_SYSTEM = "SYSTEM"
        const val EXERCISE_LANG_ENGLISH = "ENGLISH"

        val THEME_MODE_KEY = stringPreferencesKey("theme_mode")
        val WEIGHT_UNIT_KEY = stringPreferencesKey("weight_unit")
        val DISTANCE_UNIT_KEY = stringPreferencesKey("distance_unit")
        val AUTO_REST_TIMER_KEY = booleanPreferencesKey("auto_rest_timer")
        val DEFAULT_REST_SECONDS_KEY = intPreferencesKey("default_rest_seconds")
        val EXERCISE_LANGUAGE_KEY = stringPreferencesKey("exercise_language")
        val ORM_FORMULA_KEY = stringPreferencesKey("orm_formula")
        val DOUBLE_DUMBBELL_VOLUME_KEY = booleanPreferencesKey("double_dumbbell_volume")
        val EXCLUDE_WARMUP_FROM_VOLUME_KEY = booleanPreferencesKey("exclude_warmup_from_volume")

        private val LEGACY_AMOLED_KEY = booleanPreferencesKey("amoled_black")
    }

    val themeSettings: Flow<ThemeSettings> = dataStore.data
        .map { preferences ->
            val rawMode = preferences[THEME_MODE_KEY]
            val legacyAmoled = preferences[LEGACY_AMOLED_KEY] ?: false
            val resolvedMode = resolveThemeMode(rawMode, legacyAmoled)

            ThemeSettings(
                themeMode = resolvedMode,
                weightUnit = preferences[WEIGHT_UNIT_KEY] ?: "KG",
                distanceUnit = preferences[DISTANCE_UNIT_KEY] ?: "KM",
                autoRestTimer = preferences[AUTO_REST_TIMER_KEY] ?: true,
                defaultRestSeconds = preferences[DEFAULT_REST_SECONDS_KEY] ?: 90,
                exerciseLanguage = preferences[EXERCISE_LANGUAGE_KEY] ?: EXERCISE_LANG_SYSTEM,
                ormFormula = preferences[ORM_FORMULA_KEY] ?: "EPLEY",
                doubleDumbbellVolume = preferences[DOUBLE_DUMBBELL_VOLUME_KEY] ?: true,
                excludeWarmupFromVolume = preferences[EXCLUDE_WARMUP_FROM_VOLUME_KEY] ?: false
            )
        }

    private fun resolveThemeMode(rawMode: String?, legacyAmoled: Boolean): String = when {
        rawMode == MODE_AMOLED -> MODE_AMOLED
        rawMode == MODE_LIGHT -> MODE_LIGHT
        rawMode == MODE_DARK -> MODE_DARK
        legacyAmoled -> MODE_AMOLED
        else -> MODE_AMOLED
    }

    suspend fun setThemeMode(mode: String) {
        dataStore.edit { preferences ->
            preferences[THEME_MODE_KEY] = mode
            preferences.remove(LEGACY_AMOLED_KEY)
        }
    }

    suspend fun setWeightUnit(unit: String) {
        dataStore.edit { preferences ->
            preferences[WEIGHT_UNIT_KEY] = unit
        }
    }

    suspend fun setDistanceUnit(unit: String) {
        dataStore.edit { preferences ->
            preferences[DISTANCE_UNIT_KEY] = unit
        }
    }

    suspend fun setAutoRestTimer(enabled: Boolean) {
        dataStore.edit { preferences ->
            preferences[AUTO_REST_TIMER_KEY] = enabled
        }
    }

    suspend fun setDefaultRestSeconds(seconds: Int) {
        dataStore.edit { preferences ->
            preferences[DEFAULT_REST_SECONDS_KEY] = seconds
        }
    }

    suspend fun setExerciseLanguage(language: String) {
        dataStore.edit { preferences ->
            preferences[EXERCISE_LANGUAGE_KEY] = language
        }
    }

    suspend fun setOrmFormula(formula: String) {
        dataStore.edit { preferences ->
            preferences[ORM_FORMULA_KEY] = formula
        }
    }

    suspend fun setDoubleDumbbellVolume(enabled: Boolean) {
        dataStore.edit { preferences ->
            preferences[DOUBLE_DUMBBELL_VOLUME_KEY] = enabled
        }
    }

    suspend fun setExcludeWarmupFromVolume(enabled: Boolean) {
        dataStore.edit { preferences ->
            preferences[EXCLUDE_WARMUP_FROM_VOLUME_KEY] = enabled
        }
    }
}
