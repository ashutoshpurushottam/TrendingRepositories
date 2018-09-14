package com.eigendaksh.trendingrepositories.home

import com.eigendaksh.trendingrepositories.di.ScreenScope
import dagger.Subcomponent
import dagger.android.AndroidInjector

@ScreenScope
@Subcomponent
interface ContainerControllerComponent : AndroidInjector<ContainerController> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<ContainerController>()
}