package com.dmikhov.domain.repository

import com.dmikhov.entities.movie.Movie
import com.dmikhov.entities.movie.MovieCredits
import com.dmikhov.domain.entities.Result

interface IMoviesRepository {
    fun searchMovie(title: String): Result<List<Movie>>
    fun getMovie(movieId: Long): Result<Movie>
    fun getCredits(movieId: Long): Result<MovieCredits>
}