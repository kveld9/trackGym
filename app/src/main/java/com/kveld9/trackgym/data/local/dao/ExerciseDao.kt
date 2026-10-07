package com.kveld9.trackgym.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kveld9.trackgym.data.local.entity.ExerciseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseDao {

    @Query("SELECT * FROM exercises ORDER BY name ASC")
    fun getAllExercises(): Flow<List<ExerciseEntity>>

    @Query("SELECT * FROM exercises ORDER BY name ASC")
    suspend fun getAllExercisesSync(): List<ExerciseEntity>

    @Query("SELECT * FROM exercises WHERE id = :id LIMIT 1")
    suspend fun getExerciseById(id: Long): ExerciseEntity?

    @Query("SELECT * FROM exercises WHERE LOWER(name) = LOWER(:name) LIMIT 1")
    suspend fun getExerciseByName(name: String): ExerciseEntity?

    @Query("SELECT * FROM exercises WHERE muscleGroup = :muscleGroup ORDER BY name ASC")
    fun getExercisesByMuscleGroup(muscleGroup: String): Flow<List<ExerciseEntity>>

    @Query("SELECT * FROM exercises WHERE name LIKE '%' || :query || '%' ORDER BY name ASC")
    fun searchExercises(query: String): Flow<List<ExerciseEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExercise(exercise: ExerciseEntity): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(exercises: List<ExerciseEntity>)

    @Delete
    suspend fun deleteExercise(exercise: ExerciseEntity)

    @Query("DELETE FROM exercises")
    suspend fun deleteAllExercises()

    @Query("UPDATE exercises SET notes = :notes WHERE id = :id")
    suspend fun updateExerciseNotes(id: Long, notes: String)

    @Query("UPDATE exercises SET restDurationSeconds = :restSeconds WHERE id = :id")
    suspend fun updateExerciseRestDuration(id: Long, restSeconds: Int?)

    @Query("UPDATE exercises SET warmupRampProtocol = :protocol WHERE id = :id")
    suspend fun updateExerciseWarmupProtocol(id: Long, protocol: String?)

    @Query("UPDATE exercises SET autoProgressionRule = :rule WHERE id = :id")
    suspend fun updateExerciseAutoProgressionRule(id: Long, rule: String?)

    @Query("SELECT COUNT(*) FROM exercises")
    suspend fun countExercises(): Int
}
