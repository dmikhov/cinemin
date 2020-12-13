package com.dmikhov.repository.web.retrofit

import com.dmikhov.repository.web.entities.SearchWebResponse
import com.dmikhov.repository.web.entities.WebCreditsResponse
import com.dmikhov.repository.web.entities.WebMovie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitMovieServiceApi {

    @GET(SEARCH)
    fun searchForMoviesAndPeople(
        @Query(PARAM_API_KEY) apiKey: String,
        @Query(PARAM_QUERY) query: String,
    ): Call<SearchWebResponse>

    @GET(MOVIE)
    fun getMovieById(
        @Path(MOVIE_ID_PATH) movieId: Int,
        @Query(PARAM_API_KEY) apiKey: String,
    ): Call<WebMovie>

    @GET(CREDITS)
    fun getCredits(
        @Path(MOVIE_ID_PATH) movieId: Int,
        @Query(PARAM_API_KEY) apiKey: String
    ): Call<WebCreditsResponse>

    companion object {
        const val MOVIE_ID_PATH = "movie_id"

        const val MOVIE = "3/movie/{$MOVIE_ID_PATH}"
        const val SEARCH = "3/search/multi"
        const val CREDITS = "3/movie/{$MOVIE_ID_PATH}/credits"

        const val PARAM_API_KEY = "api_key"
        const val PARAM_QUERY = "query"
    }
}