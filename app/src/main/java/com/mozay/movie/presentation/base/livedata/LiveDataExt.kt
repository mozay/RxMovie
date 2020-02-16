package com.mozay.movie.presentation.base.livedata

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.mozay.movie.domain.model.base.DataHolder

inline fun <T : Any> LiveData<DataHolder<T>>.observeApi(
    lifecycleOwner: LifecycleOwner,
    crossinline body: (DataHolder<T>?) -> Unit
) {
    observe(lifecycleOwner, Observer { bean: DataHolder<T>? ->
        body(bean)
    })
}
