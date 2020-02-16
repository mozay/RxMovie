package com.mozay.movie.data.source.remote

import com.mozay.movie.data.base.DataSource
import com.mozay.movie.domain.model.base.DataHolder
import com.mozay.movie.domain.model.movie.request.MovieDetailRequest
import com.mozay.movie.domain.model.movie.response.MovieDetailResponse
import io.reactivex.Single
import javax.inject.Inject

class MovieDetailRemoteDataSource @Inject constructor(
    private val apiServices: ApiService
) : DataSource.RequestRemoteDataSource<MovieDetailRequest, MovieDetailResponse> {

    override fun getResult(request: MovieDetailRequest): Single<DataHolder<MovieDetailResponse>> =
        apiServices.getMovieDetail(
            apiKey = request.api_key!!,
            language = request.language!!,
            movie_id = request.movie_id!!
        ).map {
            return@map DataHolder.Success(it)
        }
}