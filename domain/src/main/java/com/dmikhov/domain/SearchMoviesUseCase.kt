package com.dmikhov.domain

import com.dmikhov.entities.movie.Movie
import com.dmikhov.domain.entities.Result
import com.dmikhov.domain.repository.IMoviesRepository

class SearchMoviesUseCase (
    private val moviesRepository: IMoviesRepository
) {
    fun searchMoviesByTitle(title: String): Result<List<Movie>> {
        return moviesRepository.searchMovie(title)
    }
}