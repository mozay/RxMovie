package com.mozay.movie.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.mozay.movie.domain.model.base.DataHolder
import com.mozay.movie.domain.model.movie.response.MovieListResponse
import com.mozay.movie.domain.model.movie.response.MovieLite
import com.mozay.movie.domain.usecase.MovieNowPlayingUseCase
import com.mozay.movie.domain.usecase.MoviePopularUseCase
import com.mozay.movie.domain.usecase.MovieTopRatedUseCase
import com.mozay.movie.domain.usecase.base.BaseUseCase
import com.mozay.movie.presentation.base.viewmodel.BaseViewModel
import com.mozay.movie.util.Constants
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val moviePopularUseCase: BaseUseCase.RequestInteractor<MoviePopularUseCase.MoviePopularUseCaseParams, MovieListResponse>,
    private val movieTopRatedUseCase : BaseUseCase.RequestInteractor<MovieTopRatedUseCase.MovieTopRatedUseCaseParams, MovieListResponse>,
    private val movieNowPlayingUseCase: BaseUseCase.RequestInteractor<MovieNowPlayingUseCase.MovieNowPlayingUseCaseParams, MovieListResponse>
    ) : BaseViewModel() {

    //region popular movies
    private val _popularMoviesLiveData = MediatorLiveData<DataHolder<MovieListResponse>>()
    val popularMoviesLiveData: LiveData<DataHolder<MovieListResponse>>
        get() = _popularMoviesLiveData

    fun getPopularMovies() {
        _popularMoviesLiveData.value = DataHolder.Loading

        val params = MoviePopularUseCase.MoviePopularUseCaseParams(
            api_key = Constants.API_KEY,
            language = Constants.LANGUAGE,
            page = Constants.DEFAULT_PAGE_SIZE
        )

        moviePopularUseCase.fetch(compositeDisposable, params, this::getPopularMoviesResponse)
    }

    private fun getPopularMoviesResponse(response: DataHolder<MovieListResponse>){
        when(response){
            is DataHolder.Success -> {
                _popularMoviesLiveData.value = DataHolder.Success(response.data)
            }
            is DataHolder.Fail -> {
                _popularMoviesLiveData.value = DataHolder.Fail(response.e)
            }
        }
    }

    //endregion

    //region toprated movies
    private val _topRatedMoviesLiveData = MediatorLiveData<DataHolder<MovieListResponse>>()
    val topRatedMoviesLiveData: LiveData<DataHolder<MovieListResponse>>
        get() = _topRatedMoviesLiveData

    fun getTopRatedMovies() {
        _topRatedMoviesLiveData.value = DataHolder.Loading

        val params = MovieTopRatedUseCase.MovieTopRatedUseCaseParams(
            api_key = Constants.API_KEY,
            language = Constants.LANGUAGE,
            page = Constants.DEFAULT_PAGE_SIZE
        )

        movieTopRatedUseCase.fetch(compositeDisposable, params, this::getTopRatedMoviesResponse)
    }

    private fun getTopRatedMoviesResponse(response: DataHolder<MovieListResponse>){
        when(response){
            is DataHolder.Success -> {
                _topRatedMoviesLiveData.value = DataHolder.Success(response.data)
            }
            is DataHolder.Fail -> {
                _topRatedMoviesLiveData.value = DataHolder.Fail(response.e)
            }
        }
    }

    //endregion

    //region nowplaying movies
    private val _nowPlayingrMoviesLiveData = MediatorLiveData<DataHolder<MovieListResponse>>()
    val nowPlayingMoviesLiveData: LiveData<DataHolder<MovieListResponse>>
        get() = _nowPlayingrMoviesLiveData

    fun getNowPlayingMovies() {
        _nowPlayingrMoviesLiveData.value = DataHolder.Loading

        val params = MovieNowPlayingUseCase.MovieNowPlayingUseCaseParams(
            api_key = Constants.API_KEY,
            language = Constants.LANGUAGE,
            page = Constants.DEFAULT_PAGE_SIZE
        )

        movieNowPlayingUseCase.fetch(compositeDisposable, params, this::getNowPlayingMoviesResponse)
    }

    private fun getNowPlayingMoviesResponse(response: DataHolder<MovieListResponse>){
        when(response){
            is DataHolder.Success -> {
                _nowPlayingrMoviesLiveData.value = DataHolder.Success(response.data)
            }
            is DataHolder.Fail -> {
                _nowPlayingrMoviesLiveData.value = DataHolder.Fail(response.e)
            }
        }
    }

    //endregion

}