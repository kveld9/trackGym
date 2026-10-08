package com.kveld9.trackgym.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.kveld9.trackgym.domain.model.ProgressPhoto
import com.kveld9.trackgym.domain.model.ProgressPhotoPose

@Entity(
    tableName = "progress_photos",
    indices = [
        Index("pose"),
        Index("capturedAt")
    ]
)
data class ProgressPhotoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val filePath: String,
    val pose: String,
    val capturedAt: Long = System.currentTimeMillis(),
    val notes: String = ""
) {
    fun toDomain(): ProgressPhoto {
        val mappedPose = try {
            ProgressPhotoPose.valueOf(pose)
        } catch (_: Exception) {
            ProgressPhotoPose.FRONT
        }
        return ProgressPhoto(
            id = id,
            filePath = filePath,
            pose = mappedPose,
            capturedAt = capturedAt,
            notes = notes
        )
    }

    companion object {
        fun fromDomain(photo: ProgressPhoto): ProgressPhotoEntity {
            return ProgressPhotoEntity(
                id = photo.id,
                filePath = photo.filePath,
                pose = photo.pose.name,
                capturedAt = photo.capturedAt,
                notes = photo.notes
            )
        }
    }
}
