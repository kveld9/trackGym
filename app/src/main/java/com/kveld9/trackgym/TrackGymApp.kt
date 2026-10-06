package com.kveld9.trackgym

import android.app.Application
import com.kveld9.trackgym.data.local.GymDatabase
import com.kveld9.trackgym.data.repository.GymRepository

class TrackGymApp : Application() {

    val database: GymDatabase by lazy {
        GymDatabase.getInstance(this)
    }

    val repository: GymRepository by lazy {
        GymRepository(database)
    }

    val themePreferences: com.kveld9.trackgym.data.ThemePreferences by lazy {
        com.kveld9.trackgym.data.ThemePreferences(this)
    }
}
