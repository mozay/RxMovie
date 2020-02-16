package com.mozay.movie.data.source.remote

import com.mozay.movie.data.base.DataSource
import com.mozay.movie.domain.model.base.DataHolder
import com.mozay.movie.domain.model.movie.response.MovieListResponse
import com.mozay.movie.domain.model.movie.request.MovieRequest
import io.reactivex.Single
import javax.inject.Inject


class MoviesTopRatedRemoteDataSource @Inject constructor(
    private val apiServices: ApiService
) : DataSource.RequestRemoteDataSource<MovieRequest, MovieListResponse> {

    override fun getResult(request: MovieRequest): Single<DataHolder<MovieListResponse>> =
        apiServices.getTopRatedMovies(
            apiKey = request.api_key!!,
            language = request.language!!,
            page = request.page!!
        ).map {
                return@map DataHolder.Success(it)
        }
}