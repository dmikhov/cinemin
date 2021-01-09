package com.dmikhov.domain.abs

import com.dmikhov.domain.entities.Result
import com.dmikhov.entities.movie.Movie

interface SearchMoviesUseCase {
    fun searchMoviesByTitle(title: String): Result<List<Movie>>
}