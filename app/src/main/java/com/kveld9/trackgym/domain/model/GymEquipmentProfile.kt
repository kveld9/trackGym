package com.kveld9.trackgym.domain.model

import kotlinx.serialization.Serializable
import kotlin.math.round

/**
 * Equipment configuration profile for a specific training location (e.g. Commercial Gym, Home Gym, Hotel Gym).
 * Holds custom barbell weight, available plates inventory, and minimum micro-loading increment.
 */
@Serializable
data class GymEquipmentProfile(
    val id: String,
    val name: String,
    val barWeightKg: Double = 20.0,
    val availablePlatesKg: List<Double> = listOf(25.0, 20.0, 15.0, 10.0, 5.0, 2.5, 1.25),
    val minWeightIncrementKg: Double = 2.5,
    val isDefault: Boolean = false
) {
    fun barWeight(unit: WeightUnit): Double = when (unit) {
        WeightUnit.KG -> barWeightKg
        WeightUnit.LB -> {
            val converted = unit.fromKg(barWeightKg)
            round(converted * 10.0) / 10.0
        }
    }

    fun availablePlates(unit: WeightUnit): List<Double> = when (unit) {
        WeightUnit.KG -> availablePlatesKg
        WeightUnit.LB -> availablePlatesKg.map {
            val converted = unit.fromKg(it)
            round(converted * 10.0) / 10.0
        }
    }

    fun minWeightIncrement(unit: WeightUnit): Double = when (unit) {
        WeightUnit.KG -> minWeightIncrementKg
        WeightUnit.LB -> {
            val converted = unit.fromKg(minWeightIncrementKg)
            round(converted * 10.0) / 10.0
        }
    }

    companion object {
        const val DEFAULT_COMMERCIAL_ID = "commercial_gym"
        const val DEFAULT_HOME_ID = "home_gym"
        const val DEFAULT_HOTEL_ID = "hotel_gym"

        fun defaultProfiles(): List<GymEquipmentProfile> = listOf(
            GymEquipmentProfile(
                id = DEFAULT_COMMERCIAL_ID,
                name = "Commercial Gym",
                barWeightKg = 20.0,
                availablePlatesKg = listOf(25.0, 20.0, 15.0, 10.0, 5.0, 2.5, 1.25),
                minWeightIncrementKg = 2.5,
                isDefault = true
            ),
            GymEquipmentProfile(
                id = DEFAULT_HOME_ID,
                name = "Home Gym",
                barWeightKg = 15.0,
                availablePlatesKg = listOf(20.0, 10.0, 5.0, 2.5, 1.25),
                minWeightIncrementKg = 2.5,
                isDefault = false
            ),
            GymEquipmentProfile(
                id = DEFAULT_HOTEL_ID,
                name = "Hotel Gym",
                barWeightKg = 10.0,
                availablePlatesKg = listOf(20.0, 10.0, 5.0, 2.5),
                minWeightIncrementKg = 5.0,
                isDefault = false
            )
        )
    }
}
