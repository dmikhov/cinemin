package com.dmikhov.cinemabudget.extensions

import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.widget.TextView
import androidx.annotation.ColorInt

fun TextView.setColoredKeyValueText(key: String, @ColorInt keyColor: Int,
                                   value: String, @ColorInt valueColor: Int) {
    val wordToSpan = SpannableString("$key$value")
    wordToSpan.setSpan(
        ForegroundColorSpan(keyColor), 0,
        key.length, android.text.Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    wordToSpan.setSpan(
        ForegroundColorSpan(valueColor), key.length,
        wordToSpan.length, android.text.Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    text = wordToSpan
}