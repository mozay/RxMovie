package com.mozay.movie.domain.model.movie.response

import com.google.gson.annotations.SerializedName

data class MovieImage(
    @SerializedName("file_path")
    val moviePoster: String
)