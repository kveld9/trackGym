package com.kveld9.trackgym.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kveld9.trackgym.domain.model.Exercise
import com.kveld9.trackgym.domain.model.ExerciseCategory
import com.kveld9.trackgym.domain.model.MuscleGroup

import androidx.room.Index

@Entity(
    tableName = "exercises",
    indices = [Index(value = ["name"], unique = true)]
)
data class ExerciseEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val muscleGroup: String,
    val category: String,
    val notes: String = "",
    val isCustom: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
) {
    fun toDomain(): Exercise {
        return Exercise(
            id = id,
            name = name,
            muscleGroup = MuscleGroup.fromString(muscleGroup),
            category = ExerciseCategory.fromString(category),
            notes = notes,
            isCustom = isCustom,
            createdAt = createdAt
        )
    }

    companion object {
        fun fromDomain(domain: Exercise): ExerciseEntity {
            return ExerciseEntity(
                id = domain.id,
                name = domain.name,
                muscleGroup = domain.muscleGroup.name,
                category = domain.category.name,
                notes = domain.notes,
                isCustom = domain.isCustom,
                createdAt = domain.createdAt
            )
        }
    }
}
