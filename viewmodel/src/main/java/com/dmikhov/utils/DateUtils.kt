package com.dmikhov.utils

import java.util.*

object DateUtils {
    fun getCurrentYear() = Calendar.getInstance().get(Calendar.YEAR).toString()

    fun getYearFromReleaseDate(release: String?) = release?.take(4)
}