package com.kveld9.trackgym.domain.model

data class Routine(
    val id: Long = 0,
    val folderId: Long? = null,
    val folderName: String? = null,
    val name: String,
    val notes: String = "",
    val exercises: List<RoutineExercise> = emptyList(),
    val orderIndex: Int = 0,
    val isArchived: Boolean = false,
    val isPeriodized: Boolean = false,
    val periodizedCycle: PeriodizedCycle? = null,
    val createdAt: Long = System.currentTimeMillis()
)
