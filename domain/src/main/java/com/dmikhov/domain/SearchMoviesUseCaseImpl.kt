package com.dmikhov.domain

import com.dmikhov.domain.abs.SearchMoviesUseCase
import com.dmikhov.entities.movie.Movie
import com.dmikhov.domain.entities.Result
import com.dmikhov.domain.repository.MoviesRepository

class SearchMoviesUseCaseImpl (
    private val moviesRepository: MoviesRepository
): SearchMoviesUseCase {
    override fun searchMoviesByTitle(title: String): Result<List<Movie>> {
        return moviesRepository.searchMovie(title)
    }
}