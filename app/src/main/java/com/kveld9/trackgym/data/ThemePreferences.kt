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
    val dynamicColor: Boolean = true,
    val themeMode: String = "SYSTEM", // "SYSTEM", "DARK", "LIGHT"
    val amoledBlack: Boolean = false,
    val weightUnit: String = "KG", // "KG", "LB"
    val distanceUnit: String = "KM", // "KM", "MI"
    val autoRestTimer: Boolean = true,
    val defaultRestSeconds: Int = 90
)

class ThemePreferences(
    private val dataStore: DataStore<Preferences>
) {
    constructor(context: Context) : this(context.dataStore)

    companion object {
        val DYNAMIC_COLOR_KEY = booleanPreferencesKey("dynamic_color")
        val THEME_MODE_KEY = stringPreferencesKey("theme_mode")
        val AMOLED_BLACK_KEY = booleanPreferencesKey("amoled_black")
        val WEIGHT_UNIT_KEY = stringPreferencesKey("weight_unit")
        val DISTANCE_UNIT_KEY = stringPreferencesKey("distance_unit")
        val AUTO_REST_TIMER_KEY = booleanPreferencesKey("auto_rest_timer")
        val DEFAULT_REST_SECONDS_KEY = intPreferencesKey("default_rest_seconds")
    }

    val themeSettings: Flow<ThemeSettings> = dataStore.data
        .map { preferences ->
            ThemeSettings(
                dynamicColor = preferences[DYNAMIC_COLOR_KEY] ?: true,
                themeMode = preferences[THEME_MODE_KEY] ?: "SYSTEM",
                amoledBlack = preferences[AMOLED_BLACK_KEY] ?: false,
                weightUnit = preferences[WEIGHT_UNIT_KEY] ?: "KG",
                distanceUnit = preferences[DISTANCE_UNIT_KEY] ?: "KM",
                autoRestTimer = preferences[AUTO_REST_TIMER_KEY] ?: true,
                defaultRestSeconds = preferences[DEFAULT_REST_SECONDS_KEY] ?: 90
            )
        }

    suspend fun setDynamicColor(enabled: Boolean) {
        dataStore.edit { preferences ->
            preferences[DYNAMIC_COLOR_KEY] = enabled
        }
    }

    suspend fun setThemeMode(mode: String) {
        dataStore.edit { preferences ->
            preferences[THEME_MODE_KEY] = mode
        }
    }

    suspend fun setAmoledBlack(enabled: Boolean) {
        dataStore.edit { preferences ->
            preferences[AMOLED_BLACK_KEY] = enabled
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
}
