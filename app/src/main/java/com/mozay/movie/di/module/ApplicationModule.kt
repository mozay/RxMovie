package com.mozay.movie.di.module

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.mozay.movie.MovieApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule {

    @Provides
    fun provideApplicationContext(app: MovieApp): Context {
        return app.applicationContext
    }

    @Singleton
    @Provides
    fun provideSharedPreferences(context: Context): SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context)
}