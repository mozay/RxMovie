package com.mozay.movie.presentation.module

import com.mozay.movie.di.scope.ActivityScope
import com.mozay.movie.presentation.MoviesActivity
import com.mozay.movie.presentation.detail.DetailFragmentModule
import com.mozay.movie.presentation.home.HomeFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MoviesActivityModule {
    @ActivityScope
    @ContributesAndroidInjector(
        modules = [HomeFragmentModule::class,
                DetailFragmentModule::class]
    )

    abstract fun provideMoviesActivity(): MoviesActivity
}