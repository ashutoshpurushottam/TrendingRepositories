package com.eigendaksh.trendingrepositories.details

import com.eigendaksh.trendingrepositories.di.ScreenScope
import com.eigendaksh.trendingrepositories.utils.REPO_NAME_KEY
import com.eigendaksh.trendingrepositories.utils.REPO_OWNER_KEY
import com.eigendaksh.trendingrepositories.utils.REPO_TYPE_KEY
import dagger.BindsInstance
import dagger.Subcomponent
import dagger.android.AndroidInjector
import javax.inject.Named

@ScreenScope
@Subcomponent
interface DetailContainerControllerComponent : AndroidInjector<DetailContainerController> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<DetailContainerController>() {

        @BindsInstance
        abstract fun bindRepoType(@Named(REPO_TYPE_KEY) repoType: Int)

        @BindsInstance
        abstract fun bindRepoOwner(@Named(REPO_OWNER_KEY) repoOwner: String)

        @BindsInstance
        abstract fun bindRepoName(@Named(REPO_NAME_KEY) repoName: String)


        override fun seedInstance(instance: DetailContainerController?) = if(instance != null) {
            bindRepoType(instance.args.getInt(REPO_TYPE_KEY))
            bindRepoOwner(instance.args.getString(REPO_OWNER_KEY))
            bindRepoName(instance.args.getString(REPO_NAME_KEY))
        } else {
            throw IllegalArgumentException("instance provided is null")
        }
    }
}