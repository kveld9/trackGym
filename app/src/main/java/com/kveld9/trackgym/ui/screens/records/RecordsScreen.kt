package com.kveld9.trackgym.ui.screens.records

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kveld9.trackgym.R
import com.kveld9.trackgym.domain.model.PersonalRecord
import com.kveld9.trackgym.domain.model.RecordType
import com.kveld9.trackgym.domain.model.WeightUnit
import androidx.compose.material3.MaterialTheme
import com.kveld9.trackgym.ui.util.displayName
import com.kveld9.trackgym.ui.viewmodel.GymViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecordsScreen(
    viewModel: GymViewModel,
    modifier: Modifier = Modifier,
    weightUnit: WeightUnit = WeightUnit.KG
) {
    val records by viewModel.allRecords.collectAsStateWithLifecycle()
    val exercises by viewModel.allExercises.collectAsStateWithLifecycle()
    val userBodyWeight by viewModel.userBodyWeight.collectAsStateWithLifecycle()
    val biologicalSex by viewModel.userBiologicalSex.collectAsStateWithLifecycle()
    val userAge by viewModel.userAge.collectAsStateWithLifecycle()

    val exercisesMap = exercises.associateBy { it.id }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.records_title),
                        color = MaterialTheme.colorScheme.onBackground,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background)
            )
        },
        containerColor = MaterialTheme.colorScheme.background,
        modifier = modifier
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item(key = "strength_standards_card") {
                com.kveld9.trackgym.ui.components.StrengthStandardsCard(
                    exercises = exercises,
                    records = records,
                    userBodyWeightKg = userBodyWeight,
                    biologicalSex = biologicalSex,
                    userAge = userAge,
                    weightUnit = weightUnit,
                    onUpdateProfile = { newSex, newAge ->
                        viewModel.setUserBiologicalSex(newSex)
                        viewModel.setUserAge(newAge)
                    },
                    modifier = Modifier.padding(bottom = 4.dp)
                )
            }

            if (records.isEmpty()) {
                item(key = "empty_records_view") {
                    EmptyRecordsView(modifier = Modifier.padding(vertical = 32.dp))
                }
            } else {
                item(key = "records_history_header") {
                    Text(
                        text = stringResource(R.string.records_history_section, records.size),
                        color = MaterialTheme.colorScheme.tertiary,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }

                items(records, key = { it.id }) { pr ->
                    val exercise = exercisesMap[pr.exerciseId]
                    RecordItemCard(
                        record = pr,
                        exerciseName = exercise?.displayName() ?: "Exercise",
                        weightUnit = weightUnit
                    )
                }
            }

            item(key = "bottom_spacer") {
                Spacer(modifier = Modifier.height(120.dp))
            }
        }
    }
}

@Composable
fun RecordItemCard(
    record: PersonalRecord,
    exerciseName: String,
    weightUnit: WeightUnit = WeightUnit.KG
) {
    val dateFormat = SimpleDateFormat("d MMM yyyy", Locale.getDefault())
    val dateStr = dateFormat.format(Date(record.achievedAt))

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
        border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(MaterialTheme.colorScheme.tertiary.copy(alpha = 0.5f)))
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.tertiary.copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.EmojiEvents,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = exerciseName,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = dateStr,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 12.sp
                    )
                }

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = stringResource(record.recordType.nameRes),
                    color = MaterialTheme.colorScheme.tertiary,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(4.dp))

                val recordDescription = when (record.recordType) {
                    RecordType.MAX_WEIGHT -> "${weightUnit.format(record.weightKg)} (${record.reps} reps)"
                    RecordType.MAX_REPS_AT_WEIGHT -> "${record.reps} reps @ ${weightUnit.format(record.weightKg)}"
                    RecordType.ESTIMATED_1RM -> "1RM: ${weightUnit.format(record.recordValue)}"
                    RecordType.MAX_VOLUME_SET -> "Vol: ${weightUnit.format(record.recordValue)}"
                    RecordType.MAX_AMRAP_REPS_AT_WEIGHT -> "${record.reps}+ reps @ ${weightUnit.format(record.weightKg)}"
                }

                Text(
                    text = recordDescription,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 13.sp
                )
            }
        }
    }
}

@Composable
fun EmptyRecordsView(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Surface(
            modifier = Modifier.size(80.dp),
            shape = CircleShape,
            color = MaterialTheme.colorScheme.surfaceContainerHigh
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    imageVector = Icons.Default.EmojiEvents,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.size(40.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = stringResource(R.string.records_empty_title),
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(R.string.records_empty_desc),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )
    }
}
