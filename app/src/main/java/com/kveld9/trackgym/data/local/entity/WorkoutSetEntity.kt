package com.kveld9.trackgym.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.kveld9.trackgym.domain.model.SetType
import com.kveld9.trackgym.domain.model.WorkoutSet

@Entity(
    tableName = "workout_sets",
    foreignKeys = [
        ForeignKey(
            entity = WorkoutExerciseEntity::class,
            parentColumns = ["id"],
            childColumns = ["workoutExerciseId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["workoutExerciseId"])
    ]
)
data class WorkoutSetEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val workoutExerciseId: Long,
    val setNumber: Int,
    val setType: String = SetType.NORMAL.name,
    val weightKg: Double = 0.0,
    val reps: Int = 0,
    val rpe: Double? = null,
    val isCompleted: Boolean = false,
    val completedAt: Long? = null
) {
    fun toDomain(): WorkoutSet {
        return WorkoutSet(
            id = id,
            workoutExerciseId = workoutExerciseId,
            setNumber = setNumber,
            setType = runCatching { SetType.valueOf(setType) }.getOrDefault(SetType.NORMAL),
            weightKg = weightKg,
            reps = reps,
            rpe = rpe,
            isCompleted = isCompleted,
            completedAt = completedAt
        )
    }

    companion object {
        fun fromDomain(domain: WorkoutSet, workoutExerciseId: Long): WorkoutSetEntity {
            return WorkoutSetEntity(
                id = domain.id,
                workoutExerciseId = workoutExerciseId,
                setNumber = domain.setNumber,
                setType = domain.setType.name,
                weightKg = domain.weightKg,
                reps = domain.reps,
                rpe = domain.rpe,
                isCompleted = domain.isCompleted,
                completedAt = domain.completedAt
            )
        }
    }
}
