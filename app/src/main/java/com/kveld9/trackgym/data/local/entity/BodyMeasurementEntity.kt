package com.kveld9.trackgym.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.kveld9.trackgym.domain.model.BodyMeasurement
import com.kveld9.trackgym.domain.model.BodyMeasurementType

@Entity(
    tableName = "body_measurements",
    indices = [
        Index("type"),
        Index("measuredAt")
    ]
)
data class BodyMeasurementEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val type: String,
    val value: Double,
    val measuredAt: Long,
    val notes: String = ""
) {
    fun toDomain(): BodyMeasurement {
        val parsedType = try {
            BodyMeasurementType.valueOf(type)
        } catch (_: Exception) {
            BodyMeasurementType.WEIGHT
        }
        return BodyMeasurement(
            id = id,
            type = parsedType,
            value = value,
            measuredAt = measuredAt,
            notes = notes
        )
    }

    companion object {
        fun fromDomain(domain: BodyMeasurement): BodyMeasurementEntity {
            return BodyMeasurementEntity(
                id = domain.id,
                type = domain.type.name,
                value = domain.value,
                measuredAt = domain.measuredAt,
                notes = domain.notes
            )
        }
    }
}
