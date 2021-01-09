package com.dmikhov.data

import com.dmikhov.entities.movie.Movie
import com.dmikhov.entities.movie.MovieCredits
import com.dmikhov.domain.entities.Result
import com.dmikhov.data.web.WebMovieService
import com.dmikhov.domain.repository.MoviesRepository

class MoviesRepositoryImpl(
    private val webMovieService: WebMovieService
): MoviesRepository {
    override fun searchMovie(title: String): Result<List<Movie>> {
        return webMovieService.searchMovies(title)
    }

    override fun getMovie(movieId: Long): Result<Movie> {
        return webMovieService.getMovie(movieId)
    }

    override fun getCredits(movieId: Long): Result<MovieCredits> {
        return webMovieService.getCredits(movieId)
    }
}