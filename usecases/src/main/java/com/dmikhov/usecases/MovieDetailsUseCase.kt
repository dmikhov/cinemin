package com.dmikhov.usecases

import com.dmikhov.entities.Movie
import com.dmikhov.entities.Result
import com.dmikhov.usecases.repository.IMoviesRepository

class MovieDetailsUseCase(
    private val moviesRepository: IMoviesRepository
) {
    fun getMovie(movieId: Int): Result<Movie> {
        return moviesRepository.getMovie(movieId)
    }
}