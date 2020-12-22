package com.dmikhov.cinemin.utils

import android.graphics.Color
import android.text.Html
import android.text.SpannableStringBuilder
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.URLSpan
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.dmikhov.cinemin.R

object HtmlLinksUtils {
    private fun makeLinkClickable(strBuilder: SpannableStringBuilder, span: URLSpan?, onLinkClicked: ((url: String) -> Unit)? = null) {
        val start = strBuilder.getSpanStart(span)
        val end = strBuilder.getSpanEnd(span)
        val flags = strBuilder.getSpanFlags(span)
        val clickable: ClickableSpan = object : ClickableSpan() {
            override fun onClick(view: View) {
                val url = span?.url ?: ""
                onLinkClicked?.invoke(url)
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.isUnderlineText = false
            }
        }
        strBuilder.setSpan(clickable, start, end, flags)
        strBuilder.removeSpan(span)
    }

    fun linkifyHtmlTextView(htmlTextView: TextView, html: String?, onLinkClicked: ((url: String) -> Unit)? = null) {
        val sequence: CharSequence = Html.fromHtml(html)
        val strBuilder = SpannableStringBuilder(sequence)
        val urls = strBuilder.getSpans(0, sequence.length, URLSpan::class.java)
        for (span in urls) {
            makeLinkClickable(strBuilder, span, onLinkClicked)
        }
        htmlTextView.text = strBuilder
        htmlTextView.movementMethod = LinkMovementMethod.getInstance()
        htmlTextView.highlightColor = Color.TRANSPARENT
        htmlTextView.setLinkTextColor(ContextCompat.getColor(htmlTextView.context, R.color.colorAccent))
    }
}