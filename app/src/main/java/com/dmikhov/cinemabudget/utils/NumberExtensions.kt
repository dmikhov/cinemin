package com.dmikhov.cinemabudget.utils

import java.text.NumberFormat
import java.util.*

fun Number.toCurrency(currencyCode: String = "USD"): String {
    val format: NumberFormat = NumberFormat.getCurrencyInstance()
    format.maximumFractionDigits = 0
    format.currency = Currency.getInstance(currencyCode)
    return format.format(this)
}