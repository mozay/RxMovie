package com.mozay.movie.domain.model.movie.response
import com.google.gson.annotations.SerializedName

data class MovieLite (

    @SerializedName("vote_average")
    val voterAverage: Float,

    @SerializedName("poster_path")
    val moviePoster: String,

    @SerializedName("backdrop_path")
    val movieDropPoster: String,

    @SerializedName("id")
    val movieId: Int,

    @SerializedName("original_title")
    val movieOriginalTitle: String?,

    @SerializedName("title")
    val movieTitle: String?

)