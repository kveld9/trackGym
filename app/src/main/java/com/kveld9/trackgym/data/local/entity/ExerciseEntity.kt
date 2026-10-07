package com.kveld9.trackgym.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kveld9.trackgym.domain.calculator.MuscleAnatomyRegistry
import com.kveld9.trackgym.domain.model.BodyMuscle
import com.kveld9.trackgym.domain.model.Exercise
import com.kveld9.trackgym.domain.model.ExerciseCategory
import com.kveld9.trackgym.domain.model.MechanicsType
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
    val restDurationSeconds: Int? = null,
    val warmupRampProtocol: String? = null,
    val autoProgressionRule: String? = null,
    val primaryMuscle: String? = null,
    val secondaryMuscles: String? = null,
    val mechanics: String = "COMPOUND",
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
            restDurationSeconds = restDurationSeconds,
            warmupRampProtocol = warmupRampProtocol,
            autoProgressionRule = autoProgressionRule,
            primaryMuscle = primaryMuscle?.let { runCatching { BodyMuscle.valueOf(it) }.getOrNull() },
            secondaryMuscles = MuscleAnatomyRegistry.deserializeSecondaryMuscles(secondaryMuscles),
            mechanics = MechanicsType.fromString(mechanics),
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
                restDurationSeconds = domain.restDurationSeconds,
                warmupRampProtocol = domain.warmupRampProtocol,
                autoProgressionRule = domain.autoProgressionRule,
                primaryMuscle = domain.primaryMuscle?.name,
                secondaryMuscles = MuscleAnatomyRegistry.serializeSecondaryMuscles(domain.secondaryMuscles).ifBlank { null },
                mechanics = domain.mechanics.name,
                isCustom = domain.isCustom,
                createdAt = domain.createdAt
            )
        }
    }
}
