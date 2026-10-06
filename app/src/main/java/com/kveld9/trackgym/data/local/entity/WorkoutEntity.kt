package com.kveld9.trackgym.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workouts")
data class WorkoutEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val startedAt: Long,
    val completedAt: Long? = null,
    val durationSeconds: Long = 0,
    val isCompleted: Boolean = false,
    val notes: String = ""
)
