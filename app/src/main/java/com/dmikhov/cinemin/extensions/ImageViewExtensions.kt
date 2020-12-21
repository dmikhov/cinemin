package com.dmikhov.cinemin.extensions

import android.widget.ImageView

fun ImageView.setStartCropMatrix() {
    scaleType = ImageView.ScaleType.MATRIX
    val matrix = imageMatrix
    val imageWidth = drawable.intrinsicWidth.toFloat()
    val imageHeight = drawable.intrinsicHeight.toFloat()
    val screenWidth = resources.displayMetrics.widthPixels
    val screenHeight = resources.displayMetrics.heightPixels
    val widthScaleRatio = screenWidth / imageWidth
    val heightScaleRatio = screenHeight / imageHeight
    val largestScale = if (widthScaleRatio > heightScaleRatio) {
        widthScaleRatio
    } else {
        heightScaleRatio
    }
    matrix.postScale(largestScale, largestScale)
    imageMatrix = matrix
}