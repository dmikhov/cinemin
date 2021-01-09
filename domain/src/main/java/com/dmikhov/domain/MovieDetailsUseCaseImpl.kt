package com.dmikhov.domain

import com.dmikhov.domain.abs.MovieDetailsUseCase
import com.dmikhov.entities.movie.FullMovieDetails
import com.dmikhov.domain.entities.Result
import com.dmikhov.domain.repository.MoviesRepository

class MovieDetailsUseCaseImpl(
    private val moviesRepository: MoviesRepository
): MovieDetailsUseCase {
    override fun getMovieDetails(movieId: Long): Result<FullMovieDetails> {
        val movieResult = moviesRepository.getMovie(movieId)
        val creditsResult = moviesRepository.getCredits(movieId)
        return if (movieResult.isSuccess() && creditsResult.isSuccess()) {
            Result(FullMovieDetails(movieResult.requirePayload(), creditsResult.requirePayload()))
        } else {
            val errorCode = if (!movieResult.isSuccess()) {
                movieResult.errorCode
            } else {
                creditsResult.errorCode
            }
            Result(errorCode = errorCode)
        }
    }
}