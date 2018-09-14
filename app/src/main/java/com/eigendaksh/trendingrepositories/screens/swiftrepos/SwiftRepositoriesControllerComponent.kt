package com.eigendaksh.trendingrepositories.screens.swiftrepos

import com.eigendaksh.trendingrepositories.di.ActivityScope
import com.eigendaksh.trendingrepositories.di.ScreenScope
import dagger.Subcomponent
import dagger.android.AndroidInjector

@ScreenScope
@Subcomponent
interface SwiftRepositoriesControllerComponent : AndroidInjector<SwiftRepositoriesController> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<SwiftRepositoriesController>()

}