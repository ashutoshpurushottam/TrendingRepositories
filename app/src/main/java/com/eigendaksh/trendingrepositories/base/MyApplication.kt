package com.eigendaksh.trendingrepositories.base

import android.app.Application
import com.eigendaksh.trendingrepositories.BuildConfig
import com.eigendaksh.trendingrepositories.di.ActivityInjector
import timber.log.Timber
import javax.inject.Inject

class MyApplication: Application() {

    private lateinit var component: ApplicationComponent
    @Inject lateinit var activityInjector: ActivityInjector

    override fun onCreate() {
        super.onCreate()
        component = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()

        component.inject(this)

        if(BuildConfig.DEBUG) {
            Timber.plant(MyDebugTree())
        }
    }

    internal class MyDebugTree : Timber.DebugTree() {
        override fun createStackElementTag(element: StackTraceElement): String? {
            return super.createStackElementTag(element) + ":" + element.lineNumber
        }
    }


}