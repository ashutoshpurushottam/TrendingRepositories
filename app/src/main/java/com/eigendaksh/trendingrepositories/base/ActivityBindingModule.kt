package com.eigendaksh.trendingrepositories.base

import android.app.Activity
import com.eigendaksh.trendingrepositories.details.RepoDetailsActivityComponent
import com.eigendaksh.trendingrepositories.details.RepoDetailsActivity
import com.eigendaksh.trendingrepositories.home.MainActivity
import com.eigendaksh.trendingrepositories.home.MainActivityComponent
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

@Module(subcomponents = [
    MainActivityComponent::class,
    RepoDetailsActivityComponent::class
])

abstract class ActivityBindingModule {

    @Binds
    @IntoMap
    @ActivityKey(MainActivity::class)
    abstract fun provideMainActivityInjector(
            builder: MainActivityComponent.Builder): AndroidInjector.Factory<out Activity>

    @Binds
    @IntoMap
    @ActivityKey(RepoDetailsActivity::class)
    abstract fun provideRepoDetailActivityInjector(
            builder: RepoDetailsActivityComponent.Builder) : AndroidInjector.Factory<out Activity>

}