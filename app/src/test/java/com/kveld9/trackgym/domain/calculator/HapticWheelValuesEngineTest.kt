package com.kveld9.trackgym.domain.calculator

import org.junit.Assert.assertEquals
import org.junit.Test

class HapticWheelValuesEngineTest {

    @Test
    fun splitWeight_standardWeights_splitsAccurately() {
        val split1 = HapticWheelValuesEngine.splitWeight(82.5)
        assertEquals(82, split1.whole)
        assertEquals(0.5, split1.fraction, 0.001)
        assertEquals(82.5, split1.total, 0.001)

        val split2 = HapticWheelValuesEngine.splitWeight(100.25)
        assertEquals(100, split2.whole)
        assertEquals(0.25, split2.fraction, 0.001)
        assertEquals(100.25, split2.total, 0.001)

        val split3 = HapticWheelValuesEngine.splitWeight(60.0)
        assertEquals(60, split3.whole)
        assertEquals(0.0, split3.fraction, 0.001)
        assertEquals(60.0, split3.total, 0.001)

        val split4 = HapticWheelValuesEngine.splitWeight(97.75)
        assertEquals(97, split4.whole)
        assertEquals(0.75, split4.fraction, 0.001)
        assertEquals(97.75, split4.total, 0.001)
    }

    @Test
    fun splitWeight_nonExactFraction_snapsToClosestAvailable() {
        // 82.3 should snap to 0.25 when using [0.0, 0.25, 0.5, 0.75]
        val split = HapticWheelValuesEngine.splitWeight(82.3)
        assertEquals(82, split.whole)
        assertEquals(0.25, split.fraction, 0.001)

        // 82.6 should snap to 0.5
        val split2 = HapticWheelValuesEngine.splitWeight(82.6)
        assertEquals(82, split2.whole)
        assertEquals(0.5, split2.fraction, 0.001)

        // When using half fractions [0.0, 0.5], 82.3 should snap to 0.5
        val splitHalf = HapticWheelValuesEngine.splitWeight(82.3, HapticWheelValuesEngine.HALF_FRACTIONS)
        assertEquals(82, splitHalf.whole)
        assertEquals(0.5, splitHalf.fraction, 0.001)
    }

    @Test
    fun splitWeight_edgeCases_handlesZeroAndNaN() {
        val splitZero = HapticWheelValuesEngine.splitWeight(0.0)
        assertEquals(0, splitZero.whole)
        assertEquals(0.0, splitZero.fraction, 0.001)

        val splitNeg = HapticWheelValuesEngine.splitWeight(-15.0)
        assertEquals(0, splitNeg.whole)
        assertEquals(0.0, splitNeg.fraction, 0.001)

        val splitNan = HapticWheelValuesEngine.splitWeight(Double.NaN)
        assertEquals(0, splitNan.whole)
        assertEquals(0.0, splitNan.fraction, 0.001)
    }

    @Test
    fun combineWeight_combinesWholeAndFraction() {
        assertEquals(102.5, HapticWheelValuesEngine.combineWeight(102, 0.5), 0.001)
        assertEquals(75.25, HapticWheelValuesEngine.combineWeight(75, 0.25), 0.001)
        assertEquals(80.0, HapticWheelValuesEngine.combineWeight(80, 0.0), 0.001)
        assertEquals(0.0, HapticWheelValuesEngine.combineWeight(-5, 0.0), 0.001)
    }

    @Test
    fun formatFractionDisplay_formatsExpectedTokens() {
        assertEquals(".00", HapticWheelValuesEngine.formatFractionDisplay(0.0))
        assertEquals(".25", HapticWheelValuesEngine.formatFractionDisplay(0.25))
        assertEquals(".50", HapticWheelValuesEngine.formatFractionDisplay(0.5))
        assertEquals(".75", HapticWheelValuesEngine.formatFractionDisplay(0.75))
    }

    @Test
    fun findClosestIndex_findsTargetOrDefaultsToZero() {
        val list = listOf("A", "B", "C", "D")
        assertEquals(2, HapticWheelValuesEngine.findClosestIndex(list, "C"))
        assertEquals(0, HapticWheelValuesEngine.findClosestIndex(list, "Z"))
    }
}
