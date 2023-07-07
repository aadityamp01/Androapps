package com.aaditya.pickcaptureimagejetpackcompose

import android.content.Context
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

fun Context.createImageFile(): File {
    // Create an image file name
    val timeStamp = SimpleDateFormat("ddMMyyyy_HHmmss").format(Date())
    val imageFileName = "JPEG_" + timeStamp + "_"
    return File.createTempFile(
        imageFileName, /* prefix */
        ".jpg", /* suffix */
        externalCacheDir      /* directory */
    )
}