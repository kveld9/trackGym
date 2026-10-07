package com.kveld9.trackgym.domain.util

import java.util.Locale

object DurationFormatter {
    fun formatSecondsToMmSs(totalSeconds: Int): String {
        val safeSeconds = totalSeconds.coerceAtLeast(0)
        val minutes = safeSeconds / 60
        val seconds = safeSeconds % 60
        return String.format(Locale.US, "%02d:%02d", minutes, seconds)
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
