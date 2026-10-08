package com.kveld9.trackgym.ui.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

object PhotoStorageManager {

    private const val PHOTO_DIR = "progress_photos"
    private const val MAX_DIMENSION = 1920
    private const val JPEG_QUALITY = 85

    fun getPhotosDirectory(context: Context): File {
        val dir = File(context.filesDir, PHOTO_DIR)
        if (!dir.exists()) {
            dir.mkdirs()
        }
        return dir
    }

    /**
     * Copies and downsamples an image from a content Uri (e.g. from gallery or camera)
     * into app-private internal storage, returning the absolute file path.
     */
    suspend fun savePhotoFromUri(context: Context, sourceUri: Uri): String? = withContext(Dispatchers.IO) {
        try {
            val destDir = getPhotosDirectory(context)
            val fileName = "photo_${System.currentTimeMillis()}.jpg"
            val destFile = File(destDir, fileName)

            val inputStream: InputStream = context.contentResolver.openInputStream(sourceUri)
                ?: return@withContext null

            val boundsOptions = BitmapFactory.Options().apply { inJustDecodeBounds = true }
            BitmapFactory.decodeStream(inputStream, null, boundsOptions)
            inputStream.close()

            var sampleSize = 1
            var width = boundsOptions.outWidth
            var height = boundsOptions.outHeight
            while (width > MAX_DIMENSION || height > MAX_DIMENSION) {
                sampleSize *= 2
                width /= 2
                height /= 2
            }

            val decodeStream = context.contentResolver.openInputStream(sourceUri)
                ?: return@withContext null
            val decodeOptions = BitmapFactory.Options().apply {
                inSampleSize = sampleSize
                inPreferredConfig = Bitmap.Config.RGB_565
            }
            val bitmap = BitmapFactory.decodeStream(decodeStream, null, decodeOptions)
            decodeStream.close()

            if (bitmap != null) {
                FileOutputStream(destFile).use { out ->
                    bitmap.compress(Bitmap.CompressFormat.JPEG, JPEG_QUALITY, out)
                }
                bitmap.recycle()
                destFile.absolutePath
            } else {
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    /**
     * Loads a downsampled thumbnail Bitmap for fast and memory-efficient Compose rendering.
     */
    fun loadThumbnail(filePath: String, maxDim: Int = 300): Bitmap? {
        val file = File(filePath)
        if (!file.exists()) return null

        val bounds = BitmapFactory.Options().apply { inJustDecodeBounds = true }
        BitmapFactory.decodeFile(filePath, bounds)

        var sampleSize = 1
        var w = bounds.outWidth
        var h = bounds.outHeight
        while (w > maxDim || h > maxDim) {
            sampleSize *= 2
            w /= 2
            h /= 2
        }

        val opts = BitmapFactory.Options().apply {
            inSampleSize = sampleSize
            inPreferredConfig = Bitmap.Config.RGB_565
        }
        return BitmapFactory.decodeFile(filePath, opts)
    }

    /**
     * Loads a high-res Bitmap for comparison slider.
     */
    fun loadFull(filePath: String): Bitmap? {
        val file = File(filePath)
        if (!file.exists()) return null
        return loadThumbnail(filePath, maxDim = 1200)
    }
}
