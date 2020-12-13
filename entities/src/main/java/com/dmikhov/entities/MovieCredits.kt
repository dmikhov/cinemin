package com.dmikhov.entities

class MovieCredits (
    val movieId: Int,
    val cast: List<Credit>,
    val crew: List<Credit>
)