package com.dmikhov.entities.movie

data class Movie (
    val id: Int,
    val title: String?,
    val posterUrl: String?,
    val releaseDate: String?,
    val budget: Int?,
    val revenue: Int?,
    val runtime: Int?,
    val rating: Float?,
    val adult: Boolean,
    val tagline: String?,
    val overview: String?
)