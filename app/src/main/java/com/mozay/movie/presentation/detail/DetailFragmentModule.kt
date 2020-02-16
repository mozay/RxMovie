package com.mozay.movie.presentation.detail

import com.mozay.movie.di.scope.FragmentScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class DetailFragmentModule {
    @FragmentScope
    @ContributesAndroidInjector
    abstract fun contributeDetailFragmentInjector(): DetailFragment
}