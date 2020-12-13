package com.dmikhov.repository.web

object WebConstants {
    const val MOVIEDB_BASE_URL = "https://api.themoviedb.org/"
    const val MOVIEDB_API_KEY = "6221fd2e6220aced25ed017367333222"
    const val MOVIEDB_ACCESS_TOKEN_V4 = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI2MjIxZmQyZTYyMjBhY2VkMjVlZDAxNzM2NzMzMzIyMiIsInN1YiI6IjU5Y2U2MWQyOTI1MTQxNmMyODAxOTVmMiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.gToydbSghDDKSZ-kqOr5d5Vi8eV-D8APANb95YdUjoM"
    const val MOVIEDB_IMAGE_PATTERN = "https://image.tmdb.org/t/p/w500%s"
    const val DATE_FORMAT = "yyyy-MM-dd"

    const val READ_TIMEOUT = 20L
    const val CONNECT_TIMEOUT = 20L
}