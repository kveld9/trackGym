package com.kveld9.trackgym.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.MilitaryTech
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kveld9.trackgym.R
import com.kveld9.trackgym.domain.calculator.StrengthBenchmarkEngine
import com.kveld9.trackgym.domain.model.BiologicalSex
import com.kveld9.trackgym.domain.model.CoreLift
import com.kveld9.trackgym.domain.model.Exercise
import com.kveld9.trackgym.domain.model.PersonalRecord
import com.kveld9.trackgym.domain.model.RecordType
import com.kveld9.trackgym.domain.model.StrengthBenchmarkResult
import com.kveld9.trackgym.domain.model.StrengthLevel
import com.kveld9.trackgym.domain.model.WeightUnit

@Composable
fun StrengthStandardsCard(
    exercises: List<Exercise>,
    records: List<PersonalRecord>,
    userBodyWeightKg: Double,
    biologicalSex: BiologicalSex,
    userAge: Int,
    weightUnit: WeightUnit,
    onUpdateProfile: (sex: BiologicalSex, age: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    var showProfileDialog by remember { mutableStateOf(false) }
    var isExpanded by remember { mutableStateOf(true) }

    // Group 1RMs by matched core lift
    val exercisesMap = remember(exercises) { exercises.associateBy { it.id } }
    val benchmarks = remember(exercises, records, userBodyWeightKg, biologicalSex, userAge) {
        CoreLift.entries.map { coreLift ->
            // Find max 1RM record among exercises matching this lift
            var best1RM = 0.0
            var matchedExName = ""

            records.forEach { pr ->
                val ex = exercisesMap[pr.exerciseId]
                if (ex != null && StrengthBenchmarkEngine.matchCoreLift(ex.name) == coreLift) {
                    val currentOrm = when (pr.recordType) {
                        RecordType.ESTIMATED_1RM -> pr.recordValue
                        RecordType.MAX_WEIGHT -> pr.weightKg
                        else -> 0.0
                    }
                    if (currentOrm > best1RM) {
                        best1RM = currentOrm
                        matchedExName = ex.name
                    }
                }
            }

            StrengthBenchmarkEngine.evaluate(
                lift = coreLift,
                exerciseName = matchedExName,
                user1RMKg = best1RM,
                bodyWeightKg = userBodyWeightKg,
                sex = biologicalSex,
                age = userAge
            )
        }
    }

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
        border = CardDefaults.outlinedCardBorder().copy(
            brush = androidx.compose.ui.graphics.SolidColor(MaterialTheme.colorScheme.outlineVariant)
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .weight(1f)
                        .clickable { isExpanded = !isExpanded }
                ) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.MilitaryTech,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column {
                        Text(
                            text = stringResource(R.string.strength_benchmarks_title),
                            color = MaterialTheme.colorScheme.onSurface,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "${stringResource(biologicalSex.displayNameRes)} • ${stringResource(R.string.strength_user_age_years, userAge)} • ${weightUnit.format(userBodyWeightKg)}",
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontSize = 11.sp
                        )
                    }
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(
                        onClick = { showProfileDialog = true },
                        modifier = Modifier.size(36.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = stringResource(R.string.strength_profile_settings_title),
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    IconButton(
                        onClick = { isExpanded = !isExpanded },
                        modifier = Modifier.size(36.dp)
                    ) {
                        Icon(
                            imageVector = if (isExpanded) Icons.Default.ExpandMore else Icons.Default.ChevronRight,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(22.dp)
                        )
                    }
                }
            }

            AnimatedVisibility(visible = isExpanded) {
                Column(
                    modifier = Modifier.padding(top = 14.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        text = stringResource(R.string.strength_benchmarks_subtitle),
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 12.sp,
                        lineHeight = 16.sp
                    )

                    Spacer(modifier = Modifier.height(2.dp))

                    benchmarks.forEach { result ->
                        LiftBenchmarkRow(
                            result = result,
                            weightUnit = weightUnit
                        )
                    }
                }
            }
        }
    }

    if (showProfileDialog) {
        StrengthProfileDialog(
            currentSex = biologicalSex,
            currentAge = userAge,
            onDismiss = { showProfileDialog = false },
            onConfirm = { newSex, newAge ->
                onUpdateProfile(newSex, newAge)
                showProfileDialog = false
            }
        )
    }
}

