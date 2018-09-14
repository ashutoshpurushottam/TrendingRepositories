package com.eigendaksh.trendingrepositories.screens.javarepos

import com.eigendaksh.trendingrepositories.di.ActivityScope
import com.eigendaksh.trendingrepositories.di.ScreenScope
import dagger.Subcomponent
import dagger.android.AndroidInjector

@ScreenScope
@Subcomponent
interface JavaRepositoriesControllerComponent : AndroidInjector<JavaRepositoriesController> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<JavaRepositoriesController>()

}