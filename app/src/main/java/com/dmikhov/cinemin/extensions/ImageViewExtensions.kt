package com.dmikhov.cinemin.extensions

import android.widget.ImageView

fun ImageView.setStartCropMatrix() {
    scaleType = ImageView.ScaleType.MATRIX
    val matrix = imageMatrix
    val imageHeight = drawable.intrinsicHeight.toFloat()
    val screenHeight = resources.displayMetrics.heightPixels
    val scaleRatio = screenHeight / imageHeight
    matrix.postScale(scaleRatio, scaleRatio)
    imageMatrix = matrix
}