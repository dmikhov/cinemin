package com.dmikhov.cinemin.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import com.dmikhov.cinemin.R

object IntentUtils {
    fun openInWebBrowser(context: Context, url: String) {
        val intent = Intent()
        intent.action = Intent.ACTION_VIEW
        intent.data = Uri.parse(url)
        context.startActivity(intent)
    }

    fun openEmailApp(context: Context, email: String, text: String? = null, subject: String? = null) {
        val intent = Intent(
            Intent.ACTION_SENDTO, Uri.fromParts(
            "mailto", email, null))
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
        intent.putExtra(Intent.EXTRA_TEXT, text)
        context.startActivity(
            Intent.createChooser(intent,
                context.getString(R.string.about_email_selector_title)))
    }
}