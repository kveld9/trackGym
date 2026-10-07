package com.kveld9.trackgym.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.kveld9.trackgym.data.local.entity.RoutineEntity
import com.kveld9.trackgym.data.local.entity.RoutineExerciseEntity
import com.kveld9.trackgym.data.local.entity.RoutineFolderEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RoutineDao {

    // Folders
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFolder(folder: RoutineFolderEntity): Long

    @Query("SELECT * FROM routine_folders ORDER BY orderIndex ASC, name ASC")
    fun getAllFolders(): Flow<List<RoutineFolderEntity>>

    @Query("SELECT * FROM routine_folders WHERE id = :id")
    suspend fun getFolderById(id: Long): RoutineFolderEntity?

    @Query("DELETE FROM routine_folders WHERE id = :id")
    suspend fun deleteFolder(id: Long)

    @Query("UPDATE routine_folders SET orderIndex = :orderIndex WHERE id = :id")
    suspend fun updateFolderOrder(id: Long, orderIndex: Int)

    // Routines
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRoutine(routine: RoutineEntity): Long

    @Update
    suspend fun updateRoutine(routine: RoutineEntity)

    @Query("SELECT * FROM routines ORDER BY orderIndex ASC, createdAt DESC")
    fun getAllRoutines(): Flow<List<RoutineEntity>>

    @Query("SELECT * FROM routines WHERE id = :id")
    suspend fun getRoutineById(id: Long): RoutineEntity?

    @Query("DELETE FROM routines WHERE id = :id")
    suspend fun deleteRoutine(id: Long)

    @Query("UPDATE routines SET orderIndex = :orderIndex WHERE id = :id")
    suspend fun updateRoutineOrder(id: Long, orderIndex: Int)

    @Query("UPDATE routines SET isArchived = :isArchived WHERE id = :id")
    suspend fun updateRoutineArchived(id: Long, isArchived: Boolean)

    @Query("UPDATE routines SET isPeriodized = :isPeriodized, periodizedCycleData = :cycleData WHERE id = :id")
    suspend fun updateRoutinePeriodization(id: Long, isPeriodized: Boolean, cycleData: String?)

    @Query("UPDATE routines SET periodizedCycleData = :cycleData WHERE id = :id")
    suspend fun updateRoutineCycleData(id: Long, cycleData: String)

    // Routine Exercises
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRoutineExercises(exercises: List<RoutineExerciseEntity>)

    @Query("SELECT * FROM routine_exercises WHERE routineId = :routineId ORDER BY orderIndex ASC")
    suspend fun getExercisesForRoutine(routineId: Long): List<RoutineExerciseEntity>

    @Query("DELETE FROM routine_exercises WHERE routineId = :routineId")
    suspend fun deleteExercisesForRoutine(routineId: Long)
}
