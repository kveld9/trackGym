package com.kveld9.trackgym.domain.util

import java.util.Locale

object DurationFormatter {
    private const val SECONDS_PER_HOUR = 3600
    private const val SECONDS_PER_MINUTE = 60

    fun formatSecondsToMmSs(totalSeconds: Int): String {
        val safeSeconds = totalSeconds.coerceAtLeast(0)
        val minutes = safeSeconds / SECONDS_PER_MINUTE
        val seconds = safeSeconds % SECONDS_PER_MINUTE
        return String.format(Locale.US, "%02d:%02d", minutes, seconds)
    }

    fun formatSecondsToWorkoutTime(totalSeconds: Int): String {
        val safeSeconds = totalSeconds.coerceAtLeast(0)
        return if (safeSeconds >= SECONDS_PER_HOUR) {
            val hours = safeSeconds / SECONDS_PER_HOUR
            val minutes = (safeSeconds % SECONDS_PER_HOUR) / SECONDS_PER_MINUTE
            val seconds = safeSeconds % SECONDS_PER_MINUTE
            String.format(Locale.US, "%02d:%02d:%02d", hours, minutes, seconds)
        } else {
            formatSecondsToMmSs(safeSeconds)
        }
    }

    fun parseInputToSeconds(input: String): Int {
        val clean = input.filter { it.isDigit() }
        if (clean.isEmpty()) return 0
        return when {
            clean.length <= 2 -> clean.toIntOrNull() ?: 0
            else -> {
                val sec = clean.takeLast(2).toIntOrNull() ?: 0
                val min = clean.dropLast(2).toIntOrNull() ?: 0
                min * 60 + sec
            }
        }
    }
}
