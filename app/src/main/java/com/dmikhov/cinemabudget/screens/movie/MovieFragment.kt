package com.dmikhov.cinemabudget.screens.movie

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.dmikhov.cinemabudget.R
import com.dmikhov.cinemabudget.extensions.blur
import com.dmikhov.cinemabudget.extensions.cropMargin
import com.dmikhov.cinemabudget.extensions.setColoredKeyValueText
import com.dmikhov.cinemabudget.extensions.setStartCropMatrix
import com.dmikhov.cinemabudget.screens.base.BaseFragment
import com.dmikhov.cinemabudget.utils.DialogUtils
import com.dmikhov.entity.MovieDetailsUI
import com.dmikhov.viewmodel.MovieDetailViewModel
import kotlinx.android.synthetic.main.fragment_movie.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieFragment : BaseFragment() {
    private lateinit var animationComposer: MovieFragmentAnimationComposer
    private val movieDetailsViewModel: MovieDetailViewModel by viewModel()
    private var movieId: Int? = null
    private var movieTitle: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initData()
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        animationComposer = MovieFragmentAnimationComposer(view)
        populateViews()
        initObservers()
    }

    private fun initData() {
        arguments?.apply {
            movieId = getInt(ARG_MOVIE_ID)
            movieTitle = getString(ARG_MOVIE_TITLE)
        }
        movieId?.let {
            if (it != UNKNOWN_MOVIE_ID) {
                movieDetailsViewModel.loadDetails(it)
            }
        }
    }

    private fun initObservers() {
        movieDetailsViewModel.isLoadingLive.observe(viewLifecycleOwner, { isLoading ->
            if (isLoading == true) {
                progressIndicator.visibility = View.VISIBLE
            } else {
                progressIndicator.visibility = View.GONE
            }
        })
        movieDetailsViewModel.movieDetailsLive.observe(viewLifecycleOwner, { movie: MovieDetailsUI? ->
            if (movie != null) {
                populateMovieDetails(movie)
            }
        })
        movieDetailsViewModel.onErrorInvoked.observeSingleEvent(viewLifecycleOwner, {
            if (it == true) {
                DialogUtils.showErrorDialog(requireContext(), getString(R.string.popup_something_went_wrong)) {
                    mainActivity?.onBackPressed()
                }
            }
        })
    }

    private fun populateViews() {
        mainActivity?.setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener {
            mainActivity?.onBackPressed()
        }
        toolbar.title = movieTitle ?: getString(R.string.app_name)
        placeholderBackgroundImageView.setStartCropMatrix()
        movieBackgroundImageView.visibility = View.GONE
        placeholderBackgroundImageView.visibility = View.VISIBLE
        animationComposer.hideMovieDetails()
    }

    private fun populateMovieDetails(movie: MovieDetailsUI) {
        val keyColor = ContextCompat.getColor(requireContext(), R.color.colorPlainText)
        val valueColor = ContextCompat.getColor(requireContext(), R.color.colorAccentText)
        budgetTextView.text = movie.budget ?: getString(R.string.na)
        revenueTextView.text = movie.revenue ?: getString(R.string.na)
        weightedBudgetTextView.text = movie.weightedBudget ?: getString(R.string.na)
        weightedRevenueTextView.text = movie.weightedRevenue ?: getString(R.string.na)
        titleTextView.text = movie.title
        if (movie.releaseYear != null) {
            moneyDetailsOverlayView.visibility = View.VISIBLE
            moneyDetailsOverlayTextView.text =
                getString(R.string.format_in_dollars_overlay, movie.releaseYear)
        } else {
            moneyDetailsOverlayView.visibility = View.GONE
        }
        if (movie.currentYear != null) {
            weightedMoneyDetailsOverlayView.visibility = View.VISIBLE
            weightedMoneyDetailsOverlayTextView.text =
                getString(R.string.format_in_dollars_overlay, movie.currentYear)
        } else {
            weightedMoneyDetailsOverlayView.visibility = View.GONE
        }
        releaseTextView.setColoredKeyValueText(
            getString(R.string.key_release),
            keyColor,
            movie.releaseDate ?: getString(R.string.na),
            valueColor
        )
        directorTextView.setColoredKeyValueText(
            getString(R.string.key_director),
            keyColor,
            movie.director ?: getString(R.string.na),
            valueColor
        )
        val runtime = if (movie.runtime != null) {
            getString(R.string.format_runtime, movie.runtime.toString())
        } else {
            getString(R.string.na)
        }
        runtimeTextView.setColoredKeyValueText(
            getString(R.string.key_runtime),
            keyColor,
            runtime,
            valueColor
        )
        ratingTextView.setColoredKeyValueText(
            getString(R.string.key_rating),
            keyColor,
            movie.rating ?: getString(R.string.na),
            valueColor
        )
        if (!movie.overview.isNullOrEmpty()) {
            descriptionCardView.visibility = View.VISIBLE
            descriptionTextView.text = movie.overview
        } else {
            descriptionCardView.visibility = View.GONE
        }
        if (movie.posterUrl != null) {
            Glide.with(this)
                .asBitmap()
                .load(movie.posterUrl)
                .into(object : CustomTarget<Bitmap>() {
                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap>?
                    ) {
                        posterImageView.setImageBitmap(resource)
                        movieBackgroundImageView.setImageBitmap(resource.blur())
                        movieBackgroundImageView.visibility = View.VISIBLE
                        placeholderBackgroundImageView.visibility = View.GONE
                        animationComposer.animateMovieDetailsAppearing()
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {
                        posterImageView?.setImageBitmap(null)
                        movieBackgroundImageView?.setImageBitmap(null)
                    }
                })
        } else {
            posterImageView.setImageBitmap(null) // set placeholder
            animationComposer.animateMovieDetailsAppearing()
        }
    }

    companion object {
        private const val ARG_MOVIE_ID = "ARG_MOVIE_ID"
        private const val ARG_MOVIE_TITLE = "ARG_MOVIE_TITLE"
        private const val UNKNOWN_MOVIE_ID = -1

        fun newInstance(movieId: Int? = null, movieTitle: String? = null): MovieFragment = MovieFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG_MOVIE_ID, movieId ?: UNKNOWN_MOVIE_ID)
                putString(ARG_MOVIE_TITLE, movieTitle)
            }
        }
    }
}