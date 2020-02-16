package com.mozay.movie.presentation.module

import androidx.lifecycle.ViewModel
import com.mozay.movie.presentation.base.viewmodel.ViewModelKey
import com.mozay.movie.presentation.detail.DetailViewModel
import com.mozay.movie.presentation.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MoviesViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    abstract fun bindDetailViewModel(detailViewModel: DetailViewModel): ViewModel
}