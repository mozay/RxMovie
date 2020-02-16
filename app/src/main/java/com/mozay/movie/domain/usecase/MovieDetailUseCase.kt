package com.mozay.movie.domain.usecase

import com.mozay.movie.domain.model.base.DataHolder
import com.mozay.movie.domain.model.movie.request.MovieDetailRequest
import com.mozay.movie.domain.model.movie.response.MovieDetailResponse
import com.mozay.movie.domain.repository.MovieRepository
import com.mozay.movie.domain.usecase.base.BaseUseCase
import com.mozay.movie.util.rx.AppSchedulerProvider
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.lang.Error
import javax.inject.Inject

class MovieDetailUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) : BaseUseCase.RequestInteractor<MovieDetailUseCase.MovieDetailUseCaseParams, MovieDetailResponse>{

    override fun fetch(compositeDisposable: CompositeDisposable,
                       postParams: MovieDetailUseCaseParams,
                       onResponse: (DataHolder<MovieDetailResponse>) -> Unit
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

    override fun executeAsync(postParams: MovieDetailUseCaseParams): Single<DataHolder<MovieDetailResponse>> {
        return movieRepository.fetchMovieDetail(
            MovieDetailRequest(
                api_key = postParams.api_key!!,
                language = postParams.language!!,
                movie_id = postParams.movie_id
            )
        )
    }

    class MovieDetailUseCaseParams(
        val api_key: String?,
        val language: String?,
        val movie_id: Int
    ) : BaseUseCase.Params()
}