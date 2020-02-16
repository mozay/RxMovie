package com.mozay.movie.di.module

import com.mozay.movie.data.module.MoviesDataModule
import dagger.Module

@Module(
    includes = [NetworkModule::class,
        MoviesDataModule::class]
)
internal abstract class DataModule