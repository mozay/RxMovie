package com.mozay.movie.domain.model.movie.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieListResponse (
    @SerializedName("page")
    @Expose
    val page: Int = 0,

    @SerializedName("results")
    @Expose
    val moviesResult: List<MovieLite>? = null,

    @SerializedName("total_pages")
    @Expose
    val totalPages: Int = 0
)