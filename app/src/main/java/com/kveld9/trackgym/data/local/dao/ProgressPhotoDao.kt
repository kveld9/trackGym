package com.kveld9.trackgym.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kveld9.trackgym.data.local.entity.ProgressPhotoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProgressPhotoDao {

    @Query("SELECT * FROM progress_photos ORDER BY capturedAt DESC")
    fun getAllPhotos(): Flow<List<ProgressPhotoEntity>>

    @Query("SELECT * FROM progress_photos WHERE pose = :pose ORDER BY capturedAt DESC")
    fun getPhotosByPose(pose: String): Flow<List<ProgressPhotoEntity>>

    @Query("SELECT * FROM progress_photos WHERE id = :id LIMIT 1")
    suspend fun getPhotoById(id: Long): ProgressPhotoEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhoto(entity: ProgressPhotoEntity): Long

    @Query("DELETE FROM progress_photos WHERE id = :id")
    suspend fun deletePhoto(id: Long): Int
}
