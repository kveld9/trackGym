package com.kveld9.trackgym.ui.components

import android.graphics.Bitmap
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.kveld9.trackgym.R
import com.kveld9.trackgym.domain.model.ProgressPhoto
import com.kveld9.trackgym.ui.util.PhotoStorageManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.roundToInt

@Composable
fun BeforeAfterComparisonDialog(
    beforePhoto: ProgressPhoto,
    afterPhoto: ProgressPhoto,
    onDismiss: () -> Unit
) {
    var beforeBitmap by remember { mutableStateOf<Bitmap?>(null) }
    var afterBitmap by remember { mutableStateOf<Bitmap?>(null) }

    LaunchedEffect(beforePhoto.filePath) {
        val bmp = withContext(Dispatchers.IO) {
            PhotoStorageManager.loadFull(beforePhoto.filePath)
        }
        beforeBitmap = bmp
    }

    LaunchedEffect(afterPhoto.filePath) {
        val bmp = withContext(Dispatchers.IO) {
            PhotoStorageManager.loadFull(afterPhoto.filePath)
        }
        afterBitmap = bmp
    }

    val dateFormat = remember { SimpleDateFormat("dd MMM yyyy", Locale.getDefault()) }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            shape = RoundedCornerShape(20.dp),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                // Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = stringResource(R.string.progress_compare_title),
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Text(
                            text = "${dateFormat.format(Date(beforePhoto.capturedAt))}  ➜  ${dateFormat.format(Date(afterPhoto.capturedAt))}",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.tertiary
                        )
                    }
                    IconButton(onClick = onDismiss) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = stringResource(R.string.action_close),
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Interactive Split Comparison Slider
                BoxWithConstraints(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.Black)
                ) {
                    val boxWidthPx = constraints.maxWidth.toFloat()
                    var sliderPosition by remember { mutableFloatStateOf(0.5f) }

                    val beforeBmp = beforeBitmap
                    val afterBmp = afterBitmap

                    if (beforeBmp != null && afterBmp != null) {
                        // After image rendered in background (full)
                        Image(
                            bitmap = afterBmp.asImageBitmap(),
                            contentDescription = stringResource(R.string.progress_compare_after),
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )

                        // Before image clipped to the left of the slider
                        val clipShape = remember(sliderPosition) {
                            object : Shape {
                                override fun createOutline(
                                    size: Size,
                                    layoutDirection: LayoutDirection,
                                    density: Density
                                ): Outline {
                                    val splitX = size.width * sliderPosition
                                    val path = Path().apply {
                                        reset()
                                        addRect(Rect(0f, 0f, splitX, size.height))
                                        close()
                                    }
                                    return Outline.Generic(path)
                                }
                            }
                        }

                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(clipShape)
                        ) {
                            Image(
                                bitmap = beforeBmp.asImageBitmap(),
                                contentDescription = stringResource(R.string.progress_compare_before),
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        }

                        // Divider line & touch handle
                        val dividerX = maxWidth * sliderPosition

                        // Vertical divider line
                        Box(
                            modifier = Modifier
                                .offset(x = dividerX - 1.dp)
                                .width(2.dp)
                                .fillMaxSize()
                                .background(MaterialTheme.colorScheme.primary)
                        )

                        // Circular drag handle in center
                        Box(
                            modifier = Modifier
                                .offset {
                                    IntOffset(
                                        x = (boxWidthPx * sliderPosition - 20.dp.toPx()).roundToInt(),
                                        y = (constraints.maxHeight / 2 - 20.dp.toPx()).roundToInt()
                                    )
                                }
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.primary)
                                .pointerInput(boxWidthPx) {
                                    detectDragGestures { change, dragAmount ->
                                        change.consume()
                                        val newX = (boxWidthPx * sliderPosition) + dragAmount.x
                                        sliderPosition = (newX / boxWidthPx).coerceIn(0.05f, 0.95f)
                                    }
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.SwapHoriz,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onPrimary,
                                modifier = Modifier.size(24.dp)
                            )
                        }

                        // Labels: "Before" on left top, "After" on right top
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Surface(
                                shape = RoundedCornerShape(6.dp),
                                color = Color.Black.copy(alpha = 0.7f)
                            ) {
                                Text(
                                    text = stringResource(R.string.progress_compare_before),
                                    color = Color.White,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                )
                            }
                            Spacer(modifier = Modifier.weight(1f))
                            Surface(
                                shape = RoundedCornerShape(6.dp),
                                color = Color.Black.copy(alpha = 0.7f)
                            ) {
                                Text(
                                    text = stringResource(R.string.progress_compare_after),
                                    color = MaterialTheme.colorScheme.primary,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                )
                            }
                        }
                    } else {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Loading images...",
                                color = Color.Gray,
                                fontSize = 14.sp
                            )
                        }
                    }
                }
            }
        }
    }
}
