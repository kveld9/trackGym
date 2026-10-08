package com.kveld9.trackgym.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kveld9.trackgym.data.local.entity.BodyMeasurementEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BodyMeasurementDao {

    @Query("SELECT * FROM body_measurements ORDER BY measuredAt DESC")
    fun getAllMeasurements(): Flow<List<BodyMeasurementEntity>>

    @Query("SELECT * FROM body_measurements WHERE type = :type ORDER BY measuredAt ASC")
    fun getMeasurementsByTypeAsc(type: String): Flow<List<BodyMeasurementEntity>>

    @Query("SELECT * FROM body_measurements WHERE type = :type ORDER BY measuredAt DESC LIMIT 1")
    suspend fun getLatestMeasurementByType(type: String): BodyMeasurementEntity?

    @Query("SELECT * FROM body_measurements ORDER BY measuredAt ASC")
    suspend fun getAllMeasurementsSync(): List<BodyMeasurementEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeasurement(entity: BodyMeasurementEntity): Long

    @Query("DELETE FROM body_measurements WHERE id = :id")
    suspend fun deleteMeasurement(id: Long)

    @Query("DELETE FROM body_measurements")
    suspend fun clearAllMeasurements()
}
