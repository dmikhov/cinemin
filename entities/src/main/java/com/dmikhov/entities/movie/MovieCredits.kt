package com.dmikhov.entities.movie

class MovieCredits (
    val movieId: Int,
    val cast: List<Credit>,
    val crew: List<Credit>
)