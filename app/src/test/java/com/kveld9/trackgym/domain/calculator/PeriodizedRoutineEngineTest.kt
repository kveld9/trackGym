package com.kveld9.trackgym.domain.calculator

import com.kveld9.trackgym.domain.model.PeriodizationPhase
import com.kveld9.trackgym.domain.model.PeriodizedWeek
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class PeriodizedRoutineEngineTest {

    @Test
    fun createDefaultCycle_4Weeks_createsAccumulationIntensificationAndDeload() {
        val cycle = PeriodizedRoutineEngine.createDefaultCycle(4, deloadAtEnd = true)
        assertEquals(4, cycle.totalWeeks)
        assertEquals(1, cycle.currentWeek)
        assertEquals(4, cycle.weeks.size)

        // Week 1: Accumulation
        assertEquals(1, cycle.weeks[0].weekNumber)
        assertEquals(PeriodizationPhase.ACCUMULATION, cycle.weeks[0].phase)
        assertEquals(1.0, cycle.weeks[0].volumeMultiplier, 0.001)
        assertEquals(0.95, cycle.weeks[0].intensityMultiplier, 0.001)

        // Week 2: Accumulation baseline
        assertEquals(2, cycle.weeks[1].weekNumber)
        assertEquals(PeriodizationPhase.ACCUMULATION, cycle.weeks[1].phase)
        assertEquals(1.00, cycle.weeks[1].intensityMultiplier, 0.001)

        // Week 3: Intensification
        assertEquals(3, cycle.weeks[2].weekNumber)
        assertEquals(PeriodizationPhase.INTENSIFICATION, cycle.weeks[2].phase)
        assertEquals(1.05, cycle.weeks[2].intensityMultiplier, 0.001)

        // Week 4: Deload
        assertEquals(4, cycle.weeks[3].weekNumber)
        assertEquals(PeriodizationPhase.DELOAD, cycle.weeks[3].phase)
        assertTrue(cycle.weeks[3].isDeload)
        assertEquals(0.5, cycle.weeks[3].volumeMultiplier, 0.001)
        assertEquals(0.85, cycle.weeks[3].intensityMultiplier, 0.001)
    }

    @Test
    fun createDefaultCycle_6Weeks_createsExpectedPhases() {
        val cycle = PeriodizedRoutineEngine.createDefaultCycle(6, deloadAtEnd = true)
        assertEquals(6, cycle.totalWeeks)
        assertEquals(6, cycle.weeks.size)

        // Week 6 is Deload
        val deloadWeek = cycle.weeks.last()
        assertEquals(PeriodizationPhase.DELOAD, deloadWeek.phase)
        assertTrue(deloadWeek.isDeload)
        assertEquals(0.5, deloadWeek.volumeMultiplier, 0.001)
        assertEquals(0.85, deloadWeek.intensityMultiplier, 0.001)
    }

    @Test
    fun calculatePrescribedSets_respectsMultiplierAndMinimum() {
        val normalWeek = PeriodizedWeek(1, PeriodizationPhase.ACCUMULATION, volumeMultiplier = 1.0)
        assertEquals(4, PeriodizedRoutineEngine.calculatePrescribedSets(4, normalWeek))

        val deloadWeek = PeriodizedWeek(4, PeriodizationPhase.DELOAD, volumeMultiplier = 0.5)
        assertEquals(2, PeriodizedRoutineEngine.calculatePrescribedSets(4, deloadWeek))
        assertEquals(2, PeriodizedRoutineEngine.calculatePrescribedSets(3, deloadWeek))
        assertEquals(1, PeriodizedRoutineEngine.calculatePrescribedSets(1, deloadWeek))
        assertEquals(1, PeriodizedRoutineEngine.calculatePrescribedSets(0, deloadWeek))

        val volumeWeek = PeriodizedWeek(2, PeriodizationPhase.ACCUMULATION, volumeMultiplier = 1.25)
        assertEquals(5, PeriodizedRoutineEngine.calculatePrescribedSets(4, volumeWeek))
    }

    @Test
    fun calculatePrescribedWeight_appliesIntensityMultiplierAndRounding() {
        val normalWeek = PeriodizedWeek(2, PeriodizationPhase.ACCUMULATION, intensityMultiplier = 1.0)
        assertEquals(100.0, PeriodizedRoutineEngine.calculatePrescribedWeight(100.0, normalWeek), 0.001)

        // Intensification +5%
        val heavyWeek = PeriodizedWeek(3, PeriodizationPhase.INTENSIFICATION, intensityMultiplier = 1.05)
        assertEquals(105.0, PeriodizedRoutineEngine.calculatePrescribedWeight(100.0, heavyWeek), 0.001)

        // Deload -15%
        val deloadWeek = PeriodizedWeek(4, PeriodizationPhase.DELOAD, intensityMultiplier = 0.85)
        assertEquals(85.0, PeriodizedRoutineEngine.calculatePrescribedWeight(100.0, deloadWeek), 0.001)

        // Rounding to 0.5 step
        // 82.5 * 1.05 = 86.625 -> 86.5
        assertEquals(86.5, PeriodizedRoutineEngine.calculatePrescribedWeight(82.5, heavyWeek, stepKg = 0.5), 0.001)
    }

    @Test
    fun advanceAndPreviousCycle_navigatesProperly() {
        var cycle = PeriodizedRoutineEngine.createDefaultCycle(4)
        assertEquals(1, cycle.currentWeek)

        cycle = PeriodizedRoutineEngine.advanceCycle(cycle)
        assertEquals(2, cycle.currentWeek)

        cycle = PeriodizedRoutineEngine.advanceCycle(cycle)
        assertEquals(3, cycle.currentWeek)

        cycle = PeriodizedRoutineEngine.advanceCycle(cycle)
        assertEquals(4, cycle.currentWeek)

        // Auto-repeats back to week 1
        cycle = PeriodizedRoutineEngine.advanceCycle(cycle, autoRepeat = true)
        assertEquals(1, cycle.currentWeek)

        // Previous wraps to week 4
        cycle = PeriodizedRoutineEngine.previousCycle(cycle)
        assertEquals(4, cycle.currentWeek)

        cycle = PeriodizedRoutineEngine.previousCycle(cycle)
        assertEquals(3, cycle.currentWeek)

        // setCycleWeek clamps within bounds
        cycle = PeriodizedRoutineEngine.setCycleWeek(cycle, 100)
        assertEquals(4, cycle.currentWeek)

        cycle = PeriodizedRoutineEngine.setCycleWeek(cycle, -5)
        assertEquals(1, cycle.currentWeek)
    }

    @Test
    fun encodeAndDecode_roundTripsFidelity() {
        val original = PeriodizedRoutineEngine.createDefaultCycle(4)
        val encoded = PeriodizedRoutineEngine.encode(original)

        assertNotNull(encoded)
        assertTrue(encoded.contains("TOTAL=4"))
        assertTrue(encoded.contains("CUR=1"))

        val decoded = PeriodizedRoutineEngine.decode(encoded)
        assertNotNull(decoded)
        assertEquals(original.totalWeeks, decoded!!.totalWeeks)
        assertEquals(original.currentWeek, decoded.currentWeek)
        assertEquals(original.autoAdvanceOnCompletion, decoded.autoAdvanceOnCompletion)
        assertEquals(original.weeks.size, decoded.weeks.size)

        for (i in original.weeks.indices) {
            val oWeek = original.weeks[i]
            val dWeek = decoded.weeks[i]
            assertEquals(oWeek.weekNumber, dWeek.weekNumber)
            assertEquals(oWeek.phase, dWeek.phase)
            assertEquals(oWeek.volumeMultiplier, dWeek.volumeMultiplier, 0.01)
            assertEquals(oWeek.intensityMultiplier, dWeek.intensityMultiplier, 0.01)
        }
    }

    @Test
    fun decode_handlesNullOrInvalidSafely() {
        assertNull(PeriodizedRoutineEngine.decode(null))
        assertNull(PeriodizedRoutineEngine.decode(""))
        assertNull(PeriodizedRoutineEngine.decode("   "))
    }
}
