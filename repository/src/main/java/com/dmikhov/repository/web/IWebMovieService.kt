package com.dmikhov.repository.web

import com.dmikhov.entities.Movie
import com.dmikhov.entities.Result

interface IWebMovieService {
    fun searchMovies(title: String): Result<List<Movie>>
    fun getMovie(movieId: Int): Result<Movie>
}