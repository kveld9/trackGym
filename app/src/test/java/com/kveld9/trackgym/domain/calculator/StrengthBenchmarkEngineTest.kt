package com.kveld9.trackgym.domain.calculator

import com.kveld9.trackgym.domain.model.BiologicalSex
import com.kveld9.trackgym.domain.model.CoreLift
import com.kveld9.trackgym.domain.model.StrengthLevel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class StrengthBenchmarkEngineTest {

    @Test
    fun testMatchCoreLift() {
        assertEquals(CoreLift.BENCH_PRESS, StrengthBenchmarkEngine.matchCoreLift("Barbell Bench Press"))
        assertEquals(CoreLift.BENCH_PRESS, StrengthBenchmarkEngine.matchCoreLift("Press de Banca con barra"))
        assertEquals(CoreLift.SQUAT, StrengthBenchmarkEngine.matchCoreLift("Barbell Back Squat"))
        assertEquals(CoreLift.SQUAT, StrengthBenchmarkEngine.matchCoreLift("Sentadilla"))
        assertEquals(CoreLift.DEADLIFT, StrengthBenchmarkEngine.matchCoreLift("Conventional Deadlift"))
        assertEquals(CoreLift.DEADLIFT, StrengthBenchmarkEngine.matchCoreLift("Peso Muerto"))
        assertEquals(CoreLift.OVERHEAD_PRESS, StrengthBenchmarkEngine.matchCoreLift("Overhead Barbell Press"))
        assertEquals(CoreLift.OVERHEAD_PRESS, StrengthBenchmarkEngine.matchCoreLift("Press Militar con barra"))
        assertNull(StrengthBenchmarkEngine.matchCoreLift("Bicep Curl"))
        assertNull(StrengthBenchmarkEngine.matchCoreLift("Lat Pulldown"))
    }

    @Test
    fun testAgeFactor() {
        assertEquals(1.0, StrengthBenchmarkEngine.getAgeFactor(25), 0.001)
        assertEquals(1.0, StrengthBenchmarkEngine.getAgeFactor(35), 0.001)
        assertTrue(StrengthBenchmarkEngine.getAgeFactor(50) < 1.0)
        assertTrue(StrengthBenchmarkEngine.getAgeFactor(70) < StrengthBenchmarkEngine.getAgeFactor(50))
        assertTrue(StrengthBenchmarkEngine.getAgeFactor(15) < 1.0)
    }

    @Test
    fun testMaleBenchPressEvaluation() {
        // Male, 80kg bodyweight, age 30
        // Standards male bench: [0.50, 0.75, 1.00, 1.50, 1.90] -> [40kg, 60kg, 80kg, 120kg, 152kg]
        
        // 1. Beginner (< 40kg)
        val resultBeginner = StrengthBenchmarkEngine.evaluate(
            lift = CoreLift.BENCH_PRESS,
            exerciseName = "Barbell Bench Press",
            user1RMKg = 30.0,
            bodyWeightKg = 80.0,
            sex = BiologicalSex.MALE,
            age = 30
        )
        assertEquals(StrengthLevel.BEGINNER, resultBeginner.level)
        assertTrue(resultBeginner.percentile < 20.0)
        assertEquals(40.0, resultBeginner.nextLevelTargetKg!!, 0.1)

        // 2. Novice (between 60kg and 80kg, e.g. 70kg -> ratio 0.875)
        val resultNovice = StrengthBenchmarkEngine.evaluate(
            lift = CoreLift.BENCH_PRESS,
            exerciseName = "Barbell Bench Press",
            user1RMKg = 70.0,
            bodyWeightKg = 80.0,
            sex = BiologicalSex.MALE,
            age = 30
        )
        assertEquals(StrengthLevel.NOVICE, resultNovice.level)
        assertTrue(resultNovice.percentile in 30.0..55.0)
        assertEquals(80.0, resultNovice.nextLevelTargetKg!!, 0.1)

        // 3. Intermediate (between 80kg and 120kg, e.g. 100kg -> ratio 1.25)
        val resultIntermediate = StrengthBenchmarkEngine.evaluate(
            lift = CoreLift.BENCH_PRESS,
            exerciseName = "Barbell Bench Press",
            user1RMKg = 100.0,
            bodyWeightKg = 80.0,
            sex = BiologicalSex.MALE,
            age = 30
        )
        assertEquals(StrengthLevel.INTERMEDIATE, resultIntermediate.level)
        assertTrue(resultIntermediate.percentile in 55.0..85.0)
        assertEquals(120.0, resultIntermediate.nextLevelTargetKg!!, 0.1)

        // 4. Advanced (between 120kg and 152kg, e.g. 130kg)
        val resultAdvanced = StrengthBenchmarkEngine.evaluate(
            lift = CoreLift.BENCH_PRESS,
            exerciseName = "Barbell Bench Press",
            user1RMKg = 130.0,
            bodyWeightKg = 80.0,
            sex = BiologicalSex.MALE,
            age = 30
        )
        assertEquals(StrengthLevel.ADVANCED, resultAdvanced.level)
        assertTrue(resultAdvanced.percentile in 85.0..95.0)

        // 5. Elite (> 152kg, e.g. 160kg)
        val resultElite = StrengthBenchmarkEngine.evaluate(
            lift = CoreLift.BENCH_PRESS,
            exerciseName = "Barbell Bench Press",
            user1RMKg = 160.0,
            bodyWeightKg = 80.0,
            sex = BiologicalSex.MALE,
            age = 30
        )
        assertEquals(StrengthLevel.ELITE, resultElite.level)
        assertTrue(resultElite.percentile >= 95.0)
        assertNull(resultElite.nextLevelTargetKg)
    }

    @Test
    fun testFemaleDeadliftEvaluation() {
        // Female, 60kg bodyweight, age 28
        // Female deadlift standards: [0.60, 0.95, 1.20, 1.60, 2.05] -> [36kg, 57kg, 72kg, 96kg, 123kg]
        val result = StrengthBenchmarkEngine.evaluate(
            lift = CoreLift.DEADLIFT,
            exerciseName = "Conventional Deadlift",
            user1RMKg = 80.0,
            bodyWeightKg = 60.0,
            sex = BiologicalSex.FEMALE,
            age = 28
        )
        assertEquals(StrengthLevel.INTERMEDIATE, result.level)
        assertTrue(result.percentile in 55.0..85.0)
        assertEquals(96.0, result.nextLevelTargetKg!!, 0.1)
    }

    @Test
    fun testZero1RMBoundary() {
        val result = StrengthBenchmarkEngine.evaluate(
            lift = CoreLift.SQUAT,
            exerciseName = "Barbell Back Squat",
            user1RMKg = 0.0,
            bodyWeightKg = 75.0,
            sex = BiologicalSex.MALE,
            age = 25
        )
        assertEquals(StrengthLevel.BEGINNER, result.level)
        assertEquals(0.0, result.ratio, 0.01)
        assertEquals(false, result.hasPersonalRecord)
        assertNotNull(result.nextLevelTargetKg)
    }
}
