package com.dmikhov.cinemin.screens.about

import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dmikhov.cinemin.Constants
import com.dmikhov.cinemin.R
import com.dmikhov.cinemin.extensions.setHtmlText
import com.dmikhov.cinemin.extensions.setStartCropMatrix
import com.dmikhov.cinemin.screens.base.BaseFragment
import com.dmikhov.cinemin.screens.web.WebViewFragment
import com.dmikhov.cinemin.utils.HtmlLinksUtils
import com.dmikhov.cinemin.utils.IntentUtils
import kotlinx.android.synthetic.main.fragment_about.*
import java.util.*


class AboutFragment : BaseFragment() {
    private lateinit var animationComposer: AboutFragmentAnimationComposer
    private val mainHandler = Handler()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        animationComposer = AboutFragmentAnimationComposer(view)
        populateViews()
    }

    private fun populateViews() {
        toolbar.setNavigationOnClickListener {
            mainActivity?.onBackPressed()
        }
        backgroundImageView.setStartCropMatrix()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            descriptionTextView.justificationMode = Layout.JUSTIFICATION_MODE_INTER_WORD
            tmdbAttributionTextView.justificationMode = Layout.JUSTIFICATION_MODE_INTER_WORD
            flaticonAttributionTextView.justificationMode = Layout.JUSTIFICATION_MODE_INTER_WORD
        }
        HtmlLinksUtils.linkifyHtmlTextView(
            tmdbAttributionTextView,
            getString(R.string.about_tmdb_attribution),
            this::onLinkClicked
        )
        HtmlLinksUtils.linkifyHtmlTextView(
            flaticonAttributionTextView,
            getString(R.string.about_flaticon_attribution),
            this::onLinkClicked
        )
        HtmlLinksUtils.linkifyHtmlTextView(
            pixabayAttributionTextView,
            getString(R.string.about_pixabay_attribution),
            this::onLinkClicked
        )
        val appNameTypeface =
            Typeface.createFromAsset(activity?.assets, Constants.APP_NAME_FONT_PATH)
        appNameTextView.typeface = appNameTypeface
        appNameTextView.text = getString(R.string.app_name).toLowerCase(Locale.US)
        supportLayout.setOnClickListener {
            IntentUtils.openEmailApp(requireActivity(), Constants.SUPPORT_EMAIL)
        }
        privacyPolicyLayout.setOnClickListener {
            mainActivity?.openWebFragment(
                getString(R.string.privacy_policy),
                Constants.PRIVACY_POLICY_URL
            )
        }
        termsLayout.setOnClickListener {
            mainActivity?.openWebFragment(
                getString(R.string.terms_and_conditions),
                Constants.TERMS_AND_CONDITIONS_URL
            )
        }
        animationComposer.hide()
        mainHandler.post {
            animationComposer.show()
        }
    }

    private fun onLinkClicked(url: String) {
        IntentUtils.openInWebBrowser(requireContext(), url)
    }

    companion object {
        fun newInstance() = AboutFragment()
    }
}