package com.dmikhov.data.web.entities

import com.dmikhov.entities.movie.Credit
import com.dmikhov.data.web.WebConstants
import com.google.gson.annotations.SerializedName

data class WebCredit (
    val id: Int,
    @SerializedName("credit_id") val creditId: String,
    val name: String,
    val job: String?,
    @SerializedName("profile_path") val profilePath: String?,
    val gender: Int,
    @SerializedName("credit_type") val type: String?
) {
    fun mapToBusiness(): Credit {
        val imageUrl = if (profilePath != null) {
            WebConstants.MOVIEDB_IMAGE_PATTERN.format(profilePath)
        } else {
            null
        }
        return Credit(id, creditId, name, job, imageUrl, gender, type)
    }
}

fun List<WebCredit>.mapToBusiness(): List<Credit> {
    return map { it.mapToBusiness() }
}