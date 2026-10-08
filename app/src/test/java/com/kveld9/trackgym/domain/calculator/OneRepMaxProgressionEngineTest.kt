package com.kveld9.trackgym.domain.calculator

import com.kveld9.trackgym.domain.model.ExerciseHistoryEntry
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class OneRepMaxProgressionEngineTest {

    private fun createEntry(dateMillis: Long, best1Rm: Double, maxWeight: Double): ExerciseHistoryEntry {
        return ExerciseHistoryEntry(
            workoutId = dateMillis,
            workoutName = "Session",
            dateMillis = dateMillis,
            maxWeightKg = maxWeight,
            totalVolumeKg = 1000.0,
            completedSetsCount = 3,
            best1RmKg = best1Rm,
            completedSets = emptyList(),
            bestSetVolumeKg = 300.0,
            bestTimeSeconds = 0,
            totalTimeSeconds = 0
        )
    }

    @Test
    fun `empty history yields zero progression series`() {
        val series = OneRepMaxProgressionEngine.calculateProgression(emptyList())
        assertTrue(series.points.isEmpty())
        assertEquals(0.0, series.min1RmKg, 0.01)
        assertEquals(0.0, series.max1RmKg, 0.01)
        assertEquals(0.0, series.deltaKg, 0.01)
        assertEquals(0f, series.percentageGrowth, 0.01f)
    }

    @Test
    fun `single entry yields zero delta and accurate bounds`() {
        val entry = createEntry(1000000L, 100.0, 80.0)
        val series = OneRepMaxProgressionEngine.calculateProgression(listOf(entry))

        assertEquals(1, series.points.size)
        assertEquals(100.0, series.min1RmKg, 0.01)
        assertEquals(100.0, series.max1RmKg, 0.01)
        assertEquals(0.0, series.deltaKg, 0.01)
        assertEquals(0f, series.percentageGrowth, 0.01f)
    }

    @Test
    fun `chronological progression calculates positive delta and percentage growth`() {
        val e1 = createEntry(1000L, 80.0, 70.0)
        val e2 = createEntry(2000L, 90.0, 75.0)
        val e3 = createEntry(3000L, 100.0, 85.0)

        // Pass out of order to verify sorting
        val series = OneRepMaxProgressionEngine.calculateProgression(listOf(e2, e1, e3))

        assertEquals(3, series.points.size)
        assertEquals(80.0, series.points[0].oneRmKg, 0.01)
        assertEquals(90.0, series.points[1].oneRmKg, 0.01)
        assertEquals(100.0, series.points[2].oneRmKg, 0.01)

        assertEquals(80.0, series.min1RmKg, 0.01)
        assertEquals(100.0, series.max1RmKg, 0.01)
        assertEquals(20.0, series.deltaKg, 0.01)
        assertEquals(25.0f, series.percentageGrowth, 0.01f)
    }
}
