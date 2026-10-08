package com.kveld9.trackgym.domain.calculator

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class HealthConnectSyncEngineTest {

    @Test
    fun testEstimateCaloriesBurned_typicalWorkout() {
        // 60 minutes session for 80 kg user with 5.5 METs
        // 5.5 * 80 * 1.0 = 440.0 kcal
        val calories = HealthConnectSyncEngine.estimateCaloriesBurned(
            durationMinutes = 60,
            userBodyWeightKg = 80.0
        )
        assertEquals(440.0, calories, 0.001)
    }

    @Test
    fun testEstimateCaloriesBurned_halfHourSession() {
        // 30 minutes session for 70 kg user
        // 5.5 * 70 * 0.5 = 192.5 kcal
        val calories = HealthConnectSyncEngine.estimateCaloriesBurned(
            durationMinutes = 30,
            userBodyWeightKg = 70.0
        )
        assertEquals(192.5, calories, 0.001)
    }

    @Test
    fun testEstimateCaloriesBurned_zeroOrNegative() {
        assertEquals(0.0, HealthConnectSyncEngine.estimateCaloriesBurned(0, 75.0), 0.001)
        assertEquals(0.0, HealthConnectSyncEngine.estimateCaloriesBurned(-15, 75.0), 0.001)
        assertEquals(0.0, HealthConnectSyncEngine.estimateCaloriesBurned(45, 0.0), 0.001)
        assertEquals(0.0, HealthConnectSyncEngine.estimateCaloriesBurned(45, -75.0), 0.001)
    }

    @Test
    fun testCalculateDurationMinutes() {
        val start = 1_000_000L
        val end = start + (45 * 60 * 1000L) // 45 minutes
        val duration = HealthConnectSyncEngine.calculateDurationMinutes(start, end)
        assertEquals(45L, duration)

        // Negative or equal
        assertEquals(0L, HealthConnectSyncEngine.calculateDurationMinutes(end, start))
        assertEquals(0L, HealthConnectSyncEngine.calculateDurationMinutes(start, start))
    }
}