@Composable
private fun LiftBenchmarkRow(
    result: StrengthBenchmarkResult,
    weightUnit: WeightUnit
) {
    val levelColor = when (result.level) {
        StrengthLevel.BEGINNER -> MaterialTheme.colorScheme.onSurfaceVariant
        StrengthLevel.NOVICE -> Color(0xFF4CAF50) // Green
        StrengthLevel.INTERMEDIATE -> MaterialTheme.colorScheme.primary // Cyan/Neon
        StrengthLevel.ADVANCED -> Color(0xFFFFA000) // Amber
        StrengthLevel.ELITE -> Color(0xFFFF5252) // Red / Elite
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerHigh)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            // Header row: Lift name + Level badge
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.FitnessCenter,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = stringResource(result.lift.displayNameRes),
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(levelColor.copy(alpha = 0.15f))
                        .padding(horizontal = 8.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = stringResource(result.level.displayNameRes),
                        color = levelColor,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            // Value & ratio stats
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (result.hasPersonalRecord) {
                    Text(
                        text = "1RM: ${weightUnit.format(result.user1RMKg)}",
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = stringResource(R.string.strength_ratio_label, result.ratio),
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 12.sp
                    )
                } else {
                    Text(
                        text = stringResource(R.string.strength_no_data_logged),
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                        fontSize = 12.sp
                    )
                    Text(
                        text = "0.00×",
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                        fontSize = 12.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            // Progress bar
            LinearProgressIndicator(
                progress = { (result.percentile / 100.0).toFloat().coerceIn(0f, 1f) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .clip(CircleShape),
                color = levelColor,
                trackColor = MaterialTheme.colorScheme.surfaceContainerHighest
            )

            Spacer(modifier = Modifier.height(6.dp))

            // Bottom percentile or next level
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.strength_percentile_stronger_than, result.percentile),
                    color = MaterialTheme.colorScheme.tertiary,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium
                )

                if (result.nextLevelTargetKg != null) {
                    Text(
                        text = stringResource(R.string.strength_next_level_target, weightUnit.format(result.nextLevelTargetKg)),
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 11.sp
                    )
                }
            }
        }
    }
}

@Composable
private fun StrengthProfileDialog(
    currentSex: BiologicalSex,
    currentAge: Int,
    onDismiss: () -> Unit,
    onConfirm: (BiologicalSex, Int) -> Unit
) {
    var selectedSex by remember { mutableStateOf(currentSex) }
    var ageText by remember { mutableStateOf(currentAge.toString()) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = stringResource(R.string.strength_profile_settings_title),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Column {
                    Text(
                        text = stringResource(R.string.strength_biological_sex_label),
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        BiologicalSex.entries.forEach { sex ->
                            val isSelected = selectedSex == sex
                            FilterChip(
                                selected = isSelected,
                                onClick = { selectedSex = sex },
                                label = { Text(stringResource(sex.displayNameRes)) },
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
                                    selectedLabelColor = MaterialTheme.colorScheme.primary
                                )
                            )
                        }
                    }
                }

                Column {
                    Text(
                        text = stringResource(R.string.strength_user_age_label),
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    OutlinedTextField(
                        value = ageText,
                        onValueChange = { ageText = it.filter { ch -> ch.isDigit() } },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = MaterialTheme.colorScheme.primary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outline
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    val parsedAge = ageText.toIntOrNull()?.coerceIn(12, 100) ?: currentAge
                    onConfirm(selectedSex, parsedAge)
                }
            ) {
                Text(stringResource(R.string.action_save), fontWeight = FontWeight.Bold)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(R.string.action_cancel))
            }
        }
    )
}
