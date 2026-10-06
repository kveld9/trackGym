package com.kveld9.trackgym.ui.util

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.platform.LocalContext
import com.kveld9.trackgym.domain.model.Exercise
import com.kveld9.trackgym.domain.model.ExerciseTranslationRegistry

val LocalKeepEnglishExerciseNames = compositionLocalOf { false }

fun Exercise.displayName(context: Context, keepEnglish: Boolean): String {
    if (keepEnglish) return name
    val resId = ExerciseTranslationRegistry.getNameRes(name) ?: return name
    return context.getString(resId)
}

@Composable
fun Exercise.displayName(keepEnglish: Boolean = LocalKeepEnglishExerciseNames.current): String {
    val context = LocalContext.current
    return displayName(context, keepEnglish)
}

