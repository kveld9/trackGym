package com.kveld9.trackgym.domain.calculator

import org.junit.Assert.assertEquals
import org.junit.Test

class OneRepMaxCalculatorTest {

    @Test
    fun `calculate1RM with single rep returns exact weight`() {
        val oneRm = OneRepMaxCalculator.calculate1RM(100.0, 1)
        assertEquals(100.0, oneRm, 0.01)
    }

    @Test
    fun `calculate1RM with multiple reps applies Epley formula`() {
        // 15kg x 8 reps -> 15 * (1 + 8/30) = 15 * 1.26667 = 19.0
        val oneRm = OneRepMaxCalculator.calculate1RM(15.0, 8)
        assertEquals(19.0, oneRm, 0.1)
    }

    @Test
    fun `calculate1RM handles zero or negative values safely`() {
        assertEquals(0.0, OneRepMaxCalculator.calculate1RM(0.0, 10), 0.01)
        assertEquals(0.0, OneRepMaxCalculator.calculate1RM(100.0, 0), 0.01)
        assertEquals(0.0, OneRepMaxCalculator.calculate1RM(-50.0, 5), 0.01)
    }
}
