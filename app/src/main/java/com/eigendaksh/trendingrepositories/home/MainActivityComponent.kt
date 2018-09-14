package com.eigendaksh.trendingrepositories.home

import com.eigendaksh.trendingrepositories.di.ActivityScope
import dagger.Subcomponent
import dagger.android.AndroidInjector

@ActivityScope
@Subcomponent(modules = [
    MainScreenBindingModule::class])

interface MainActivityComponent: AndroidInjector<MainActivity> {

    @Subcomponent.Builder
    abstract class Builder: AndroidInjector.Builder<MainActivity>() {
        override fun seedInstance(instance: MainActivity?) {
        }
    }
}