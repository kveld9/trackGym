package com.kveld9.trackgym.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke

/**
 * Renders an anatomical ghost silhouette outline over the camera viewfinder / capture preview
 * to ensure consistent distance, angle, and framing between sessions.
 */
@Composable
fun CameraGhostSilhouette(
    modifier: Modifier = Modifier,
    color: Color = Color.White.copy(alpha = 0.5f)
) {
    Canvas(modifier = modifier.fillMaxSize()) {
        val w = size.width
        val h = size.height

        val strokeStyle = Stroke(
            width = 3f,
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(15f, 15f), 0f)
        )

        // Head oval
        val headCenterX = w / 2f
        val headCenterY = h * 0.22f
        val headRadiusX = w * 0.11f
        val headRadiusY = h * 0.08f
        drawOval(
            color = color,
            topLeft = Offset(headCenterX - headRadiusX, headCenterY - headRadiusY),
            size = Size(headRadiusX * 2, headRadiusY * 2),
            style = strokeStyle
        )

        // Torso / shoulders / waist contour
        val bodyPath = Path().apply {
            // Neck base
            moveTo(headCenterX - headRadiusX * 0.45f, headCenterY + headRadiusY)
            // Left neck to shoulder
            lineTo(w * 0.26f, h * 0.33f)
            // Left arm drop
            lineTo(w * 0.22f, h * 0.65f)
            // Left hip/waist return
            lineTo(w * 0.32f, h * 0.58f)
            // Left hip outer
            lineTo(w * 0.30f, h * 0.70f)
            // Left leg outer
            lineTo(w * 0.33f, h * 0.95f)
            // Left leg inner
            lineTo(w * 0.45f, h * 0.95f)
            // Crotch
            lineTo(headCenterX, h * 0.68f)
            // Right leg inner
            lineTo(w * 0.55f, h * 0.95f)
            // Right leg outer
            lineTo(w * 0.67f, h * 0.95f)
            // Right hip outer
            lineTo(w * 0.70f, h * 0.70f)
            // Right hip/waist return
            lineTo(w * 0.68f, h * 0.58f)
            // Right arm drop
            lineTo(w * 0.78f, h * 0.65f)
            // Right shoulder
            lineTo(w * 0.74f, h * 0.33f)
            // Right neck base
            lineTo(headCenterX + headRadiusX * 0.45f, headCenterY + headRadiusY)
        }

        drawPath(
            path = bodyPath,
            color = color,
            style = strokeStyle
        )

        // Reference alignment guide crosshairs (horizontal at chest & waist, vertical center line)
        drawLine(
            color = color.copy(alpha = 0.25f),
            start = Offset(headCenterX, h * 0.08f),
            end = Offset(headCenterX, h * 0.96f),
            strokeWidth = 2f,
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
        )

        // Shoulder level line
        drawLine(
            color = color.copy(alpha = 0.25f),
            start = Offset(w * 0.22f, h * 0.33f),
            end = Offset(w * 0.78f, h * 0.33f),
            strokeWidth = 2f,
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
        )

        // Hip level line
        drawLine(
            color = color.copy(alpha = 0.25f),
            start = Offset(w * 0.28f, h * 0.68f),
            end = Offset(w * 0.72f, h * 0.68f),
            strokeWidth = 2f,
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
        )
    }
}
