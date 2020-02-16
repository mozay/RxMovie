package com.mozay.movie.presentation.home

import com.mozay.movie.di.scope.FragmentScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HomeFragmentModule {
    @FragmentScope
    @ContributesAndroidInjector
    abstract fun contributeHomeFragmentInjector(): HomeFragment
}