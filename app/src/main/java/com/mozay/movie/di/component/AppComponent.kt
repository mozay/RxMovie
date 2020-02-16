package com.mozay.movie.di.component

import com.mozay.movie.MovieApp
import com.mozay.movie.di.module.*
import com.mozay.movie.di.module.DataModule
import com.mozay.movie.di.module.DomainModule
import com.mozay.movie.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AndroidInjectionModule::class,
        ApplicationModule::class,
        ActivityModule::class,
        DomainModule::class,
        DataModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: MovieApp): Builder

        fun build(): AppComponent
    }

    fun inject(application: MovieApp)
}