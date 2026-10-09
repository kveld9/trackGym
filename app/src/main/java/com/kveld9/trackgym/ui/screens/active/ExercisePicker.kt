package com.kveld9.trackgym.ui.screens.active

import com.kveld9.trackgym.domain.calculator.FuzzyExerciseSearchEngine
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.HorizontalDivider
import com.kveld9.trackgym.domain.calculator.ExerciseSubstitutionEngine
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kveld9.trackgym.R
import com.kveld9.trackgym.domain.model.Exercise
import com.kveld9.trackgym.domain.model.ExerciseCategory
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.platform.LocalContext
import com.kveld9.trackgym.ui.util.LocalKeepEnglishExerciseNames
import com.kveld9.trackgym.ui.util.displayName

@Composable
fun ExercisePickerContent(
    exercises: List<Exercise>,
    exerciseToSubstitute: Exercise? = null,
    onSelectExercise: (Exercise) -> Unit
) {
    val context = LocalContext.current
    val keepEnglish = LocalKeepEnglishExerciseNames.current
    var searchQuery by remember { mutableStateOf("") }
    var selectedEquipmentFilter by remember { mutableStateOf<ExerciseCategory?>(null) }

    val recommendedSubstitutes = remember(exercises, exerciseToSubstitute, selectedEquipmentFilter) {
        if (exerciseToSubstitute == null) emptyList()
        else ExerciseSubstitutionEngine.findSubstitutes(
            target = exerciseToSubstitute,
            allExercises = exercises,
            targetCategory = selectedEquipmentFilter,
            limit = 6
        )
    }

    val filteredList = remember(exercises, searchQuery, keepEnglish) {
        if (searchQuery.isBlank()) exercises
        else {
            FuzzyExerciseSearchEngine.filterAndRank(
                query = searchQuery,
                exercises = exercises,
                keepEnglish = keepEnglish
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(540.dp)
            .padding(16.dp)
    ) {
        Text(
            text = if (exerciseToSubstitute != null) {
                stringResource(R.string.title_substitutes)
            } else {
                stringResource(R.string.title_select_exercise)
            },
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = if (exerciseToSubstitute != null) 2.dp else 8.dp)
        )

        if (exerciseToSubstitute != null) {
            Text(
                text = stringResource(R.string.subtitle_substitutes_for, exerciseToSubstitute.displayName(context, keepEnglish)),
                color = MaterialTheme.colorScheme.primary,
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Equipment filter row for substitutes
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .padding(bottom = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                FilterChip(
                    selected = selectedEquipmentFilter == null,
                    onClick = { selectedEquipmentFilter = null },
                    label = { Text(stringResource(R.string.chip_filter_all_equipment), fontSize = 12.sp) }
                )
                ExerciseCategory.entries.filter { it != ExerciseCategory.OTHER }.forEach { cat ->
                    FilterChip(
                        selected = selectedEquipmentFilter == cat,
                        onClick = { selectedEquipmentFilter = cat },
                        label = { Text(stringResource(cat.nameRes), fontSize = 12.sp) }
                    )
                }
            }
        }

        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = { Text(stringResource(R.string.search_exercise_placeholder), color = MaterialTheme.colorScheme.onSurfaceVariant) },
            leadingIcon = {
                Icon(Icons.Default.Search, contentDescription = null, tint = MaterialTheme.colorScheme.onSurfaceVariant)
            },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f),
                focusedContainerColor = MaterialTheme.colorScheme.surfaceContainer,
                unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainer,
                focusedTextColor = MaterialTheme.colorScheme.onSurface,
                unfocusedTextColor = MaterialTheme.colorScheme.onSurface
            )
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (exerciseToSubstitute != null && searchQuery.isBlank()) {
                item {
                    Text(
                        text = stringResource(R.string.section_recommended_substitutes),
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }

                if (recommendedSubstitutes.isEmpty()) {
                    item {
                        Surface(
                            color = MaterialTheme.colorScheme.surfaceContainerHighest,
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                        ) {
                            Text(
                                text = stringResource(R.string.substitute_no_alternatives),
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                fontSize = 12.sp,
                                modifier = Modifier.padding(12.dp)
                            )
                        }
                    }
                } else {
                    items(recommendedSubstitutes, key = { "sub_${it.exercise.id}" }) { sub ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(10.dp))
                                .background(MaterialTheme.colorScheme.surfaceContainerHigh)
                                .border(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.5f), RoundedCornerShape(10.dp))
                                .clickable { onSelectExercise(sub.exercise) }
                                .padding(12.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = sub.exercise.displayName(context, keepEnglish),
                                    color = MaterialTheme.colorScheme.onSurface,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = "${stringResource(sub.exercise.muscleGroup.nameRes)} • ${stringResource(sub.exercise.category.nameRes)} • ${stringResource(sub.exercise.mechanics.nameRes)}",
                                    color = MaterialTheme.colorScheme.primary,
                                    fontSize = 12.sp
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = stringResource(sub.reason.nameRes),
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    fontSize = 11.sp
                                )
                            }
                            Surface(
                                color = MaterialTheme.colorScheme.primaryContainer,
                                shape = RoundedCornerShape(6.dp)
                            ) {
                                Text(
                                    text = stringResource(R.string.substitute_match_score, sub.matchScore),
                                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                )
                            }
                        }
                    }
                }

                item {
                    HorizontalDivider(
                        color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f),
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    Text(
                        text = stringResource(R.string.title_select_exercise),
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                }
            }

            items(filteredList, key = { it.id }) { exercise ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(MaterialTheme.colorScheme.surfaceContainerHigh)
                        .clickable { onSelectExercise(exercise) }
                        .padding(14.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = exercise.displayName(),
                            color = MaterialTheme.colorScheme.onSurface,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "${stringResource(exercise.muscleGroup.nameRes)} • ${stringResource(exercise.category.nameRes)} • ${stringResource(exercise.force.nameRes)} • ${stringResource(exercise.level.nameRes)}",
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 12.sp
                        )
                    }
                    if (exercise.isCustom) {
                        Text(
                            text = stringResource(R.string.badge_manual),
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(4.dp))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    } else {
                        Text(
                            text = stringResource(R.string.badge_preloaded),
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier
                                .border(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.6f), RoundedCornerShape(4.dp))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }
            }
        }
    }
}
