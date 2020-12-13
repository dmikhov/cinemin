package com.dmikhov.repository.web.entities

import com.dmikhov.entities.movie.Movie
import com.dmikhov.repository.web.WebConstants
import com.google.gson.annotations.SerializedName

data class WebMovie(
    val id: Int,
    val title: String?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("release_date")
    val releaseDate: String?,
    val budget: Int?,
    val revenue: Int?,
    val runtime: Int?,
    @SerializedName("vote_average")
    val rating: Float?,
    val adult: Boolean,
    val tagline: String?,
    val overview: String?
) {
    fun mapToBusiness(): Movie? {
        val posterUrl = posterPath?.let {
            WebConstants.MOVIEDB_IMAGE_PATTERN.format(posterPath)
        }
        return Movie(
            id, title, posterUrl, releaseDate, budget,
            revenue, runtime, rating, adult, tagline, overview
        )
    }
}

fun List<WebMovie>.mapToBusiness(): List<Movie> = mapNotNull { it.mapToBusiness() }