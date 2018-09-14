package com.eigendaksh.trendingrepositories.di

import android.app.Activity
import android.content.Context
import com.eigendaksh.trendingrepositories.base.BaseActivity
import com.eigendaksh.trendingrepositories.base.MyApplication
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjector
import javax.inject.Inject
import javax.inject.Provider


class ActivityInjector @Inject constructor(
        private val activityInjectorMap:
        Map<Class<out Activity>,
                @JvmSuppressWildcards Provider<AndroidInjector.Factory<out Activity>>>) {

    // out T is like ? extends T
    private val cache: MutableMap<String?, AndroidInjector<out Activity>> = mutableMapOf()

    @Suppress("UNCHECKED_CAST")
    fun inject(activity: Activity) {

        if (activity !is BaseActivity)
            throw IllegalArgumentException("Activity must extend BaseActivity")

        val instanceId = activity.instanceId
        if (cache.containsKey(instanceId)) {
            (cache[instanceId] as AndroidInjector<Activity>).inject(activity)
        } else {
            val androidInjectorFactory: AndroidInjector.Factory<Activity> =
                    activityInjectorMap[activity.javaClass]?.get() as AndroidInjector.Factory<Activity>
            val androidInjector: AndroidInjector<Activity> =
                    androidInjectorFactory.create(activity)
            cache[instanceId] = androidInjector
            androidInjector.inject(activity)
        }
    }

    fun clear(activity: Activity) {
        if (activity !is BaseActivity)
            throw IllegalArgumentException("Activity must extend BaseActivity")
        val instanceId = activity.instanceId
        if (cache.containsKey(instanceId))
            cache.remove(instanceId)
    }

    @Module
    companion object Companion {
        @Provides
        fun get(context: Context): ActivityInjector =
                (context.applicationContext as MyApplication).activityInjector
    }
}
