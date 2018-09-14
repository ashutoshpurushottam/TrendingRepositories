package com.eigendaksh.trendingrepositories.base

import com.eigendaksh.trendingrepositories.data.RepoServiceModule
import com.eigendaksh.trendingrepositories.networking.ServiceModule
import com.eigendaksh.trendingrepositories.ui.NavigationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ApplicationModule::class,
    ActivityBindingModule::class,
    NavigationModule::class,
    ServiceModule::class,
    RepoServiceModule::class
])
interface ApplicationComponent {
    fun inject(myApplication: MyApplication)
}