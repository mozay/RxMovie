package com.mozay.movie.domain.model.movie.request

import com.google.gson.annotations.SerializedName

data class MovieImageRequest(
    @SerializedName("api_key") val api_key: String?,
    @SerializedName("movie_id") val movie_id: Int?
)