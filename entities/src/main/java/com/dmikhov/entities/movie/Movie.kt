package com.dmikhov.entities.movie

data class Movie (
    val id: Long,
    val title: String?,
    val posterUrl: String?,
    val releaseDate: String?,
    val budget: Long?,
    val revenue: Long?,
    val runtime: Long?,
    val rating: Float?,
    val adult: Boolean,
    val tagline: String?,
    val overview: String?
)