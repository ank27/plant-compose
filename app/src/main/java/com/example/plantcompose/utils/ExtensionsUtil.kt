package com.example.plantcompose.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.provider.Settings
import android.util.Log
import androidx.core.content.FileProvider
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

/**
 * Convert string to [Instant] object.
 *
 */
internal fun String.toDateInstant(): Instant? {
    return try {
        if (this.isEmpty()) null else Instant.parse(this)
    } catch (e: Exception) {
        Log.e(LOG_TAG, "Unable to parse to instant $this")
        null
    }
}

/**
 * Convert json string to model object.
 */
internal inline fun <reified T> Gson.fromJson(json: String) =
    fromJson<T>(json, object : TypeToken<T>() {}.type)

/**
 * Check if the string data is null.
 */
internal fun String.isNull() = this == "null"

private const val MONTH_YEAR_TIME_FORMAT = "dd/MM/yyyy, HH:mm a"

fun Instant.toFormattedDateTime(): String {
    val formatter = DateTimeFormatter
        .ofPattern(MONTH_YEAR_TIME_FORMAT)
        .withZone(ZoneId.systemDefault())
    return formatter.format(this)
}

/**
 * Create Image Uri for saving image.
 */
fun Context.createImageUri(): Uri? {
    val imageFile = try {
        File(
            this.getExternalFilesDir(Environment.DIRECTORY_PICTURES),
            "plant_${System.currentTimeMillis()}.jpg"
        ).apply {
            parentFile?.mkdirs() // Ensure directory exists
        }
    } catch (e: Exception) {
        e.printStackTrace()
        return null
    }

    return try {
        FileProvider.getUriForFile(
            this,
            "${this.packageName}.fileprovider",
            imageFile
        )
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

/**
 * Open app settings.
 */
fun Context.openAppSettings() {
    try {
        Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
            data = Uri.fromParts("package", packageName, null)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(this)
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}


private const val LOG_TAG = "ExtensionUtil"
