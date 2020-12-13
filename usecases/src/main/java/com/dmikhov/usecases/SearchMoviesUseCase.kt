package com.dmikhov.usecases

import com.dmikhov.entities.Movie
import com.dmikhov.entities.Result
import com.dmikhov.usecases.repository.IMoviesRepository

class SearchMoviesUseCase (
    private val moviesRepository: IMoviesRepository
) {
    fun searchMoviesByTitle(title: String): Result<List<Movie>> {
        return moviesRepository.searchMovie(title)
    }
}