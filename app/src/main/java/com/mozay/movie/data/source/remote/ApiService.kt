package com.mozay.movie.data.source.remote

import com.mozay.movie.domain.model.movie.response.MovieDetailResponse
import com.mozay.movie.domain.model.movie.response.MovieImagesResponse
import com.mozay.movie.domain.model.movie.response.MovieListResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService{

    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Single<MovieListResponse>

    @GET("movie/top_rated")
    fun getTopRatedMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Single<MovieListResponse>

    @GET("movie/now_playing")
    fun getNowPlayingMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Single<MovieListResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetail(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Single<MovieDetailResponse>

    @GET("movie/{movie_id}/images")
    fun getMovieImages(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") apiKey: String
    ): Single<MovieImagesResponse>
}