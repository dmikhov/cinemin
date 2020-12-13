package com.dmikhov.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dmikhov.entities.Movie
import com.dmikhov.entity.MovieDetailsUI
import com.dmikhov.usecases.MovieDetailsUseCase
import com.dmikhov.utils.DateUtils
import com.dmikhov.utils.toCurrency
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MovieDetailViewModel(
    private val detailsUseCase: MovieDetailsUseCase
): ViewModel() {
    val isLoadingLive = MutableLiveData<Boolean>()
    val movieDetailsLive = MutableLiveData<MovieDetailsUI>()

    fun loadDetails(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            isLoadingLive.postValue(true)
            val result = detailsUseCase.getMovie(movieId)
            val movie = result.payload
            if (result.isSuccess() && movie != null) {
                val budget = movie.budget?.toCurrency()
                val revenue = movie.revenue?.toCurrency()
                val weightedBudget = movie.budget?.toCurrency()
                val weightedRevenue = movie.revenue?.toCurrency()
                val releaseYear = DateUtils.getYearFromReleaseDate(movie.releaseDate)
                val currentYear = DateUtils.getCurrentYear()
                val director = "Unknown Director"
                val movieUI = MovieDetailsUI(movie.id, movie.title, movie.posterUrl,
                    movie.releaseDate, releaseYear, currentYear, budget, revenue, weightedBudget,
                    weightedRevenue, movie.runtime?.toString(), movie.rating?.toString(),
                    movie.adult, movie.tagline, movie.overview, director
                )
                movieDetailsLive.postValue(movieUI)
            } else {
                movieDetailsLive.postValue(null)
            }
            isLoadingLive.postValue(false)
        }
    }
}