package com.dmikhov.utils

import java.text.NumberFormat
import java.util.*

fun Number.toCurrency(): String? {
    return try {
        val format: NumberFormat = NumberFormat.getCurrencyInstance(Locale.US)
        format.maximumFractionDigits = 0
        format.format(this)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}