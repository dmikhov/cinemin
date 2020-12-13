package com.dmikhov.cinemabudget.utils

import java.util.*

object DateUtils {
    fun getCurrentYear() = Calendar.getInstance().get(Calendar.YEAR).toString()
}