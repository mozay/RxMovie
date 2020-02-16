package com.mozay.movie.domain.module

import com.mozay.movie.domain.model.movie.response.MovieDetailResponse
import com.mozay.movie.domain.model.movie.response.MovieImagesResponse
import com.mozay.movie.domain.model.movie.response.MovieListResponse
import com.mozay.movie.domain.repository.MovieRepository
import com.mozay.movie.domain.usecase.*
import com.mozay.movie.domain.usecase.base.BaseUseCase
import dagger.Module
import dagger.Provides

@Module
class MoviesDomainModule {

    @Provides
    fun provideMoviePopularUseCase(movieRepository: MovieRepository):
            BaseUseCase.RequestInteractor<MoviePopularUseCase.MoviePopularUseCaseParams, MovieListResponse> =
        MoviePopularUseCase(movieRepository)

    @Provides
    fun provideMovieTopRatedUseCase(movieRepository: MovieRepository):
            BaseUseCase.RequestInteractor<MovieTopRatedUseCase.MovieTopRatedUseCaseParams, MovieListResponse> =
        MovieTopRatedUseCase(movieRepository)

    @Provides
    fun provideMovieNowPlayingUseCase(movieRepository: MovieRepository):
            BaseUseCase.RequestInteractor<MovieNowPlayingUseCase.MovieNowPlayingUseCaseParams, MovieListResponse> =
        MovieNowPlayingUseCase(movieRepository)

    @Provides
    fun provideMovieDetailUseCase(movieRepository: MovieRepository):
            BaseUseCase.RequestInteractor<MovieDetailUseCase.MovieDetailUseCaseParams, MovieDetailResponse> =
        MovieDetailUseCase(movieRepository)

    @Provides
    fun provideMovieImagesUseCase(movieRepository: MovieRepository):
            BaseUseCase.RequestInteractor<MovieImagesUseCase.MovieImagesUseCaseParams, MovieImagesResponse> =
        MovieImagesUseCase(movieRepository)

}