package com.dmikhov.repository

import com.dmikhov.entities.Movie
import com.dmikhov.entities.MovieCredits
import com.dmikhov.usecases.entities.Result
import com.dmikhov.repository.web.IWebMovieService
import com.dmikhov.usecases.repository.IMoviesRepository

class MoviesRepository(
    private val webMovieService: IWebMovieService
): IMoviesRepository {
    override fun searchMovie(title: String): Result<List<Movie>> {
        return webMovieService.searchMovies(title)
    }

    override fun getMovie(movieId: Int): Result<Movie> {
        return webMovieService.getMovie(movieId)
    }

    override fun getCredits(movieId: Int): Result<MovieCredits> {
        return webMovieService.getCredits(movieId)
    }
}