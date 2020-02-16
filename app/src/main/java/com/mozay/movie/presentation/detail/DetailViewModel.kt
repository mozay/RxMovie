package com.mozay.movie.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.mozay.movie.domain.model.base.DataHolder
import com.mozay.movie.domain.model.movie.response.MovieDetailResponse
import com.mozay.movie.domain.model.movie.response.MovieImagesResponse
import com.mozay.movie.domain.usecase.MovieDetailUseCase
import com.mozay.movie.domain.usecase.MovieImagesUseCase
import com.mozay.movie.domain.usecase.base.BaseUseCase
import com.mozay.movie.presentation.base.viewmodel.BaseViewModel
import com.mozay.movie.util.Constants
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val movieDetailUseCase: BaseUseCase.RequestInteractor<MovieDetailUseCase.MovieDetailUseCaseParams, MovieDetailResponse>,
    private val movieImagesUseCase : BaseUseCase.RequestInteractor<MovieImagesUseCase.MovieImagesUseCaseParams, MovieImagesResponse>
    ) : BaseViewModel() {

    //region images of movie
    private val _movieImagesLiveData = MediatorLiveData<DataHolder<MovieImagesResponse>>()
    val movieImagesLiveData: LiveData<DataHolder<MovieImagesResponse>>
        get() = _movieImagesLiveData

    fun getMovieImages(movieId : Int) {
        _movieImagesLiveData.value = DataHolder.Loading

        val params = MovieImagesUseCase.MovieImagesUseCaseParams(
            api_key = Constants.API_KEY,
            movie_id = movieId
        )

        movieImagesUseCase.fetch(compositeDisposable, params, this::getMovieImagesResponse)
    }

    private fun getMovieImagesResponse(response: DataHolder<MovieImagesResponse>){
        when(response){
            is DataHolder.Success -> {
                _movieImagesLiveData.value = DataHolder.Success(response.data)
            }
            is DataHolder.Fail -> {
                _movieImagesLiveData.value = DataHolder.Fail(response.e)
            }
        }
    }

    //endregion

    //region details of movie
    private val _movieDetailLiveData = MediatorLiveData<DataHolder<MovieDetailResponse>>()
    val movieDetailLiveData: LiveData<DataHolder<MovieDetailResponse>>
        get() = _movieDetailLiveData

    fun getMovieDetail(movieId : Int) {
        _movieImagesLiveData.value = DataHolder.Loading

        val params = MovieDetailUseCase.MovieDetailUseCaseParams(
            api_key = Constants.API_KEY,
            language = Constants.LANGUAGE,
            movie_id = movieId
        )

        movieDetailUseCase.fetch(compositeDisposable, params, this::getMovieDetailResponse)
    }

    private fun getMovieDetailResponse(response: DataHolder<MovieDetailResponse>){
        when(response){
            is DataHolder.Success -> {
                _movieDetailLiveData.value = DataHolder.Success(response.data)
            }
            is DataHolder.Fail -> {
                _movieDetailLiveData.value = DataHolder.Fail(response.e)
            }
        }
    }

    //endregion
}