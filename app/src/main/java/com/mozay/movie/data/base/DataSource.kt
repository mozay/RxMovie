package com.mozay.movie.data.base

import com.mozay.movie.domain.model.base.DataHolder
import io.reactivex.Single

interface DataSource {

    interface RequestRemoteDataSource<Req, Res : Any> : DataSource {
        fun getResult(request: Req): Single<DataHolder<Res>>
    }

    interface FetchRemoteDataSource<Res : Any> : DataSource {
        fun getResult(): Single<DataHolder<Res>>
    }

}