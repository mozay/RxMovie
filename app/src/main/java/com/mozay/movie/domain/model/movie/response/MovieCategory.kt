package com.mozay.movie.domain.model.movie.response

import com.google.gson.annotations.SerializedName

data class MovieCategory (

    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("name")
    val name: String? = null
)