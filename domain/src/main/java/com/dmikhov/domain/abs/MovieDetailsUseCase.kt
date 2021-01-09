package com.dmikhov.domain.abs

import com.dmikhov.domain.entities.Result
import com.dmikhov.entities.movie.FullMovieDetails

interface MovieDetailsUseCase {
    fun getMovieDetails(movieId: Long): Result<FullMovieDetails>
}