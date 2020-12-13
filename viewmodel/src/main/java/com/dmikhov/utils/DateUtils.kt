package com.dmikhov.utils

import java.util.*

object DateUtils {
    fun getCurrentYear() = Calendar.getInstance().get(Calendar.YEAR)

    fun getYearFromReleaseDate(release: String?) = try {
        release?.take(4)?.toInt()
    } catch (e: Exception) {
        null
    }
}