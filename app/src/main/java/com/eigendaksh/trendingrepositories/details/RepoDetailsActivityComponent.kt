package com.eigendaksh.trendingrepositories.details

import com.eigendaksh.trendingrepositories.di.ActivityScope
import dagger.Subcomponent
import dagger.android.AndroidInjector

@ActivityScope
@Subcomponent(modules = [
    RepoDetailsScreenBindingModule::class
])
interface RepoDetailsActivityComponent : AndroidInjector<RepoDetailsActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<RepoDetailsActivity>() {
        override fun seedInstance(instance: RepoDetailsActivity?) {

        }
    }
}