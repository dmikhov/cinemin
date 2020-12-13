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
        val nullableBudget = if (budget == 0) {
            null
        } else {
            budget
        }
        val nullableRevenue = if (revenue == 0) {
            null
        } else {
            revenue
        }
        val nullableRelease = if (releaseDate.isNullOrEmpty()) {
            null
        } else {
            releaseDate
        }
        val nullableRating = if (rating == 0F) {
            null
        } else {
            rating
        }
        return Movie(
            id, title, posterUrl, nullableRelease, nullableBudget, nullableRevenue,
            runtime, nullableRating, adult, tagline, overview
        )
    }
}

fun List<WebMovie>.mapToBusiness(): List<Movie> = mapNotNull { it.mapToBusiness() }