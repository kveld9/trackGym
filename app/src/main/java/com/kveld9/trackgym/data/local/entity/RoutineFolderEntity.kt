package com.kveld9.trackgym.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kveld9.trackgym.domain.model.RoutineFolder

@Entity(tableName = "routine_folders")
data class RoutineFolderEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val orderIndex: Int = 0,
    val createdAt: Long = System.currentTimeMillis()
) {
    fun toDomain(): RoutineFolder = RoutineFolder(
        id = id,
        name = name,
        orderIndex = orderIndex,
        createdAt = createdAt
    )

    companion object {
        fun fromDomain(domain: RoutineFolder): RoutineFolderEntity = RoutineFolderEntity(
            id = domain.id,
            name = domain.name,
            orderIndex = domain.orderIndex,
            createdAt = domain.createdAt
        )
    }
}
