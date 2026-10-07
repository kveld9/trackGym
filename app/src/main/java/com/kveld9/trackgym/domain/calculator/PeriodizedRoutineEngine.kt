package com.kveld9.trackgym.domain.calculator

import com.kveld9.trackgym.domain.model.PeriodizationPhase
import com.kveld9.trackgym.domain.model.PeriodizedCycle
import com.kveld9.trackgym.domain.model.PeriodizedWeek
import kotlin.math.max
import kotlin.math.roundToInt

/**
 * Multi-week periodization and deload block engine.
 * Computes progressive volume and load adaptations across training microcycles.
 */
object PeriodizedRoutineEngine {

    const val DEFAULT_CYCLE_WEEKS = 4

    /**
     * Builds a standard periodized cycle with volume accumulation, intensification,
     * and a final active recovery / deload week.
     */
    fun createDefaultCycle(totalWeeks: Int = DEFAULT_CYCLE_WEEKS, deloadAtEnd: Boolean = true): PeriodizedCycle {
        val clampedWeeks = totalWeeks.coerceIn(2, 16)
        val weeks = mutableListOf<PeriodizedWeek>()

        if (clampedWeeks == 4) {
            weeks.add(PeriodizedWeek(1, PeriodizationPhase.ACCUMULATION, "Week 1 - Intro Volume", 1.0, 0.95, 7.0))
            weeks.add(PeriodizedWeek(2, PeriodizationPhase.ACCUMULATION, "Week 2 - Baseline Load", 1.0, 1.00, 8.0))
            weeks.add(PeriodizedWeek(3, PeriodizationPhase.INTENSIFICATION, "Week 3 - Heavy Load", 1.0, 1.05, 9.0))
            if (deloadAtEnd) {
                weeks.add(PeriodizedWeek(4, PeriodizationPhase.DELOAD, "Week 4 - Active Deload", 0.5, 0.85, 6.0, isDeload = true))
            } else {
                weeks.add(PeriodizedWeek(4, PeriodizationPhase.PEAK, "Week 4 - Peak Intensity", 0.85, 1.08, 9.5))
            }
        } else if (clampedWeeks == 6) {
            weeks.add(PeriodizedWeek(1, PeriodizationPhase.ACCUMULATION, "Week 1 - Accumulation Intro", 1.0, 0.95, 7.0))
            weeks.add(PeriodizedWeek(2, PeriodizationWeekPhaseOrDefault(2), "Week 2 - Volume Ramp", 1.1, 0.975, 7.5))
            weeks.add(PeriodizedWeek(3, PeriodizationWeekPhaseOrDefault(3), "Week 3 - Max Volume", 1.15, 1.00, 8.0))
            weeks.add(PeriodizedWeek(4, PeriodizationPhase.INTENSIFICATION, "Week 4 - Intensification", 1.0, 1.025, 8.5))
            weeks.add(PeriodizedWeek(5, PeriodizationPhase.PEAK, "Week 5 - Heavy Peak", 0.85, 1.06, 9.5))
            weeks.add(PeriodizedWeek(6, PeriodizationPhase.DELOAD, "Week 6 - Active Deload", 0.5, 0.85, 6.0, isDeload = true))
        } else {
            val nonDeloadWeeks = if (deloadAtEnd) clampedWeeks - 1 else clampedWeeks
            val accumulationCount = max(1, (nonDeloadWeeks * 0.6).roundToInt())

            for (w in 1..nonDeloadWeeks) {
                if (w <= accumulationCount) {
                    val progress = if (accumulationCount > 1) (w - 1).toDouble() / (accumulationCount - 1) else 0.0
                    val vol = 1.0 + (0.15 * progress)
                    val intensity = 0.95 + (0.05 * progress)
                    val rpe = 7.0 + (1.0 * progress)
                    weeks.add(PeriodizedWeek(w, PeriodizationPhase.ACCUMULATION, "Week $w - Accumulation", vol, intensity, rpe))
                } else {
                    val intensIndex = w - accumulationCount
                    val intensTotal = nonDeloadWeeks - accumulationCount
                    val progress = if (intensTotal > 1) (intensIndex - 1).toDouble() / (intensTotal - 1) else 0.5
                    val phase = if (w == nonDeloadWeeks) PeriodizationPhase.PEAK else PeriodizationPhase.INTENSIFICATION
                    val vol = 1.0 - (0.15 * progress)
                    val intensity = 1.025 + (0.05 * progress)
                    val rpe = 8.5 + (1.0 * progress)
                    weeks.add(PeriodizedWeek(w, phase, "Week $w - ${phase.name.lowercase().replaceFirstChar { it.uppercase() }}", vol, intensity, rpe))
                }
            }

            if (deloadAtEnd) {
                weeks.add(PeriodizedWeek(clampedWeeks, PeriodizationPhase.DELOAD, "Week $clampedWeeks - Active Deload", 0.5, 0.85, 6.0, isDeload = true))
            }
        }

        return PeriodizedCycle(
            totalWeeks = clampedWeeks,
            currentWeek = 1,
            autoAdvanceOnCompletion = true,
            weeks = weeks
        )
    }

    private fun PeriodizationWeekPhaseOrDefault(week: Int): PeriodizationPhase {
        return if (week <= 3) PeriodizationPhase.ACCUMULATION else PeriodizationPhase.INTENSIFICATION
    }

