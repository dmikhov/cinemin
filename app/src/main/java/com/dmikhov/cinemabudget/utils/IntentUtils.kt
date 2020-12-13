package com.dmikhov.cinemabudget.utils

import android.app.Activity
import android.content.Intent
import android.net.Uri
import com.dmikhov.cinemabudget.R

object IntentUtils {
    fun openInWebBrowser(activity: Activity, url: String) {
        val intent = Intent()
        intent.action = Intent.ACTION_VIEW
        intent.data = Uri.parse(url)
        activity.startActivity(intent)
    }

    fun openEmailApp(activity: Activity, email: String, text: String? = null, subject: String? = null) {
        val intent = Intent(
            Intent.ACTION_SENDTO, Uri.fromParts(
            "mailto", email, null))
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
        intent.putExtra(Intent.EXTRA_TEXT, text)
        activity.startActivity(
            Intent.createChooser(intent,
            activity.getString(R.string.about_email_selector_title)))
    }
}