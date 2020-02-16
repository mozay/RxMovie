package com.mozay.movie.domain.model.movie.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieImagesResponse (

    @SerializedName("backdrops")
    @Expose
    val backdrops: List<MovieImage>? = null,

    @SerializedName("posters")
    @Expose
    val posters: List<MovieImage>? = null

)
