package com.kveld9.trackgym.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.kveld9.trackgym.data.local.entity.CustomExerciseCategoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CustomExerciseCategoryDao {

    @Query("SELECT * FROM custom_exercise_categories ORDER BY name ASC")
    fun getAllCategories(): Flow<List<CustomExerciseCategoryEntity>>

    @Query("SELECT * FROM custom_exercise_categories ORDER BY name ASC")
    suspend fun getAllCategoriesSync(): List<CustomExerciseCategoryEntity>

    @Query("SELECT * FROM custom_exercise_categories WHERE id = :id LIMIT 1")
    suspend fun getCategoryById(id: Long): CustomExerciseCategoryEntity?

    @Query("SELECT * FROM custom_exercise_categories WHERE LOWER(name) = LOWER(:name) LIMIT 1")
    suspend fun getCategoryByName(name: String): CustomExerciseCategoryEntity?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCategory(category: CustomExerciseCategoryEntity): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(categories: List<CustomExerciseCategoryEntity>)

    @Update
    suspend fun updateCategory(category: CustomExerciseCategoryEntity)

    @Query("DELETE FROM custom_exercise_categories WHERE id = :id")
    suspend fun deleteCategoryById(id: Long)

    @Query("DELETE FROM custom_exercise_categories")
    suspend fun deleteAllCategories()
}
