package com.dmikhov.cinemabudget.extensions

import android.graphics.Bitmap

fun Bitmap.cropMargin(cropMargin: Int = 10): Bitmap {
    val dstX = cropMargin
    val dstY = cropMargin
    val dstWidth = width - 2 * cropMargin
    val dstHeight = height - 2 * cropMargin
    return Bitmap.createBitmap(this, dstX, dstY, dstWidth, dstHeight)
}

fun Bitmap.blur(radius: Int = 8): Bitmap {
    val bitmapCopy = copy(Bitmap.Config.ARGB_8888, true)
    val w = bitmapCopy.width
    val h = bitmapCopy.height
    val pix = IntArray(w * h)
    bitmapCopy.getPixels(pix, 0, w, 0, 0, w, h)
    var r = radius
    while (r >= 1) {
        for (i in r until h - r) {
            for (j in r until w - r) {
                val tl = pix[(i - r) * w + j - r]
                val tr = pix[(i - r) * w + j + r]
                val tc = pix[(i - r) * w + j]
                val bl = pix[(i + r) * w + j - r]
                val br = pix[(i + r) * w + j + r]
                val bc = pix[(i + r) * w + j]
                val cl = pix[i * w + j - r]
                val cr = pix[i * w + j + r]
                pix[i * w + j] = -0x1000000 or (
                        (tl and 0xFF) + (tr and 0xFF) + (tc and 0xFF) + (bl and 0xFF) + (br and 0xFF) + (bc and 0xFF) + (cl and 0xFF) + (cr and 0xFF) shr 3 and 0xFF) or (
                        (tl and 0xFF00) + (tr and 0xFF00) + (tc and 0xFF00) + (bl and 0xFF00) + (br and 0xFF00) + (bc and 0xFF00) + (cl and 0xFF00) + (cr and 0xFF00) shr 3 and 0xFF00) or (
                        (tl and 0xFF0000) + (tr and 0xFF0000) + (tc and 0xFF0000) + (bl and 0xFF0000) + (br and 0xFF0000) + (bc and 0xFF0000) + (cl and 0xFF0000) + (cr and 0xFF0000) shr 3 and 0xFF0000)
            }
        }
        r /= 2
    }
    try {
        bitmapCopy.setPixels(pix, 0, w, 0, 0, w, h)
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return bitmapCopy
}