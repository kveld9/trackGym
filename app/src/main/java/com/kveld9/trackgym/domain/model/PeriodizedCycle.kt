package com.kveld9.trackgym.domain.model

data class PeriodizedWeek(
    val weekNumber: Int,
    val phase: PeriodizationPhase = PeriodizationPhase.ACCUMULATION,
    val name: String = "",
    val volumeMultiplier: Double = 1.0,
    val intensityMultiplier: Double = 1.0,
    val targetRpe: Double? = null,
    val isDeload: Boolean = (phase == PeriodizationPhase.DELOAD)
)

data class PeriodizedCycle(
    val totalWeeks: Int = 4,
    val currentWeek: Int = 1,
    val autoAdvanceOnCompletion: Boolean = true,
    val weeks: List<PeriodizedWeek> = emptyList()
) {
    fun getCurrentWeekConfig(): PeriodizedWeek {
        return weeks.firstOrNull { it.weekNumber == currentWeek }
            ?: PeriodizedWeek(weekNumber = currentWeek)
    }
}
