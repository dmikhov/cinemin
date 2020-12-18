package com.dmikhov.cinemin.utils

import android.app.AlertDialog
import android.content.Context
import com.dmikhov.cinemin.R

object DialogUtils {
    fun showErrorDialog(context: Context, message: String, okPressed: (() -> Unit)? = null) {
        val builder = AlertDialog.Builder(context)
        builder.setMessage(message)
        builder.setTitle(context.getString(R.string.popup_error))
        builder.setCancelable(false)
        builder.setPositiveButton(context.getString(R.string.popup_ok)) { _, _ ->
            okPressed?.invoke()
        }
        val dialog = builder.create()
        dialog.show()
    }
}