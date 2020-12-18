package com.dmikhov.cinemin.screens.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dmikhov.cinemin.R
import com.dmikhov.cinemin.screens.base.BaseFragment
import com.dmikhov.cinemin.utils.IntentUtils
import com.dmikhov.cinemin.extensions.setStartCropMatrix
import kotlinx.android.synthetic.main.fragment_about.*


class AboutFragment: BaseFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        populateViews()
    }

    private fun populateViews() {
        toolbar.setNavigationOnClickListener {
            mainActivity?.onBackPressed()
        }
        backgroundImageView.setStartCropMatrix()
        emailLayout.setOnClickListener {
            IntentUtils.openEmailApp(requireActivity(), getString(R.string.about_email))
        }
        privacyPolicyLayout.setOnClickListener {

        }
    }

    companion object {
        fun newInstance() = AboutFragment()
    }
}