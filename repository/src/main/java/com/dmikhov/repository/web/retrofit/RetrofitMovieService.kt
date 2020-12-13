package com.dmikhov.repository.web.retrofit

import android.util.Log
import com.dmikhov.entities.ErrorCodes
import com.dmikhov.entities.Movie
import com.dmikhov.entities.Result
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
            Log.d("madtag", "RetrofitMovieService getMovie movieId $movieId, response ${response.body()}")
            if (response.isSuccessful) {
                val webMovie = response.body()
                Result(webMovie?.mapToMovie())
            } else {
                Result(errorCode = ErrorCodes.ERROR_DETAILS_FETCHING_FAILED)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Result(errorCode = ErrorCodes.ERROR_DETAILS_FETCHING_FAILED)
        }
    }
}