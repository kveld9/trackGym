package com.kveld9.trackgym.domain.model

data class MuscleGroupVolume(
    val muscleGroup: MuscleGroup,
    val setsCount: Int,
    val percentage: Float = 0f
)

data class MuscleHeatmapState(
    val muscleSets: Map<BodyMuscle, Int> = emptyMap(),
    val muscleGroupVolumes: List<MuscleGroupVolume> = emptyList(),
    val totalSets: Int = 0
) {
    fun getIntensity(muscle: BodyMuscle): Float {
        val sets = muscleSets[muscle] ?: return 0f
        return when {
            sets <= 0 -> 0f
            sets in 1..2 -> 0.50f
            sets in 3..5 -> 0.75f
            else -> 1.0f
        }
    }

    val isEmpty: Boolean
        get() = totalSets == 0 || muscleSets.isEmpty()
}
