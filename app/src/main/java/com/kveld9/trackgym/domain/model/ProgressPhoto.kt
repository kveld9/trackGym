package com.kveld9.trackgym.domain.model

import androidx.annotation.StringRes
import com.kveld9.trackgym.R

/**
 * Standard progress photo poses.
 */
enum class ProgressPhotoPose(
    @get:StringRes val displayNameRes: Int
) {
    FRONT(R.string.photo_pose_front),
    SIDE(R.string.photo_pose_side),
    BACK(R.string.photo_pose_back)
}

/**
 * Domain model representing a captured or imported progress picture.
 */
data class ProgressPhoto(
    val id: Long = 0,
    val filePath: String,
    val pose: ProgressPhotoPose,
    val capturedAt: Long = System.currentTimeMillis(),
    val notes: String = ""
)
