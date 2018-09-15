package com.eigendaksh.trendingrepositories.details

import com.eigendaksh.trendingrepositories.di.ScreenScope
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
        abstract fun bindRepoOwner(@Named("repo_owner") repoOwner: String)

        @BindsInstance
        abstract fun bindRepoName(@Named("repo_name") repoName: String)

        override fun seedInstance(instance: DetailContainerController?) {
            bindRepoName(instance!!.args.getString(DetailContainerController.REPO_NAME_KEY))
            bindRepoOwner(instance.args.getString(DetailContainerController.REPO_OWNER_KEY))
        }
    }
}