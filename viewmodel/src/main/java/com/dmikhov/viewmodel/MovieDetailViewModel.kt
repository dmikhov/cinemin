package com.dmikhov.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dmikhov.entities.Movie
import com.dmikhov.usecases.MovieDetailsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MovieDetailViewModel(
    private val detailsUseCase: MovieDetailsUseCase
): ViewModel() {
    val isLoadingLive = MutableLiveData<Boolean>()
    val movieDetailsLive = MutableLiveData<Movie>()

    fun loadDetails(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            isLoadingLive.postValue(true)
            val result = detailsUseCase.getMovie(movieId)
            if (result.isSuccess()) {
                movieDetailsLive.postValue(result.payload)
            } else {
                movieDetailsLive.postValue(null)
            }
            isLoadingLive.postValue(false)
        }
    }
}