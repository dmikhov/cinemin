package com.dmikhov.cinemin.screens.web

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import com.dmikhov.cinemin.R
import com.dmikhov.cinemin.screens.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_about.*
import kotlinx.android.synthetic.main.fragment_web.*
import kotlinx.android.synthetic.main.fragment_web.toolbar

class WebViewFragment: BaseFragment() {
    private var title: String? = null
    private var url: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initData()
        return inflater.inflate(R.layout.fragment_web, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        populateViews()
    }

    private fun initData() {
        title = arguments?.getString(ARG_TITLE)
        url = arguments?.getString(ARG_URL)
    }

    private fun populateViews() {
        toolbar.title = title
        toolbar.setNavigationOnClickListener {
            mainActivity?.onBackPressed()
        }
        webView.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                progressBar.visibility = View.VISIBLE
            }

            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)
                progressBar.visibility = View.GONE
            }
        }
        webView.loadUrl(url)
    }

    companion object {
        private const val ARG_TITLE = "ARG_TITLE"
        private const val ARG_URL = "ARG_URL"

        fun newInstance(title: String, url: String) = WebViewFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_TITLE, title)
                putString(ARG_URL, url)
            }
        }
    }
}