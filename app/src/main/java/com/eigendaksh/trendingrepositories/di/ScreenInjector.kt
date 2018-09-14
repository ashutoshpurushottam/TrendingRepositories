package com.eigendaksh.trendingrepositories.di

import android.app.Activity
import com.bluelinelabs.conductor.Controller
import com.eigendaksh.trendingrepositories.base.BaseActivity
import com.eigendaksh.trendingrepositories.base.BaseController
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjector
import javax.inject.Inject
import javax.inject.Provider

@ActivityScope
class ScreenInjector @Inject constructor(
        private val screenInjectorMap:
        Map<Class<out Controller>, @JvmSuppressWildcards Provider<AndroidInjector.Factory<out Controller>>>) {

    private val cache: MutableMap<String?, AndroidInjector<out Controller>> = mutableMapOf()

    @Suppress("UNCHECKED_CAST")
    fun inject(controller: Controller) {

        if (controller !is BaseController)
            throw IllegalArgumentException("Controller must extend BaseController")

        val instanceId = controller.instanceId
        if (cache.containsKey(instanceId)) {
            (cache[instanceId] as AndroidInjector<Controller>).inject(controller)
        } else {
            val androidInjectorFactory: AndroidInjector.Factory<Controller> =
                    screenInjectorMap[controller.javaClass]?.get() as AndroidInjector.Factory<Controller>
            val androidInjector: AndroidInjector<Controller> =
                    androidInjectorFactory.create(controller)
            cache[instanceId] = androidInjector
            androidInjector.inject(controller)
        }
    }

    fun clearController(controller: Controller) {
        if (controller !is BaseController)
            throw IllegalArgumentException("Activity must extend BaseActivity")

        val instanceId = controller.instanceId
        if (cache.containsKey(instanceId))
            cache.remove(instanceId)
    }

    @Module
    companion object Companion {
        @Provides
        fun get(activity: Activity): ScreenInjector {
            if (activity !is BaseActivity)
                throw IllegalArgumentException("Controller must be hosted by BaseActivity")
            return activity.screenInjector
        }
    }
}