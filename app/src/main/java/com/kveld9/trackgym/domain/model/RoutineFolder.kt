package com.kveld9.trackgym.domain.model

data class RoutineFolder(
    val id: Long = 0,
    val name: String,
    val orderIndex: Int = 0,
    val createdAt: Long = System.currentTimeMillis()
)
