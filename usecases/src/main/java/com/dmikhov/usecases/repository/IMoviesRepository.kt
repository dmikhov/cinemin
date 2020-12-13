package com.dmikhov.usecases.repository

import com.dmikhov.entities.Movie
import com.dmikhov.entities.Result

interface IMoviesRepository {
    fun searchMovie(title: String): Result<List<Movie>>
    fun getMovie(movieId: Int): Result<Movie>
}