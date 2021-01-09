package com.dmikhov.data.web

import com.dmikhov.entities.movie.Movie
import com.dmikhov.entities.movie.MovieCredits
import com.dmikhov.domain.entities.Result

interface WebMovieService {
    fun searchMovies(title: String): Result<List<Movie>>
    fun getMovie(movieId: Long): Result<Movie>
    fun getCredits(movieId: Long): Result<MovieCredits>
}