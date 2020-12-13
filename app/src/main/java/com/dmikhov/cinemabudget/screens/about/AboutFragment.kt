package com.dmikhov.cinemabudget.screens.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import com.dmikhov.cinemabudget.R
import com.dmikhov.cinemabudget.screens.base.BaseFragment
import com.dmikhov.cinemabudget.utils.IntentUtils
import com.dmikhov.cinemabudget.extensions.blur
import com.dmikhov.cinemabudget.extensions.cropMargin
import com.dmikhov.cinemabudget.extensions.setStartCropMatrix
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
        val backgroundBitmap = ContextCompat.getDrawable(requireContext(), R.drawable.img_posters)?.toBitmap()
        backgroundImageView.setImageBitmap(backgroundBitmap?.blur()?.cropMargin())
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