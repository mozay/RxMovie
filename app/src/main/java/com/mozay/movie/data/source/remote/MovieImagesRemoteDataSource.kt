package com.mozay.movie.data.source.remote

import com.mozay.movie.data.base.DataSource
import com.mozay.movie.domain.model.base.DataHolder
import com.mozay.movie.domain.model.movie.request.MovieDetailRequest
import com.mozay.movie.domain.model.movie.request.MovieImageRequest
import com.mozay.movie.domain.model.movie.response.MovieImagesResponse
import io.reactivex.Single
import javax.inject.Inject

class MovieImagesRemoteDataSource @Inject constructor(
    private val apiServices: ApiService
) : DataSource.RequestRemoteDataSource<MovieImageRequest, MovieImagesResponse> {

    override fun getResult(request: MovieImageRequest): Single<DataHolder<MovieImagesResponse>> =
        apiServices.getMovieImages(
            apiKey = request.api_key!!,
            movie_id = request.movie_id!!
        ).map {
            return@map DataHolder.Success(it)
        }
}