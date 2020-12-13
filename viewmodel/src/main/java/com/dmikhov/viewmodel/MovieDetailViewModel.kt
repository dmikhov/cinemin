package com.dmikhov.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dmikhov.entities.movie.Credit
import com.dmikhov.entity.MovieDetailsUI
import com.dmikhov.usecases.CalculateWeightedPriceUseCase
import com.dmikhov.usecases.MovieDetailsUseCase
import com.dmikhov.utils.DateUtils
import com.dmikhov.utils.toCurrency
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDetailViewModel(
    private val detailsUseCase: MovieDetailsUseCase,
    private val calculateWeightedPriceUseCase: CalculateWeightedPriceUseCase
) : ViewModel() {
    val isLoadingLive = MutableLiveData<Boolean>()
    val movieDetailsLive = MutableLiveData<MovieDetailsUI>()

    fun loadDetails(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            isLoadingLive.postValue(true)
            val result = detailsUseCase.getMovieDetails(movieId)
            if (result.isSuccess()) {
                val fullMovieDetails = result.requirePayload()
                val movie = fullMovieDetails.movie
                val director = fullMovieDetails.credits.crew.find { it.job == Credit.JOB_DIRECTOR }
                val budget = movie.budget?.toCurrency()
                val revenue = movie.revenue?.toCurrency()
                val releaseYear = DateUtils.getYearFromReleaseDate(movie.releaseDate)
                val currentYear = DateUtils.getCurrentYear()
                val weightedBudget = calculateWeightedPriceUseCase.calculateWeightedPrice(
                    movie.budget?.toDouble(), releaseYear, currentYear
                )?.toCurrency()
                val weightedRevenue = calculateWeightedPriceUseCase.calculateWeightedPrice(
                    movie.revenue?.toDouble(), releaseYear, currentYear
                )?.toCurrency()
                val movieUI = MovieDetailsUI(
                    movie.id,
                    movie.title,
                    movie.posterUrl,
                    movie.releaseDate,
                    releaseYear.toString(),
                    currentYear.toString(),
                    budget,
                    revenue,
                    weightedBudget,
                    weightedRevenue,
                    movie.runtime?.toString(),
                    movie.rating?.toString(),
                    movie.adult,
                    movie.tagline,
                    movie.overview,
                    director?.name
                )
                movieDetailsLive.postValue(movieUI)
            } else {
                movieDetailsLive.postValue(null)
            }
            isLoadingLive.postValue(false)
        }
    }
}