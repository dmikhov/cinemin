package com.dmikhov.repository.web.entities

import com.dmikhov.entities.movie.MovieCredits

data class WebCreditsResponse (
    val id: Int,
    val cast: List<WebCredit>,
    val crew: List<WebCredit>
) {
    fun mapToBusiness(): MovieCredits {
        return MovieCredits(id, cast.mapToBusiness(), crew.mapToBusiness())
    }
}