    /**
     * Calculates prescribed working sets for an exercise given the week's volume multiplier.
     */
    fun calculatePrescribedSets(baseSets: Int, week: PeriodizedWeek): Int {
        if (baseSets <= 0) return 1
        val adjusted = (baseSets * week.volumeMultiplier).roundToInt()
        return max(1, adjusted)
    }

    /**
     * Calculates prescribed weight for an exercise given the week's intensity multiplier,
     * rounded to the smallest standard step (e.g. 0.5 kg).
     */
    fun calculatePrescribedWeight(baseWeightKg: Double, week: PeriodizedWeek, stepKg: Double = 0.5): Double {
        if (baseWeightKg <= 0.0) return 0.0
        val raw = baseWeightKg * week.intensityMultiplier
        val step = if (stepKg <= 0.0) 0.5 else stepKg
        val rounded = (kotlin.math.round(raw / step) * step)
        return max(0.0, rounded)
    }

    /**
     * Advances to the next week in the cycle.
     */
    fun advanceCycle(cycle: PeriodizedCycle, autoRepeat: Boolean = true): PeriodizedCycle {
        val nextWeek = if (cycle.currentWeek < cycle.totalWeeks) {
            cycle.currentWeek + 1
        } else if (autoRepeat) {
            1
        } else {
            cycle.totalWeeks
        }
        return cycle.copy(currentWeek = nextWeek)
    }

    /**
     * Moves to the previous week in the cycle.
     */
    fun previousCycle(cycle: PeriodizedCycle): PeriodizedCycle {
        val prevWeek = if (cycle.currentWeek > 1) {
            cycle.currentWeek - 1
        } else {
            cycle.totalWeeks
        }
        return cycle.copy(currentWeek = prevWeek)
    }

    /**
     * Sets current week explicitly within valid bounds.
     */
    fun setCycleWeek(cycle: PeriodizedCycle, targetWeek: Int): PeriodizedCycle {
        val clamped = targetWeek.coerceIn(1, cycle.totalWeeks)
        return cycle.copy(currentWeek = clamped)
    }

    /**
     * Serializes [PeriodizedCycle] into a compact, deterministic string.
     * Format: "TOTAL=4;CUR=1;AUTO=1|W:1,PH:ACCUMULATION,V:1.0,I:0.95,RPE:7.0,NAME:Intro|..."
     */
    fun encode(cycle: PeriodizedCycle): String {
        val header = "TOTAL=${cycle.totalWeeks};CUR=${cycle.currentWeek};AUTO=${if (cycle.autoAdvanceOnCompletion) "1" else "0"}"
        if (cycle.weeks.isEmpty()) return header

        val weeksString = cycle.weeks.joinToString("|") { w ->
            val rpeStr = w.targetRpe?.let { "%.1f".format(java.util.Locale.US, it) } ?: "NONE"
            "W:${w.weekNumber},PH:${w.phase.name},V:${w.volumeMultiplier},I:${w.intensityMultiplier},RPE:$rpeStr,NAME:${w.name.replace(";", "").replace("|", "")}"
        }
        return "$header|$weeksString"
    }

    /**
     * Deserializes encoded string into [PeriodizedCycle].
     */
    fun decode(encoded: String?): PeriodizedCycle? {
        if (encoded.isNullOrBlank()) return null
        return try {
            val parts = encoded.split("|")
            val headerPart = parts[0]
            val headerMap = headerPart.split(";").associate {
                val kv = it.split("=")
                if (kv.size == 2) kv[0] to kv[1] else "" to ""
            }

            val totalWeeks = headerMap["TOTAL"]?.toIntOrNull() ?: DEFAULT_CYCLE_WEEKS
            val currentWeek = headerMap["CUR"]?.toIntOrNull() ?: 1
            val autoAdvance = headerMap["AUTO"] != "0"

            val weeks = mutableListOf<PeriodizedWeek>()
            for (i in 1 until parts.size) {
                val weekToken = parts[i]
                if (weekToken.isBlank()) continue
                val kvMap = weekToken.split(",").associate {
                    val kv = it.split(":")
                    if (kv.size >= 2) kv[0] to kv.drop(1).joinToString(":") else "" to ""
                }
                val wNum = kvMap["W"]?.toIntOrNull() ?: i
                val phase = try {
                    PeriodizationPhase.valueOf(kvMap["PH"] ?: PeriodizationPhase.ACCUMULATION.name)
                } catch (_: Exception) {
                    PeriodizationPhase.ACCUMULATION
                }
                val vol = kvMap["V"]?.toDoubleOrNull() ?: 1.0
                val intensity = kvMap["I"]?.toDoubleOrNull() ?: 1.0
                val rpe = kvMap["RPE"]?.toDoubleOrNull()
                val name = kvMap["NAME"] ?: ""
                weeks.add(
                    PeriodizedWeek(
                        weekNumber = wNum,
                        phase = phase,
                        name = name,
                        volumeMultiplier = vol,
                        intensityMultiplier = intensity,
                        targetRpe = rpe,
                        isDeload = (phase == PeriodizationPhase.DELOAD)
                    )
                )
            }

            PeriodizedCycle(
                totalWeeks = totalWeeks,
                currentWeek = currentWeek.coerceIn(1, totalWeeks),
                autoAdvanceOnCompletion = autoAdvance,
                weeks = if (weeks.isNotEmpty()) weeks else createDefaultCycle(totalWeeks).weeks
            )
        } catch (_: Exception) {
            null
        }
    }
}
