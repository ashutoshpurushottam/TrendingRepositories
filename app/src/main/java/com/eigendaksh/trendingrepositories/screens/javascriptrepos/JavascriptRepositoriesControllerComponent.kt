package com.eigendaksh.trendingrepositories.screens.javascriptrepos

import com.eigendaksh.trendingrepositories.di.ActivityScope
import com.eigendaksh.trendingrepositories.di.ScreenScope
import dagger.Subcomponent
import dagger.android.AndroidInjector

@ScreenScope
@Subcomponent
interface JavascriptRepositoriesControllerComponent : AndroidInjector<JavascriptRepositoriesController> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<JavascriptRepositoriesController>()

}