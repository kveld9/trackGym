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
}
