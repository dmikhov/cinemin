package com.dmikhov.cinemabudget.screens.search

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.dmikhov.cinemabudget.R
import com.dmikhov.cinemabudget.screens.MainActivity
import com.dmikhov.cinemabudget.screens.base.BaseFragment
import com.dmikhov.cinemabudget.utils.*
import com.dmikhov.viewmodel.SearchMoviesViewModel
import kotlinx.android.synthetic.main.fragment_movie_search.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : BaseFragment() {
    private val mainActivity get() = activity as? MainActivity
    private val searchMovieViewModel: SearchMoviesViewModel by viewModel()
    private val moviesAdapter by lazy { MoviesAdapter() }
    private val mainHandler = Handler()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        populateViews()
        initObservers()
    }

    private fun initObservers() {
        searchMovieViewModel.moviesLive.observe(viewLifecycleOwner, { movies ->
            if (movies.isNotEmpty()) {
                moviesRecyclerView.visibility = View.VISIBLE
                errorLayout.visibility = View.GONE
                hintLayout.visibility = View.GONE
            } else {
                moviesRecyclerView.visibility = View.GONE
                if (searchEditText.text.isNullOrEmpty()) {
                    errorLayout.visibility = View.GONE
                    hintLayout.visibility = View.VISIBLE
                } else {
                    errorLayout.visibility = View.VISIBLE
                    hintLayout.visibility = View.GONE
                }
            }
            val launchLayoutAnimation = moviesAdapter.movies.isEmpty()
            moviesAdapter.movies = movies
            if (launchLayoutAnimation) {
                moviesRecyclerView.scheduleLayoutAnimation()
            }
        })
    }

    private fun populateViews() {
        val originalBitmap = ContextCompat.getDrawable(requireContext(), R.drawable.img_posters)?.toBitmap()
        backgroundImageView.setImageBitmap(originalBitmap?.blur()?.cropMargin())
        backgroundImageView.setStartCropMatrix()

        moviesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        moviesRecyclerView.adapter = moviesAdapter
        searchEditText.doAfterTextChanged {
            mainHandler.removeCallbacksAndMessages(null)
            mainHandler.postDelayed({
                val movieTitle = it?.toString() ?: ""
                searchMovieViewModel.searchMovies(movieTitle)
            }, SEARCH_DELAY)
        }
        searchEditText.showKeyboard()
        moviesAdapter.onMovieClicked = { movie ->
            searchEditText.hideKeyboard()
            mainActivity?.openHomeFragment(movie.id)
        }
    }

    companion object {
        private const val SEARCH_DELAY = 500L

        fun newInstance(): SearchFragment {
            return SearchFragment()
        }
    }
}