package com.dmikhov.entities

import java.io.Serializable

data class Credit(
    val id: Int,
    val creditId: String,
    val name: String,
    val job: String?,
    val imageUrl: String?,
    val gender: Int,
    val type: String?
) : Serializable {
    companion object {
        const val TYPE_CREW = "crew"
        const val TYPE_CAST = "cast"
        const val JOB_DIRECTOR = "Director"
        const val JOB_ACTOR = "Actor"
    }
}