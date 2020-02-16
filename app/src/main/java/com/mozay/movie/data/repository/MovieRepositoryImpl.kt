package com.mozay.movie.data.repository

import com.mozay.movie.data.base.DataSource
import com.mozay.movie.domain.model.base.DataHolder
import com.mozay.movie.domain.model.movie.request.MovieDetailRequest
import com.mozay.movie.domain.model.movie.request.MovieImageRequest
import com.mozay.movie.domain.model.movie.response.MovieListResponse
import com.mozay.movie.domain.model.movie.request.MovieRequest
import com.mozay.movie.domain.model.movie.response.MovieDetailResponse
import com.mozay.movie.domain.model.movie.response.MovieImagesResponse
import com.mozay.movie.domain.repository.MovieRepository
import io.reactivex.Single
import java.lang.Error
import javax.inject.Inject

class MovieRepositoryImpl  @Inject constructor(
    private val moviesPopularRemoteDataSource: DataSource.RequestRemoteDataSource<MovieRequest, MovieListResponse>,
    private val moviesTopRatedRemoteDataSource: DataSource.RequestRemoteDataSource<MovieRequest, MovieListResponse>,
    private val moviesNowPlayingRemoteDataSource: DataSource.RequestRemoteDataSource<MovieRequest, MovieListResponse>,
    private val movieDetailRemoteDataSource : DataSource.RequestRemoteDataSource<MovieDetailRequest, MovieDetailResponse>,
    private val movieImagesRemoteDataSource : DataSource.RequestRemoteDataSource<MovieImageRequest, MovieImagesResponse>
) : MovieRepository{

    override fun fetchPopularMovies(request: MovieRequest): Single<DataHolder<MovieListResponse>> =
        moviesPopularRemoteDataSource.getResult(request)
                              .onErrorReturn { DataHolder.Fail(Error(it.message)) }

    override fun fetchTopRatedMovies(request: MovieRequest): Single<DataHolder<MovieListResponse>> =
        moviesTopRatedRemoteDataSource.getResult(request)
            .onErrorReturn { DataHolder.Fail(Error(it.message)) }

    override fun fetchNowPlayingMovies(request: MovieRequest): Single<DataHolder<MovieListResponse>> =
        moviesNowPlayingRemoteDataSource.getResult(request)
            .onErrorReturn { DataHolder.Fail(Error(it.message)) }

    override fun fetchMovieDetail(request: MovieDetailRequest): Single<DataHolder<MovieDetailResponse>> =
        movieDetailRemoteDataSource.getResult(request)
            .onErrorReturn { DataHolder.Fail(Error(it.message)) }

    override fun fetchMovieImages(request: MovieImageRequest): Single<DataHolder<MovieImagesResponse>> =
        movieImagesRemoteDataSource.getResult(request)
            .onErrorReturn { DataHolder.Fail(Error(it.message)) }

}