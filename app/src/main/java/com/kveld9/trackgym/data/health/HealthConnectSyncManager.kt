package com.kveld9.trackgym.data.health

import android.content.Context
import androidx.health.connect.client.HealthConnectClient
import androidx.health.connect.client.permission.HealthPermission
import androidx.health.connect.client.records.ExerciseSessionRecord
import androidx.health.connect.client.records.TotalCaloriesBurnedRecord
import androidx.health.connect.client.units.Energy
import com.kveld9.trackgym.domain.calculator.HealthConnectSyncEngine
import com.kveld9.trackgym.domain.model.Workout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.Instant
import java.time.ZoneId
import java.time.ZoneOffset

class HealthConnectSyncManager(private val context: Context) {

    val permissions = setOf(
        HealthPermission.getWritePermission(ExerciseSessionRecord::class),
        HealthPermission.getWritePermission(TotalCaloriesBurnedRecord::class)
    )

    fun isAvailable(): Boolean {
        return try {
            HealthConnectClient.getSdkStatus(context) == HealthConnectClient.SDK_AVAILABLE
        } catch (_: Exception) {
            false
        }
    }

    suspend fun hasAllPermissions(): Boolean = withContext(Dispatchers.IO) {
        if (!isAvailable()) return@withContext false
        val client = HealthConnectClient.getOrCreate(context)
        val granted = client.permissionController.getGrantedPermissions()
        granted.containsAll(permissions)
    }

    /**
     * Inserts the completed workout as an ExerciseSessionRecord and TotalCaloriesBurnedRecord into Health Connect.
     */
    suspend fun syncWorkout(
        workout: Workout,
        userBodyWeightKg: Double
    ): Boolean = withContext(Dispatchers.IO) {
        if (!isAvailable()) return@withContext false
        try {
            val client = HealthConnectClient.getOrCreate(context)
            val granted = client.permissionController.getGrantedPermissions()
            if (!granted.containsAll(permissions)) return@withContext false

            val startTime = Instant.ofEpochMilli(workout.startedAt)
            val endTime = Instant.ofEpochMilli(workout.completedAt ?: (workout.startedAt + 1000L))
            if (!endTime.isAfter(startTime)) return@withContext false

            val zoneOffset = ZoneId.systemDefault().rules.getOffset(startTime)

            val exerciseRecord = ExerciseSessionRecord(
                startTime = startTime,
                startZoneOffset = zoneOffset,
                endTime = endTime,
                endZoneOffset = zoneOffset,
                exerciseType = ExerciseSessionRecord.EXERCISE_TYPE_STRENGTH_TRAINING,
                title = workout.name,
                notes = workout.notes.ifBlank { null }
            )

            val durationMinutes = HealthConnectSyncEngine.calculateDurationMinutes(
                workout.startedAt,
                workout.completedAt ?: workout.startedAt
            )
            val caloriesKcal = HealthConnectSyncEngine.estimateCaloriesBurned(
                durationMinutes,
                userBodyWeightKg
            )

            val records = mutableListOf<androidx.health.connect.client.records.Record>(exerciseRecord)

            if (caloriesKcal > 0.0) {
                val caloriesRecord = TotalCaloriesBurnedRecord(
                    startTime = startTime,
                    startZoneOffset = zoneOffset,
                    endTime = endTime,
                    endZoneOffset = zoneOffset,
                    energy = Energy.kilocalories(caloriesKcal)
                )
                records.add(caloriesRecord)
            }

            client.insertRecords(records)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}
