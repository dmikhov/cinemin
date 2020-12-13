package com.dmikhov.cinemabudget.screens.movie

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.TransitionManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.dmikhov.cinemabudget.R
import com.dmikhov.cinemabudget.screens.MainActivity
import com.dmikhov.cinemabudget.animation.LittleBounceInterpolator
import com.dmikhov.cinemabudget.screens.base.BaseFragment
import com.dmikhov.cinemabudget.utils.*
import com.dmikhov.entities.Movie
import com.dmikhov.viewmodel.MovieDetailViewModel
import kotlinx.android.synthetic.main.fragment_movie.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieFragment : BaseFragment() {
    private lateinit var animationComposer: MovieFragmentAnimationComposer
    private val movieDetailsViewModel: MovieDetailViewModel by viewModel()
    private var movieId: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("madtag", "MovieFragment onCreateView")
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
        }
        movieId?.let {
            if (it != UNKNOWN_MOVIE_ID) {
                movieDetailsViewModel.loadDetails(it)
            }
        }
    }

    private fun initObservers() {
        movieDetailsViewModel.isLoadingLive.observe(viewLifecycleOwner, {

        })
        movieDetailsViewModel.movieDetailsLive.observe(viewLifecycleOwner, { movie ->
            populateMovieDetails(movie)
        })
    }

    private fun populateViews() {
        val postersBgBitmap =
            ContextCompat.getDrawable(requireContext(), R.drawable.img_posters)?.toBitmap()?.blur()
                ?.cropMargin()
        placeholderBackgroundImageView.setImageBitmap(postersBgBitmap)
        placeholderBackgroundImageView.setStartCropMatrix()
        movieBackgroundImageView.visibility = View.GONE
        placeholderBackgroundImageView.visibility = View.VISIBLE
        animationComposer.hideMovieDetails()
    }

    private fun populateMovieDetails(movie: Movie) {
        val keyColor = ContextCompat.getColor(requireContext(), R.color.colorPlainText)
        val valueColor = ContextCompat.getColor(requireContext(), R.color.colorAccentText)
        budgetTextView.text = movie.budget?.toCurrency()
        revenueTextView.text = movie.revenue?.toCurrency()
        weightedBudgetTextView.text = movie.budget?.toCurrency()
        weightedRevenueTextView.text = movie.revenue?.toCurrency()
        titleTextView.text = movie.title
        moneyDetailsOverlayTextView.text =
            getString(R.string.format_in_dollars_overlay, movie.releaseDate?.take(4))
        weightedMoneyDetailsOverlayTextView.text =
            getString(R.string.format_in_dollars_overlay, DateUtils.getCurrentYear())
        releaseTextView.setColoredKeyValueText(
            getString(R.string.key_release),
            keyColor,
            movie.releaseDate ?: getString(R.string.na),
            valueColor
        )
        directorTextView.setColoredKeyValueText(
            getString(R.string.key_director),
            keyColor,
            "K. Nolan",
            valueColor
        )
        directorTextView.visibility = View.GONE // remove for now
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
            getString(R.string.key_imdb_rating),
            keyColor,
            movie.rating?.toString() ?: getString(R.string.na),
            valueColor
        )
        if (!movie.overview.isNullOrEmpty()) {
            descriptionCardView.visibility = View.VISIBLE
            descriptionTextView.text = movie.overview
        } else {
            descriptionCardView.visibility = View.GONE
        }
        Glide.with(this)
            .asBitmap()
            .load(movie.posterUrl)
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
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
    }

    companion object {
        private const val ARG_MOVIE_ID = "ARG_MOVIE_ID"
        private const val UNKNOWN_MOVIE_ID = -1

        fun newInstance(movieId: Int? = null): MovieFragment = MovieFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG_MOVIE_ID, movieId ?: UNKNOWN_MOVIE_ID)
            }
        }
    }
}