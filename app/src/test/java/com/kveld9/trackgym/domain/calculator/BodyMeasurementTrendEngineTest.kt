package com.kveld9.trackgym.domain.calculator

import com.kveld9.trackgym.domain.model.BodyMeasurement
import com.kveld9.trackgym.domain.model.BodyMeasurementType
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class BodyMeasurementTrendEngineTest {

    @Test
    fun testEmptyMeasurements() {
        val trend = BodyMeasurementTrendEngine.calculateTrend(emptyList())
        assertTrue(trend.points.isEmpty())
        assertEquals(0.0, trend.currentValue, 0.001)
        assertEquals(0.0, trend.netDelta, 0.001)
    }

    @Test
    fun testSingleMeasurement() {
        val list = listOf(
            BodyMeasurement(id = 1, type = BodyMeasurementType.WEIGHT, value = 80.0, measuredAt = 1000L)
        )
        val trend = BodyMeasurementTrendEngine.calculateTrend(list)
        assertEquals(1, trend.points.size)
        assertEquals(80.0, trend.currentValue, 0.001)
        assertEquals(80.0, trend.points.first().movingAverage, 0.001)
        assertEquals(0.0, trend.netDelta, 0.001)
    }

    @Test
    fun testMovingAverageAndDelta() {
        // Values: 80, 81, 79 (window = 3)
        // Point 1: val 80, MA = 80.0
        // Point 2: val 81, MA = (80+81)/2 = 80.5
        // Point 3: val 79, MA = (80+81+79)/3 = 80.0
        val list = listOf(
            BodyMeasurement(id = 1, type = BodyMeasurementType.WEIGHT, value = 80.0, measuredAt = 1000L),
            BodyMeasurement(id = 2, type = BodyMeasurementType.WEIGHT, value = 81.0, measuredAt = 2000L),
            BodyMeasurement(id = 3, type = BodyMeasurementType.WEIGHT, value = 79.0, measuredAt = 3000L)
        )
        val trend = BodyMeasurementTrendEngine.calculateTrend(list, windowSize = 3)
        assertEquals(3, trend.points.size)
        assertEquals(80.0, trend.points[0].movingAverage, 0.01)
        assertEquals(80.5, trend.points[1].movingAverage, 0.01)
        assertEquals(80.0, trend.points[2].movingAverage, 0.01)
        assertEquals(79.0, trend.currentValue, 0.01)
        assertEquals(-1.0, trend.netDelta, 0.01) // 79.0 - 80.0
        assertEquals(-1.25, trend.percentDelta, 0.1) // -1.0 / 80.0 * 100 = -1.25%
        assertEquals(79.0, trend.minValue, 0.01)
        assertEquals(81.0, trend.maxValue, 0.01)
    }
}
