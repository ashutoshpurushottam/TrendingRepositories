package com.eigendaksh.trendingrepositories.di

import android.app.Activity
import com.bluelinelabs.conductor.Controller

object Injector {

    fun inject(activity: Activity) {
        ActivityInjector.get(activity).inject(activity)
    }

    fun clearComponent(activity: Activity) {
        ActivityInjector.get(activity).clear(activity)
    }

    fun inject(controller: Controller) {
        ScreenInjector.get(controller.activity!!).inject(controller)
    }

    fun clearComponent(controller: Controller) {
        ScreenInjector.get(controller.activity!!).clearController(controller)
    }

}