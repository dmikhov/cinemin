package com.dmikhov.usecases

import com.dmikhov.entities.movie.Movie
import com.dmikhov.usecases.entities.Result
import com.dmikhov.usecases.repository.IMoviesRepository

class SearchMoviesUseCase (
    private val moviesRepository: IMoviesRepository
) {
    fun searchMoviesByTitle(title: String): Result<List<Movie>> {
        return moviesRepository.searchMovie(title)
    }
}