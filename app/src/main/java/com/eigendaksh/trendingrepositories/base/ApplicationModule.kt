package com.eigendaksh.trendingrepositories.base

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule constructor(
        private val application: Application) {

    @Provides
    fun provideApplicationContext(): Context = application
}