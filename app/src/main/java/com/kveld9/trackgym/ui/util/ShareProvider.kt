package com.kveld9.trackgym.ui.util

import android.content.ClipData
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import androidx.core.content.FileProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

object ShareProvider {

    /**
     * Shares the given workout bitmap via standard Android share sheet off the main thread.
     */
    suspend fun shareBitmap(
        context: Context,
        bitmap: Bitmap,
        shareChooserTitle: String = "Share Workout"
    ) = withContext(Dispatchers.IO) {
        try {
            val cachePath = File(context.cacheDir, "shared_images")
            if (!cachePath.exists()) {
                cachePath.mkdirs()
            }
            val file = File(cachePath, "workout_summary.png")
            FileOutputStream(file).use { stream ->
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
            }

            val contentUri = FileProvider.getUriForFile(
                context,
                "${context.packageName}.fileprovider",
                file
            )

            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "image/png"
                putExtra(Intent.EXTRA_STREAM, contentUri)
                clipData = ClipData.newRawUri(null, contentUri)
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }
            val chooser = Intent.createChooser(intent, shareChooserTitle).apply {
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            withContext(Dispatchers.Main) {
                context.startActivity(chooser)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * Saves the workout summary bitmap directly to the device Gallery (Pictures/TrackGym) off the main thread.
     */
    suspend fun saveBitmapToGallery(context: Context, bitmap: Bitmap): Boolean = withContext(Dispatchers.IO) {
        val filename = "TrackGym_Workout_${System.currentTimeMillis()}.png"
        var fos: OutputStream? = null

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                val resolver = context.contentResolver ?: return@withContext false
                val contentValues = ContentValues().apply {
                    put(MediaStore.MediaColumns.DISPLAY_NAME, filename)
                    put(MediaStore.MediaColumns.MIME_TYPE, "image/png")
                    put(MediaStore.MediaColumns.RELATIVE_PATH, "Pictures/TrackGym")
                }
                val imageUri: Uri? = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
                fos = imageUri?.let { resolver.openOutputStream(it) }
            } else {
                @Suppress("DEPRECATION")
                val picturesDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                val imagesDir = File(picturesDir, "TrackGym").takeIf { it.exists() || it.mkdirs() }
                    ?: File(context.getExternalFilesDir(null), "TrackGym").apply { mkdirs() }
                val image = File(imagesDir, filename)
                fos = FileOutputStream(image)
                MediaScannerConnection.scanFile(
                    context,
                    arrayOf(image.absolutePath),
                    arrayOf("image/png"),
                    null
                )
            }

            fos?.use {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, it)
                return@withContext true
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return@withContext false
    }
}
