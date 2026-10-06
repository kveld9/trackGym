package com.kveld9.trackgym.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.kveld9.trackgym.domain.model.PersonalRecord
import com.kveld9.trackgym.domain.model.RecordType

@Entity(
    tableName = "personal_records",
    foreignKeys = [
        ForeignKey(
            entity = ExerciseEntity::class,
            parentColumns = ["id"],
            childColumns = ["exerciseId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["exerciseId"])
    ]
)
data class PersonalRecordEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val exerciseId: Long,
    val recordType: String,
    val recordValue: Double,
    val weightKg: Double,
    val reps: Int,
    val achievedAt: Long,
    val workoutId: Long,
    val description: String
) {
    fun toDomain(): PersonalRecord {
        return PersonalRecord(
            id = id,
            exerciseId = exerciseId,
            recordType = runCatching { RecordType.valueOf(recordType) }.getOrDefault(RecordType.MAX_WEIGHT),
            recordValue = recordValue,
            weightKg = weightKg,
            reps = reps,
            achievedAt = achievedAt,
            workoutId = workoutId,
            description = description
        )
    }

    companion object {
        fun fromDomain(domain: PersonalRecord): PersonalRecordEntity {
            return PersonalRecordEntity(
                id = domain.id,
                exerciseId = domain.exerciseId,
                recordType = domain.recordType.name,
                recordValue = domain.recordValue,
                weightKg = domain.weightKg,
                reps = domain.reps,
                achievedAt = domain.achievedAt,
                workoutId = domain.workoutId,
                description = domain.description
            )
        }
    }
}
