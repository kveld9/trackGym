package com.kveld9.trackgym.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.kveld9.trackgym.domain.model.CustomExerciseCategory

@Entity(
    tableName = "custom_exercise_categories",
    indices = [Index(value = ["name"], unique = true)]
)
data class CustomExerciseCategoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val createdAt: Long = System.currentTimeMillis()
) {
    fun toDomain(): CustomExerciseCategory {
        return CustomExerciseCategory(
            id = id,
            name = name,
            createdAt = createdAt
        )
    }

    companion object {
        fun fromDomain(domain: CustomExerciseCategory): CustomExerciseCategoryEntity {
            return CustomExerciseCategoryEntity(
                id = domain.id,
                name = domain.name.trim(),
                createdAt = domain.createdAt
            )
        }
    }
}
