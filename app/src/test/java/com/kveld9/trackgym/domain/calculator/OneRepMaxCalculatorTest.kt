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
        assertEquals(0.0, OneRepMaxCalculator.calculate1RM(Double.NaN, 5), 0.01)
        assertEquals(0.0, OneRepMaxCalculator.calculate1RM(Double.POSITIVE_INFINITY, 5), 0.01)
    }

    @Test
    fun `calculate1RM with all models returns exact weight on single rep`() {
        OneRepMaxFormula.entries.forEach { formula ->
            val result = OneRepMaxCalculator.calculate1RM(80.0, 1, formula)
            assertEquals("Formula $formula should return exact weight on 1 rep", 80.0, result, 0.01)
        }
    }

    @Test
    fun `calculate1RM applies Brzycki formula correctly`() {
        // 100kg x 10 reps -> 100 * (36 / (37 - 10)) = 100 * 36 / 27 = 133.3
        val result = OneRepMaxCalculator.calculate1RM(100.0, 10, OneRepMaxFormula.BRZYCKI)
        assertEquals(133.3, result, 0.1)
    }

    @Test
    fun `calculate1RM applies Wathan formula correctly`() {
        val result = OneRepMaxCalculator.calculate1RM(100.0, 10, OneRepMaxFormula.WATHAN)
        assertEquals(134.7, result, 0.2)
    }

    @Test
    fun `calculate1RM applies Lander formula correctly`() {
        val result = OneRepMaxCalculator.calculate1RM(100.0, 10, OneRepMaxFormula.LANDER)
        assertEquals(134.1, result, 0.2)
    }

    @Test
    fun `calculate1RM applies Lombardi formula correctly`() {
        // 100kg x 10 reps -> 100 * 10^0.10 = 125.9
        val result = OneRepMaxCalculator.calculate1RM(100.0, 10, OneRepMaxFormula.LOMBARDI)
        assertEquals(125.9, result, 0.2)
    }

    @Test
    fun `calculate1RM applies Mayhew formula correctly`() {
        val result = OneRepMaxCalculator.calculate1RM(100.0, 10, OneRepMaxFormula.MAYHEW)
        assertEquals(130.9, result, 0.2)
    }

    @Test
    fun `calculate1RM applies OConner formula correctly`() {
        // 100kg x 10 reps -> 100 * (1 + 0.025 * 10) = 125.0
        val result = OneRepMaxCalculator.calculate1RM(100.0, 10, OneRepMaxFormula.OCONNER)
        assertEquals(125.0, result, 0.1)
    }
}
