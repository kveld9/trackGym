package com.kveld9.trackgym.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kveld9.trackgym.data.local.entity.PersonalRecordEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonalRecordDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecord(record: PersonalRecordEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(records: List<PersonalRecordEntity>)

    @Query("SELECT * FROM personal_records WHERE exerciseId = :exerciseId ORDER BY achievedAt DESC")
    fun getRecordsForExercise(exerciseId: Long): Flow<List<PersonalRecordEntity>>

    @Query("SELECT * FROM personal_records ORDER BY achievedAt DESC")
    fun getAllRecords(): Flow<List<PersonalRecordEntity>>

    @Query("SELECT * FROM personal_records ORDER BY achievedAt ASC")
    suspend fun getAllRecordsSync(): List<PersonalRecordEntity>

    @Query("DELETE FROM personal_records")
    suspend fun deleteAllRecords()

    @Query("SELECT * FROM personal_records WHERE workoutId = :workoutId")
    suspend fun getRecordsForWorkout(workoutId: Long): List<PersonalRecordEntity>

    @Query("DELETE FROM personal_records WHERE workoutId = :workoutId")
    suspend fun deleteRecordsForWorkout(workoutId: Long)

    @Query("SELECT * FROM personal_records WHERE exerciseId = :exerciseId AND recordType = :recordType ORDER BY recordValue DESC LIMIT 1")
    suspend fun getBestRecord(exerciseId: Long, recordType: String): PersonalRecordEntity?
}
