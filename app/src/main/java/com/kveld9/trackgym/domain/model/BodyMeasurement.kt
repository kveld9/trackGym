package com.kveld9.trackgym.domain.model

import androidx.annotation.StringRes
import com.kveld9.trackgym.R

/**
 * Standard body telemetry measurement types (circumferences in cm or inches, weight in kg, fat in %).
 */
enum class BodyMeasurementType(
    @get:StringRes val displayNameRes: Int,
    val defaultUnit: String
) {
    WEIGHT(R.string.body_meas_weight, "kg"),
    BODY_FAT(R.string.body_meas_body_fat, "%"),
    NECK(R.string.body_meas_neck, "cm"),
    SHOULDERS(R.string.body_meas_shoulders, "cm"),
    CHEST(R.string.body_meas_chest, "cm"),
    LEFT_BICEP(R.string.body_meas_left_bicep, "cm"),
    RIGHT_BICEP(R.string.body_meas_right_bicep, "cm"),
    LEFT_FOREARM(R.string.body_meas_left_forearm, "cm"),
    RIGHT_FOREARM(R.string.body_meas_right_forearm, "cm"),
    WAIST(R.string.body_meas_waist, "cm"),
    HIPS(R.string.body_meas_hips, "cm"),
    LEFT_THIGH(R.string.body_meas_left_thigh, "cm"),
    RIGHT_THIGH(R.string.body_meas_right_thigh, "cm"),
    LEFT_CALF(R.string.body_meas_left_calf, "cm"),
    RIGHT_CALF(R.string.body_meas_right_calf, "cm")
}

/**
 * Domain model representing a single anthropometric measurement log.
 */
data class BodyMeasurement(
    val id: Long = 0,
    val type: BodyMeasurementType,
    val value: Double,
    val measuredAt: Long = System.currentTimeMillis(),
    val notes: String = ""
)
