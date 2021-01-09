package com.dmikhov.data.web.retrofit

import com.dmikhov.domain.entities.ErrorCodes
import com.dmikhov.entities.movie.Movie
import com.dmikhov.entities.movie.MovieCredits
import com.dmikhov.domain.entities.Result
import com.dmikhov.data.web.WebMovieService
import com.dmikhov.data.web.WebConstants
import com.dmikhov.data.web.entities.mapToMovies

class RetrofitMovieServiceImpl (
    private val moviesApi: RetrofitMovieServiceApi,
): WebMovieService {
    override fun searchMovies(title: String): Result<List<Movie>> {
        return try {
            val response = moviesApi.searchForMoviesAndPeople(WebConstants.MOVIEDB_API_KEY, title).execute()
            if (response.isSuccessful) {
                val searchResponse = response.body()
                Result(searchResponse?.results?.mapToMovies()?.toMutableList())
            } else {
                Result(errorCode = ErrorCodes.ERROR_SEARCH_FAILED)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Result(errorCode = ErrorCodes.ERROR_SEARCH_FAILED)
        }
    }

    override fun getMovie(movieId: Long): Result<Movie> {
        return try {
            val response = moviesApi.getMovieById(movieId, WebConstants.MOVIEDB_API_KEY).execute()
            if (response.isSuccessful) {
                val webMovie = response.body()
                Result(webMovie?.mapToBusiness())
            } else {
                Result(errorCode = ErrorCodes.ERROR_DETAILS_FETCHING_FAILED)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Result(errorCode = ErrorCodes.ERROR_DETAILS_FETCHING_FAILED)
        }
    }

    override fun getCredits(movieId: Long): Result<MovieCredits> {
        return try {
            val response = moviesApi.getCredits(movieId, WebConstants.MOVIEDB_API_KEY).execute()
            if (response.isSuccessful) {
                val webCredits = response.body()
                Result(webCredits?.mapToBusiness())
            } else {
                Result(errorCode = ErrorCodes.ERROR_DETAILS_FETCHING_FAILED)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Result(errorCode = ErrorCodes.ERROR_DETAILS_FETCHING_FAILED)
        }
    }
}