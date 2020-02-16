package com.mozay.movie.domain.usecase

import com.mozay.movie.domain.model.base.DataHolder
import com.mozay.movie.domain.model.movie.request.MovieImageRequest
import com.mozay.movie.domain.model.movie.response.MovieImagesResponse
import com.mozay.movie.domain.repository.MovieRepository
import com.mozay.movie.domain.usecase.base.BaseUseCase
import com.mozay.movie.util.rx.AppSchedulerProvider
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.lang.Error
import javax.inject.Inject

class MovieImagesUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) : BaseUseCase.RequestInteractor<MovieImagesUseCase.MovieImagesUseCaseParams, MovieImagesResponse>{

    override fun fetch(compositeDisposable: CompositeDisposable,
                       postParams: MovieImagesUseCaseParams,
                       onResponse: (DataHolder<MovieImagesResponse>) -> Unit
    ): Disposable {
        return this.executeAsync(postParams)
            .subscribeOn(AppSchedulerProvider.io())
            .observeOn(AppSchedulerProvider.ui())
            .subscribe(
                {
                    onResponse(it)
                },
                {
                    onResponse(DataHolder.Fail(Error(it.message)))
                }
            ).also { compositeDisposable.add(it) }
    }

    override fun executeAsync(postParams: MovieImagesUseCaseParams): Single<DataHolder<MovieImagesResponse>> {
        return movieRepository.fetchMovieImages(
            MovieImageRequest(
                api_key = postParams.api_key!!,
                movie_id = postParams.movie_id
            )
        )
    }

    class MovieImagesUseCaseParams(
        val api_key: String?,
        val movie_id: Int
    ) : BaseUseCase.Params()
}