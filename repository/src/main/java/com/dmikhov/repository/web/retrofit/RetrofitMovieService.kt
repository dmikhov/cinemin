package com.dmikhov.repository.web.retrofit

import com.dmikhov.usecases.entities.ErrorCodes
import com.dmikhov.entities.Movie
import com.dmikhov.entities.MovieCredits
import com.dmikhov.usecases.entities.Result
import com.dmikhov.repository.web.IWebMovieService
import com.dmikhov.repository.web.WebConstants
import com.dmikhov.repository.web.entities.mapToMovies

class RetrofitMovieService (
    private val moviesApi: RetrofitMovieServiceApi,
): IWebMovieService {
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

    override fun getMovie(movieId: Int): Result<Movie> {
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

    override fun getCredits(movieId: Int): Result<MovieCredits> {
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