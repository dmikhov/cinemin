package com.dmikhov.utils

import java.text.NumberFormat
import java.util.*

fun Number.toCurrency(currencyCode: String = "USD"): String? {
    return try {
        val format: NumberFormat = NumberFormat.getCurrencyInstance()
        format.maximumFractionDigits = 0
        format.currency = Currency.getInstance(currencyCode)
        format.format(this)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}