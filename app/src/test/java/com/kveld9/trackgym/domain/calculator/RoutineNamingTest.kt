package com.kveld9.trackgym.domain.calculator

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class RoutineNamingTest {

    @Test
    fun `resolveNewRoutineName with empty string returns null`() {
        assertNull(resolveNewRoutineName(""))
    }

    @Test
    fun `resolveNewRoutineName with only whitespace returns null`() {
        assertNull(resolveNewRoutineName("   "))
        assertNull(resolveNewRoutineName("\t\n\r "))
    }

    @Test
    fun `resolveNewRoutineName with surrounding whitespace trims correctly`() {
        assertEquals("Legs Day", resolveNewRoutineName("  Legs Day  "))
        assertEquals("Upper Power", resolveNewRoutineName("\tUpper Power\n"))
    }

    @Test
    fun `resolveNewRoutineName with clean name preserves name intact`() {
        assertEquals("Full Body A", resolveNewRoutineName("Full Body A"))
        assertEquals("Push 1", resolveNewRoutineName("Push 1"))
    }

    @Test
    fun `sanitizeRoutineTargetSets preserves valid values and falls back for invalid`() {
        assertEquals(3, sanitizeRoutineTargetSets(3))
        assertEquals(5, sanitizeRoutineTargetSets(5))
        assertEquals(3, sanitizeRoutineTargetSets(0))
        assertEquals(3, sanitizeRoutineTargetSets(-1))
        assertEquals(4, sanitizeRoutineTargetSets(0, fallback = 4))
    }

    @Test
    fun `sanitizeRoutineDefaultReps preserves valid values and falls back for invalid`() {
        assertEquals(10, sanitizeRoutineDefaultReps(10))
        assertEquals(12, sanitizeRoutineDefaultReps(12))
        assertEquals(10, sanitizeRoutineDefaultReps(0))
        assertEquals(10, sanitizeRoutineDefaultReps(-5))
        assertEquals(8, sanitizeRoutineDefaultReps(0, fallback = 8))
    }

    @Test
    fun `sanitizeRoutineDefaultWeight preserves non-negative weights and zeroes out negatives`() {
        assertEquals(60.0, sanitizeRoutineDefaultWeight(60.0), 0.001)
        assertEquals(0.0, sanitizeRoutineDefaultWeight(0.0), 0.001)
        assertEquals(0.0, sanitizeRoutineDefaultWeight(-10.5), 0.001)
    }
}

