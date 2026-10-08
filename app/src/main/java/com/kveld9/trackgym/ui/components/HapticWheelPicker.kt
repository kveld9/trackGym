package com.kveld9.trackgym.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kveld9.trackgym.R
import com.kveld9.trackgym.domain.calculator.HapticWheelValuesEngine
import com.kveld9.trackgym.domain.model.WeightUnit
import kotlin.math.abs

/**
 * Reusable 3D cylindrical vertical wheel picker with magnetic snap and haptic feedback.
 */
@Composable
fun <T> HapticWheelColumn(
    items: List<T>,
    selectedIndex: Int,
    onSelectedIndexChanged: (Int) -> Unit,
    modifier: Modifier = Modifier,
    itemHeight: Dp = 48.dp,
    visibleItemsCount: Int = 5,
    formatItem: (T) -> String = { it.toString() }
) {
    val haptic = LocalHapticFeedback.current
    val listState = rememberLazyListState(initialFirstVisibleItemIndex = selectedIndex.coerceIn(0, (items.size - 1).coerceAtLeast(0)))
    val flingBehavior = rememberSnapFlingBehavior(lazyListState = listState)

    // Current centered index derived from scroll offset
    val currentIndex by remember {
        derivedStateOf {
            val layoutInfo = listState.layoutInfo
            val visibleItems = layoutInfo.visibleItemsInfo
            if (visibleItems.isEmpty()) 0
            else {
                val viewportCenter = (layoutInfo.viewportStartOffset + layoutInfo.viewportEndOffset) / 2
                val closest = visibleItems.minByOrNull { item ->
                    abs((item.offset + item.size / 2) - viewportCenter)
                }
                closest?.index?.coerceIn(0, (items.size - 1).coerceAtLeast(0)) ?: 0
            }
        }
    }

    var lastHapticIndex by remember { mutableIntStateOf(selectedIndex) }

    LaunchedEffect(listState) {
        snapshotFlow { currentIndex }
            .collect { index ->
                if (index != lastHapticIndex) {
                    haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                    lastHapticIndex = index
                    onSelectedIndexChanged(index)
                }
            }
    }

    val totalHeight = itemHeight * visibleItemsCount

    Box(
        modifier = modifier
            .height(totalHeight)
            .clip(RoundedCornerShape(12.dp)),
        contentAlignment = Alignment.Center
    ) {
        // Selection highlight bar behind items
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(itemHeight),
            shape = RoundedCornerShape(8.dp),
            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.12f),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))
        ) {}

        LazyColumn(
            state = listState,
            flingBehavior = flingBehavior,
            modifier = Modifier.height(totalHeight),
            contentPadding = PaddingValues(vertical = itemHeight * (visibleItemsCount / 2))
        ) {
            itemsIndexed(items) { index, item ->
                val distance = abs(index - currentIndex)
                val scale = (1f - (distance * 0.12f)).coerceIn(0.7f, 1f)
                val alpha = (1f - (distance * 0.28f)).coerceIn(0.2f, 1f)
                val rotation = ((index - currentIndex) * -14f).coerceIn(-45f, 45f)

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(itemHeight)
                        .graphicsLayer {
                            scaleX = scale
                            scaleY = scale
                            this.alpha = alpha
                            rotationX = rotation
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = formatItem(item),
                        fontSize = if (distance == 0) 20.sp else 16.sp,
                        fontWeight = if (distance == 0) FontWeight.Black else FontWeight.Medium,
                        color = if (distance == 0) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

/**
 * Dialog presenting haptic wheel pickers for rapid weight and reps entry without a soft keyboard.
 */
@Composable
fun HapticWheelSetPickerDialog(
    exerciseName: String,
    setNumber: Int,
    initialWeightKg: Double,
    initialReps: Int,
    weightUnit: WeightUnit,
    onApply: (weightKg: Double, reps: Int, completeNow: Boolean) -> Unit,
    onDismiss: () -> Unit
) {
    val initialDisplayWeight = weightUnit.fromKg(initialWeightKg)
    val availableFractions = remember { HapticWheelValuesEngine.DEFAULT_FRACTIONS }
    val initialSplit = remember(initialDisplayWeight) {
        HapticWheelValuesEngine.splitWeight(initialDisplayWeight, availableFractions)
    }

    val wholeWeightItems = remember { (0..HapticWheelValuesEngine.MAX_WEIGHT_WHOLE).toList() }
    val fractionItems = remember { availableFractions }
    val repsItems = remember { (0..HapticWheelValuesEngine.MAX_REPS).toList() }

    var selectedWholeIndex by remember {
        mutableIntStateOf(HapticWheelValuesEngine.findClosestIndex(wholeWeightItems, initialSplit.whole))
    }
    var selectedFractionIndex by remember {
        mutableIntStateOf(HapticWheelValuesEngine.findClosestIndex(fractionItems, initialSplit.fraction))
    }
    var selectedRepsIndex by remember {
        mutableIntStateOf(HapticWheelValuesEngine.findClosestIndex(repsItems, initialReps.coerceIn(0, HapticWheelValuesEngine.MAX_REPS)))
    }

    val currentDisplayWeight by remember {
        derivedStateOf {
            val whole = wholeWeightItems.getOrElse(selectedWholeIndex) { 0 }
            val frac = fractionItems.getOrElse(selectedFractionIndex) { 0.0 }
            HapticWheelValuesEngine.combineWeight(whole, frac)
        }
    }

    val currentReps by remember {
        derivedStateOf {
            repsItems.getOrElse(selectedRepsIndex) { 0 }
        }
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = MaterialTheme.colorScheme.surfaceContainer,
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Speed,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(
                        text = stringResource(R.string.wheel_picker_title, setNumber),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = exerciseName,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                // Live preview summary card
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerHigh),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f))
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 12.dp),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = stringResource(R.string.wheel_picker_weight_label),
                                fontSize = 11.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                fontWeight = FontWeight.SemiBold
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = "$currentDisplayWeight ${weightUnit.symbol}",
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Black,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }

                        Surface(
                            modifier = Modifier
                                .width(1.dp)
                                .height(36.dp),
                            color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f)
                        ) {}

                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = stringResource(R.string.wheel_picker_reps_label),
                                fontSize = 11.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                fontWeight = FontWeight.SemiBold
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = "$currentReps",
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Black,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }

                // Quick weight micro-bump chips
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val increments = listOf(1.25, 2.5, 5.0)
                    increments.forEach { inc ->
                        FilterChip(
                            selected = false,
                            onClick = {
                                val bumped = currentDisplayWeight + inc
                                val split = HapticWheelValuesEngine.splitWeight(bumped, availableFractions)
                                selectedWholeIndex = HapticWheelValuesEngine.findClosestIndex(wholeWeightItems, split.whole)
                                selectedFractionIndex = HapticWheelValuesEngine.findClosestIndex(fractionItems, split.fraction)
                            },
                            label = { Text("+$inc ${weightUnit.symbol}", fontSize = 11.sp) }
                        )
                    }
                }

                // 3 Cylindrical Wheels side by side
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Whole Weight
                    Column(
                        modifier = Modifier.weight(1.2f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = weightUnit.symbol.uppercase(),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        HapticWheelColumn(
                            items = wholeWeightItems,
                            selectedIndex = selectedWholeIndex,
                            onSelectedIndexChanged = { selectedWholeIndex = it }
                        )
                    }

                    // Fractional Weight
                    Column(
                        modifier = Modifier.weight(0.9f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = ".XX",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        HapticWheelColumn(
                            items = fractionItems,
                            selectedIndex = selectedFractionIndex,
                            onSelectedIndexChanged = { selectedFractionIndex = it },
                            formatItem = { HapticWheelValuesEngine.formatFractionDisplay(it) }
                        )
                    }

                    // Reps Wheel
                    Column(
                        modifier = Modifier.weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = stringResource(R.string.wheel_picker_reps_label).uppercase(),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        HapticWheelColumn(
                            items = repsItems,
                            selectedIndex = selectedRepsIndex,
                            onSelectedIndexChanged = { selectedRepsIndex = it }
                        )
                    }
                }
            }
        },
        confirmButton = {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedButton(
                    onClick = {
                        val finalKg = weightUnit.toKg(currentDisplayWeight)
                        onApply(finalKg, currentReps, false)
                    },
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(stringResource(R.string.wheel_picker_apply))
                }

                Button(
                    onClick = {
                        val finalKg = weightUnit.toKg(currentDisplayWeight)
                        onApply(finalKg, currentReps, true)
                    },
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Icon(imageVector = Icons.Default.Check, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(stringResource(R.string.wheel_picker_apply_and_complete))
                }
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(R.string.action_cancel))
            }
        }
    )
}
