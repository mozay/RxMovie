package com.mozay.movie.domain.repository

import com.mozay.movie.domain.model.base.DataHolder
import com.mozay.movie.domain.model.movie.request.MovieDetailRequest
import com.mozay.movie.domain.model.movie.request.MovieImageRequest
import com.mozay.movie.domain.model.movie.response.MovieListResponse
import com.mozay.movie.domain.model.movie.request.MovieRequest
import com.mozay.movie.domain.model.movie.response.MovieDetailResponse
import com.mozay.movie.domain.model.movie.response.MovieImagesResponse
import io.reactivex.Single

interface MovieRepository {

    fun fetchPopularMovies(request: MovieRequest): Single<DataHolder<MovieListResponse>>

    fun fetchTopRatedMovies(request: MovieRequest): Single<DataHolder<MovieListResponse>>

    fun fetchNowPlayingMovies(request: MovieRequest): Single<DataHolder<MovieListResponse>>

    fun fetchMovieImages(request: MovieImageRequest) : Single<DataHolder<MovieImagesResponse>>

    fun fetchMovieDetail(request: MovieDetailRequest) : Single<DataHolder<MovieDetailResponse>>

}