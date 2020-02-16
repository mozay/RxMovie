package com.mozay.movie.data.module

import com.mozay.movie.data.base.DataSource
import com.mozay.movie.data.repository.MovieRepositoryImpl
import com.mozay.movie.data.source.remote.*
import com.mozay.movie.domain.model.movie.request.MovieDetailRequest
import com.mozay.movie.domain.model.movie.request.MovieImageRequest
import com.mozay.movie.domain.model.movie.response.MovieListResponse
import com.mozay.movie.domain.model.movie.request.MovieRequest
import com.mozay.movie.domain.model.movie.response.MovieDetailResponse
import com.mozay.movie.domain.model.movie.response.MovieImagesResponse
import com.mozay.movie.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton


@Module
class MoviesDataModule {

    @Provides
    @Singleton
    fun provideApiServices(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideMoviesPopularRemoteDataSource(apiService: ApiService): DataSource.RequestRemoteDataSource<MovieRequest, MovieListResponse> =
        MoviesPopularRemoteDataSource(apiService)

    @Provides
    @Singleton
    fun provideMoviesTopRatedRemoteDataSource(apiService: ApiService): DataSource.RequestRemoteDataSource<MovieRequest, MovieListResponse> =
        MoviesTopRatedRemoteDataSource(apiService)

    @Provides
    @Singleton
    fun provideMoviesNowPlayingRemoteDataSource(apiService: ApiService): DataSource.RequestRemoteDataSource<MovieRequest, MovieListResponse> =
        MoviesNowPlayingRemoteDataSource(apiService)

    @Provides
    @Singleton
    fun provideMovieDetailRemoteDataSource(apiService: ApiService): DataSource.RequestRemoteDataSource<MovieDetailRequest, MovieDetailResponse> =
        MovieDetailRemoteDataSource(apiService)

    @Provides
    @Singleton
    fun provideMovieImagesRemoteDataSource(apiService: ApiService): DataSource.RequestRemoteDataSource<MovieImageRequest, MovieImagesResponse> =
        MovieImagesRemoteDataSource(apiService)

    @Provides
    @Singleton
    fun provideMoviesRepository(
        moviesPopularRemoteDataSource: MoviesPopularRemoteDataSource,
        moviesTopRatedRemoteDataSource: MoviesTopRatedRemoteDataSource,
        moviesNowPlayingRemoteDataSource: MoviesNowPlayingRemoteDataSource,
        movieDetailRemoteDataSource: MovieDetailRemoteDataSource,
        movieImagesRemoteDataSource: MovieImagesRemoteDataSource
    ): MovieRepository = MovieRepositoryImpl(
        moviesPopularRemoteDataSource,
        moviesTopRatedRemoteDataSource,
        moviesNowPlayingRemoteDataSource,
        movieDetailRemoteDataSource,
        movieImagesRemoteDataSource
    )
}