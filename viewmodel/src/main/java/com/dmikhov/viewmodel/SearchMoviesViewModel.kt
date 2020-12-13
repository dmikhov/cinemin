package com.dmikhov.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dmikhov.entities.movie.Movie
import com.dmikhov.usecases.SearchMoviesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchMoviesViewModel(
    private val searchMoviesUseCase: SearchMoviesUseCase
) : ViewModel() {
    val isLoadingLive = MutableLiveData<Boolean>()
    val moviesLive = MutableLiveData<MutableList<Movie>>()

    fun searchMovies(title: String) {
        if (title.isNotBlank()) {
            viewModelScope.launch(Dispatchers.IO) {
                isLoadingLive.postValue(true)
                val result = searchMoviesUseCase.searchMoviesByTitle(title)
                if (result.isSuccess()) {
                    moviesLive.postValue(result.payload?.toMutableList())
                } else {
                    moviesLive.postValue(mutableListOf())
                }
                isLoadingLive.postValue(false)
            }
        } else {
            moviesLive.postValue(mutableListOf())
        }
    }
}