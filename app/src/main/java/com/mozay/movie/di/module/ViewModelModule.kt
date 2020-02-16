package com.mozay.movie.di.module

import androidx.lifecycle.ViewModelProvider
import com.mozay.movie.presentation.module.MoviesViewModelModule
import com.mozay.movie.presentation.base.viewmodel.VmFactory
import dagger.Binds
import dagger.Module

@Module(
    includes = [MoviesViewModelModule::class]
)

internal abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(vmFactory: VmFactory): ViewModelProvider.Factory
}