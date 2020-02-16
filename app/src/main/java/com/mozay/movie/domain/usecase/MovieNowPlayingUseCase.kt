package com.mozay.movie.domain.usecase

import com.mozay.movie.domain.model.base.DataHolder
import com.mozay.movie.domain.model.movie.response.MovieListResponse
import com.mozay.movie.domain.model.movie.request.MovieRequest
import com.mozay.movie.domain.repository.MovieRepository
import com.mozay.movie.domain.usecase.base.BaseUseCase
import com.mozay.movie.util.rx.AppSchedulerProvider
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.lang.Error
import javax.inject.Inject

class MovieNowPlayingUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) : BaseUseCase.RequestInteractor<MovieNowPlayingUseCase.MovieNowPlayingUseCaseParams, MovieListResponse>{

    override fun fetch(compositeDisposable: CompositeDisposable,
                       postParams: MovieNowPlayingUseCaseParams,
                       onResponse: (DataHolder<MovieListResponse>) -> Unit
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

    override fun executeAsync(postParams: MovieNowPlayingUseCaseParams): Single<DataHolder<MovieListResponse>> {
        return movieRepository.fetchNowPlayingMovies(
            MovieRequest(
                api_key = postParams.api_key!!,
                language = postParams.language!!,
                page = postParams.page
            )
        )
    }

    class MovieNowPlayingUseCaseParams(
        val api_key: String?,
        val language: String?,
        val page: Int
    ) : BaseUseCase.Params()
}