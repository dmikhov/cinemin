package com.dmikhov.cinemin.screens.search

import android.os.Bundle
import android.os.Handler
import android.view.*
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.manager.SupportRequestManagerFragment
import com.dmikhov.cinemin.R
import com.dmikhov.cinemin.extensions.*
import com.dmikhov.cinemin.screens.base.BaseFragment
import com.dmikhov.viewmodel.SearchMoviesViewModel
import kotlinx.android.synthetic.main.fragment_movie_search.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : BaseFragment() {
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
        mainActivity?.setSupportActionBar(toolbar)
        mainActivity?.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        setHasOptionsMenu(true)
        toolbar.setNavigationOnClickListener {
            mainActivity?.onBackPressed()
        }
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
        val searchIsOnTopOfStack = parentFragmentManager.fragments
            .lastOrNull { it::class.java != SupportRequestManagerFragment::class.java }?.javaClass == javaClass
        if (searchIsOnTopOfStack) {
            searchEditText.showKeyboard()
        }
        moviesAdapter.onMovieClicked = { movie ->
            searchEditText.hideKeyboard()
            mainActivity?.openHomeFragment(movie.id, movie.title)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_item_about -> {
                searchEditText.hideKeyboard()
                mainActivity?.openAboutFragment()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        searchEditText?.hideKeyboard()
        super.onDestroyView()
    }

    companion object {
        private const val SEARCH_DELAY = 500L

        fun newInstance(): SearchFragment {
            return SearchFragment()
        }
    }
}