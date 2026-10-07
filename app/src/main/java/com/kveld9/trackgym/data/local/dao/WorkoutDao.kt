package com.kveld9.trackgym.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.kveld9.trackgym.data.local.entity.WorkoutEntity
import com.kveld9.trackgym.data.local.entity.WorkoutExerciseEntity
import com.kveld9.trackgym.data.local.entity.WorkoutSetEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkout(workout: WorkoutEntity): Long

    @Update
    suspend fun updateWorkout(workout: WorkoutEntity)

    @Query("UPDATE workouts SET notes = :notes WHERE id = :id")
    suspend fun updateWorkoutNotes(id: Long, notes: String)

    @Delete
    suspend fun deleteWorkout(workout: WorkoutEntity)

    @Query("DELETE FROM workouts WHERE id = :id")
    suspend fun deleteWorkoutById(id: Long)

    @Query("SELECT * FROM workouts WHERE id = :id LIMIT 1")
    suspend fun getWorkoutById(id: Long): WorkoutEntity?

    @Query("SELECT * FROM workouts WHERE isCompleted = 0 ORDER BY startedAt DESC LIMIT 1")
    suspend fun getActiveWorkout(): WorkoutEntity?

    @Query("SELECT * FROM workouts WHERE isCompleted = 1 ORDER BY completedAt DESC")
    fun getCompletedWorkouts(): Flow<List<WorkoutEntity>>

    @Query("SELECT * FROM workouts ORDER BY startedAt DESC")
    fun getAllWorkouts(): Flow<List<WorkoutEntity>>

    @Query("SELECT * FROM workouts ORDER BY startedAt ASC")
    suspend fun getAllWorkoutsSync(): List<WorkoutEntity>

    @Query("DELETE FROM workouts")
    suspend fun deleteAllWorkouts()

    @Query("DELETE FROM workout_exercises")
    suspend fun deleteAllWorkoutExercises()

    @Query("DELETE FROM workout_sets")
    suspend fun deleteAllWorkoutSets()

    // Workout Exercises
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkoutExercise(workoutExercise: WorkoutExerciseEntity): Long

    @Query("SELECT * FROM workout_exercises WHERE workoutId = :workoutId ORDER BY orderIndex ASC")
    suspend fun getWorkoutExercises(workoutId: Long): List<WorkoutExerciseEntity>

    @Query("DELETE FROM workout_exercises WHERE id = :id")
    suspend fun deleteWorkoutExercise(id: Long)

    @Query("UPDATE workout_exercises SET orderIndex = :newOrderIndex WHERE id = :workoutExerciseId")
    suspend fun updateExerciseOrder(workoutExerciseId: Long, newOrderIndex: Int)

    @Query("UPDATE workout_exercises SET exerciseId = :newExerciseId WHERE id = :workoutExerciseId")
    suspend fun swapExercise(workoutExerciseId: Long, newExerciseId: Long)

    @Query("UPDATE workout_exercises SET supersetGroupId = :supersetGroupId WHERE id = :workoutExerciseId")
    suspend fun updateExerciseSupersetGroup(workoutExerciseId: Long, supersetGroupId: String?)

    // Workout Sets
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkoutSet(workoutSet: WorkoutSetEntity): Long

    @Update
    suspend fun updateWorkoutSet(workoutSet: WorkoutSetEntity)

    @Query("DELETE FROM workout_sets WHERE id = :id")
    suspend fun deleteWorkoutSet(id: Long)

    @Query("DELETE FROM workout_sets WHERE workoutExerciseId = :workoutExerciseId")
    suspend fun deleteWorkoutSetsForExercise(workoutExerciseId: Long)

    @Query("SELECT * FROM workout_sets WHERE workoutExerciseId = :workoutExerciseId ORDER BY setNumber ASC")
    suspend fun getWorkoutSets(workoutExerciseId: Long): List<WorkoutSetEntity>

    @Query("""
        SELECT ws.* FROM workout_sets ws
        INNER JOIN workout_exercises we ON ws.workoutExerciseId = we.id
        WHERE we.exerciseId = :exerciseId AND ws.isCompleted = 1
        ORDER BY ws.completedAt DESC
    """)
    suspend fun getAllCompletedSetsForExercise(exerciseId: Long): List<WorkoutSetEntity>

    @Query("""
        SELECT we.* FROM workout_exercises we
        INNER JOIN workouts w ON we.workoutId = w.id
        WHERE we.exerciseId = :exerciseId AND w.isCompleted = 1 AND w.id != :currentWorkoutId
        ORDER BY w.completedAt DESC
        LIMIT 1
    """)
    suspend fun getLastCompletedWorkoutExercise(exerciseId: Long, currentWorkoutId: Long): WorkoutExerciseEntity?

    @Query("""
        SELECT we.* FROM workout_exercises we
        INNER JOIN workouts w ON we.workoutId = w.id
        WHERE we.exerciseId = :exerciseId AND w.isCompleted = 1 AND w.id != :currentWorkoutId
        ORDER BY w.completedAt DESC
        LIMIT :limit
    """)
    suspend fun getRecentCompletedWorkoutExercises(exerciseId: Long, currentWorkoutId: Long, limit: Int = 5): List<WorkoutExerciseEntity>
}
