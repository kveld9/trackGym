package com.kveld9.trackgym.domain.util

/**
 * Utility codec for serializing, deserializing, and manipulating comma-separated
 * user-defined category tags on exercises.
 */
object CustomCategoryCodec {

    fun serialize(categories: List<String>): String {
        return categories
            .map { it.replace(",", " ").trim() }
            .filter { it.isNotBlank() }
            .distinctBy { it.lowercase() }
            .joinToString(",")
    }

    fun deserialize(raw: String?): List<String> {
        if (raw.isNullOrBlank()) return emptyList()
        return raw.split(",")
            .map { it.trim() }
            .filter { it.isNotBlank() }
            .distinctBy { it.lowercase() }
    }

    fun rename(raw: String?, oldName: String, newName: String): String {
        val trimmedOld = oldName.replace(",", " ").trim()
        val trimmedNew = newName.replace(",", " ").trim()
        if (trimmedOld.isBlank() || trimmedNew.isBlank()) return raw.orEmpty()

        val list = deserialize(raw).map { current ->
            if (current.equals(trimmedOld, ignoreCase = true)) trimmedNew else current
        }
        return serialize(list)
    }

    fun remove(raw: String?, target: String): String {
        val trimmedTarget = target.replace(",", " ").trim()
        if (trimmedTarget.isBlank()) return raw.orEmpty()

        val list = deserialize(raw).filterNot { current ->
            current.equals(trimmedTarget, ignoreCase = true)
        }
        return serialize(list)
    }
}
