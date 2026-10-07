package com.kveld9.trackgym.domain.calculator

import com.kveld9.trackgym.R
import com.kveld9.trackgym.domain.model.DifficultyLevel
import com.kveld9.trackgym.domain.model.Exercise
import com.kveld9.trackgym.domain.model.ExerciseCategory
import com.kveld9.trackgym.domain.model.ForceType
import com.kveld9.trackgym.domain.model.MechanicsType
import com.kveld9.trackgym.domain.model.MuscleGroup
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class RoutineBiomechanicalBalanceEngineTest {

    private fun createExercise(
        name: String,
        force: ForceType,
        level: DifficultyLevel,
        mechanics: MechanicsType = MechanicsType.COMPOUND
    ): Exercise {
        return Exercise(
            name = name,
            muscleGroup = MuscleGroup.CHEST,
            category = ExerciseCategory.BARBELL,
            force = force,
            level = level,
            mechanics = mechanics
        )
    }

    @Test
    fun calculate_emptyList_returnsZeroNeutralBalance() {
        val balance = RoutineBiomechanicalBalanceEngine.calculate(emptyList())

        assertEquals(0, balance.totalExercises)
        assertEquals(0, balance.pushCount)
        assertEquals(0, balance.pullCount)
        assertEquals(0, balance.staticCount)
        assertNull(balance.pushPullRatio)
        assertTrue(balance.isBalanced)
        assertEquals(R.string.routine_balance_status_neutral, balance.balanceStatusRes)
    }

    @Test
    fun calculate_balancedPushPullRoutine_returnsBalanced() {
        val exercises = listOf(
            createExercise("Bench Press", ForceType.PUSH, DifficultyLevel.INTERMEDIATE),
            createExercise("Overhead Press", ForceType.PUSH, DifficultyLevel.INTERMEDIATE),
            createExercise("Tricep Extension", ForceType.PUSH, DifficultyLevel.BEGINNER, MechanicsType.ISOLATION),
            createExercise("Barbell Row", ForceType.PULL, DifficultyLevel.INTERMEDIATE),
            createExercise("Lat Pulldown", ForceType.PULL, DifficultyLevel.BEGINNER),
            createExercise("Bicep Curl", ForceType.PULL, DifficultyLevel.BEGINNER, MechanicsType.ISOLATION)
        )

        val balance = RoutineBiomechanicalBalanceEngine.calculate(exercises)

        assertEquals(6, balance.totalExercises)
        assertEquals(3, balance.pushCount)
        assertEquals(3, balance.pullCount)
        assertEquals(0, balance.staticCount)
        assertEquals(1.0, balance.pushPullRatio!!, 0.01)
        assertTrue(balance.isBalanced)
        assertEquals(R.string.routine_balance_status_balanced, balance.balanceStatusRes)
        assertEquals(3, balance.beginnerCount)
        assertEquals(3, balance.intermediateCount)
        assertEquals(0, balance.expertCount)
        assertEquals(4, balance.compoundCount)
        assertEquals(2, balance.isolationCount)
    }

    @Test
    fun calculate_pushDominantRoutine_returnsPushDominant() {
        val exercises = listOf(
            createExercise("Bench Press", ForceType.PUSH, DifficultyLevel.INTERMEDIATE),
            createExercise("Incline Dumbbell Press", ForceType.PUSH, DifficultyLevel.INTERMEDIATE),
            createExercise("Dips", ForceType.PUSH, DifficultyLevel.INTERMEDIATE),
            createExercise("Tricep Pushdown", ForceType.PUSH, DifficultyLevel.BEGINNER, MechanicsType.ISOLATION),
            createExercise("Face Pull", ForceType.PULL, DifficultyLevel.BEGINNER, MechanicsType.ISOLATION)
        )

        val balance = RoutineBiomechanicalBalanceEngine.calculate(exercises)

        assertEquals(5, balance.totalExercises)
        assertEquals(4, balance.pushCount)
        assertEquals(1, balance.pullCount)
        assertEquals(4.0, balance.pushPullRatio!!, 0.01)
        assertFalse(balance.isBalanced)
        assertEquals(R.string.routine_balance_status_push_dominant, balance.balanceStatusRes)
    }

    @Test
    fun calculate_pullDominantRoutine_returnsPullDominant() {
        val exercises = listOf(
            createExercise("Deadlift", ForceType.PULL, DifficultyLevel.INTERMEDIATE),
            createExercise("Barbell Row", ForceType.PULL, DifficultyLevel.INTERMEDIATE),
            createExercise("Lat Pulldown", ForceType.PULL, DifficultyLevel.BEGINNER),
            createExercise("Bicep Curl", ForceType.PULL, DifficultyLevel.BEGINNER, MechanicsType.ISOLATION)
        )

        val balance = RoutineBiomechanicalBalanceEngine.calculate(exercises)

        assertEquals(4, balance.totalExercises)
        assertEquals(0, balance.pushCount)
        assertEquals(4, balance.pullCount)
        assertFalse(balance.isBalanced)
        assertEquals(R.string.routine_balance_status_pull_dominant, balance.balanceStatusRes)
    }

    @Test
    fun calculate_staticDominantRoutine_returnsStaticDominant() {
        val exercises = listOf(
            createExercise("Plank", ForceType.STATIC, DifficultyLevel.BEGINNER, MechanicsType.ISOLATION),
            createExercise("Side Plank", ForceType.STATIC, DifficultyLevel.BEGINNER, MechanicsType.ISOLATION),
            createExercise("L-Sit", ForceType.STATIC, DifficultyLevel.EXPERT, MechanicsType.COMPOUND),
            createExercise("Push-up", ForceType.PUSH, DifficultyLevel.BEGINNER, MechanicsType.COMPOUND)
        )

        val balance = RoutineBiomechanicalBalanceEngine.calculate(exercises)

        assertEquals(4, balance.totalExercises)
        assertEquals(3, balance.staticCount)
        assertEquals(1, balance.pushCount)
        assertEquals(0, balance.pullCount)
        assertFalse(balance.isBalanced)
        assertEquals(R.string.routine_balance_status_static_dominant, balance.balanceStatusRes)
        assertEquals(1, balance.expertCount)
    }

    @Test
    fun calculate_threeExercisesWithOneStatic_returnsBalancedNotStaticDominant() {
        val exercises = listOf(
            createExercise("Bench Press", ForceType.PUSH, DifficultyLevel.INTERMEDIATE),
            createExercise("Barbell Row", ForceType.PULL, DifficultyLevel.INTERMEDIATE),
            createExercise("Plank", ForceType.STATIC, DifficultyLevel.BEGINNER, MechanicsType.ISOLATION)
        )

        val balance = RoutineBiomechanicalBalanceEngine.calculate(exercises)

        assertEquals(3, balance.totalExercises)
        assertEquals(1, balance.pushCount)
        assertEquals(1, balance.pullCount)
        assertEquals(1, balance.staticCount)
        assertEquals(1.0, balance.pushPullRatio!!, 0.01)
        assertTrue(balance.isBalanced)
        assertEquals(R.string.routine_balance_status_balanced, balance.balanceStatusRes)
    }
}
