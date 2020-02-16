package com.mozay.movie.di.module

import com.mozay.movie.presentation.module.MoviesActivityModule
import dagger.Module

@Module(
    includes = [MoviesActivityModule::class]
)
abstract class ActivityModule