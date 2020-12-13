package com.dmikhov.usecases

import com.dmikhov.entities.FullMovieDetails
import com.dmikhov.usecases.entities.Result
import com.dmikhov.usecases.repository.IMoviesRepository

class MovieDetailsUseCase(
    private val moviesRepository: IMoviesRepository
) {
    fun getMovieDetails(movieId: Int): Result<FullMovieDetails> {
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