package com.dmikhov.cinemabudget.utils

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

const val SHOW_KEYBOARD_DELAY = 500L

fun EditText.showKeyboard() {
    postDelayed( {
        requestFocus()
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
    }, SHOW_KEYBOARD_DELAY)
}

fun EditText.hideKeyboard() {
    val activity = context as? Activity
    activity?.currentFocus?.let { focus ->
        val manager = activity
            .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        manager?.hideSoftInputFromWindow(focus.windowToken, 0)
    }
}