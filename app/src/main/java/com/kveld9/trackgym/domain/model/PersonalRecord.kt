package com.kveld9.trackgym.domain.model

import androidx.annotation.StringRes
import com.kveld9.trackgym.R

enum class RecordType(@get:StringRes val nameRes: Int, val displayName: String) {
    MAX_WEIGHT(R.string.record_max_weight, "Max Weight"),
    MAX_REPS_AT_WEIGHT(R.string.record_max_reps, "Rep Record"),
    ESTIMATED_1RM(R.string.record_estimated_1rm, "Best Estimated 1RM"),
    MAX_VOLUME_SET(R.string.record_max_volume, "Set Volume"),
    MAX_AMRAP_REPS_AT_WEIGHT(R.string.record_max_amrap_reps, "AMRAP Rep Record")
}

data class PersonalRecord(
    val id: Long = 0,
    val exerciseId: Long,
    val recordType: RecordType,
    val recordValue: Double,
    val weightKg: Double,
    val reps: Int,
    val achievedAt: Long = System.currentTimeMillis(),
    val workoutId: Long = 0,
    val description: String = ""
)
