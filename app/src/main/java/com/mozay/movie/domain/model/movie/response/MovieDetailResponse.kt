package com.mozay.movie.domain.model.movie.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class MovieDetailResponse (
    @SerializedName("vote_average")
    @Expose
    val voteAvarage: Double? = null,

    @SerializedName("overview")
    @Expose
    val overview: String? = null,

    @SerializedName("release_date")
    @Expose
    val releaseDate: String? = null,

    @SerializedName("poster_path")
    @Expose
    val moviePoster: String? = null,

    @SerializedName("backdrop_path")
    @Expose
    val movieDropPoster: String? = null,

    @SerializedName("title")
    @Expose
    val title: String? = null,

    @SerializedName("original_title")
    @Expose
    val originalTitle: String? = null,

    @SerializedName("runtime")
    @Expose
    val runtime: Int? = null,

    @SerializedName("tagline")
    @Expose
    val tagline: String? = null,

    @SerializedName("genres")
    @Expose
    val genres: List<MovieCategory>? = null
)