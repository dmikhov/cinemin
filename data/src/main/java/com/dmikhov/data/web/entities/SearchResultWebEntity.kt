package com.dmikhov.data.web.entities

import com.dmikhov.entities.movie.Movie
import com.dmikhov.data.web.WebConstants
import com.google.gson.annotations.SerializedName

data class SearchResultWebEntity(
    val id: Long,
    @SerializedName("media_type")
    val mediaType: String,
    val title: String?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("release_date")
    val releaseDate: String?
) {
    fun mapToMovie(): Movie? {
        return if (mediaType == TYPE_MOVIE) {
            val posterUrl = posterPath?.let {
                WebConstants.MOVIEDB_IMAGE_PATTERN.format(posterPath)
            }
            Movie(id, title, posterUrl, releaseDate, null,
                null, null, null, false, null, null)
        } else {
            null
        }
    }

    companion object {
        const val TYPE_MOVIE = "movie"
    }
}

fun List<SearchResultWebEntity>.mapToMovies(): List<Movie> = mapNotNull { it.mapToMovie() }