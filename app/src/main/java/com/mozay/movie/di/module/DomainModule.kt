package com.mozay.movie.di.module

import com.mozay.movie.domain.module.MoviesDomainModule
import dagger.Module

@Module(
    includes = [MoviesDomainModule::class]
)
internal abstract class DomainModule