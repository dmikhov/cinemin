package com.dmikhov.repository.web

import com.dmikhov.entities.movie.Movie
import com.dmikhov.entities.movie.MovieCredits
import com.dmikhov.usecases.entities.Result

interface IWebMovieService {
    fun searchMovies(title: String): Result<List<Movie>>
    fun getMovie(movieId: Int): Result<Movie>
    fun getCredits(movieId: Int): Result<MovieCredits>
}