package com.eigendaksh.trendingrepositories.details

import com.bluelinelabs.conductor.Controller
import com.eigendaksh.trendingrepositories.di.ControllerKey
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

@Module(subcomponents = [
    DetailContainerControllerComponent::class
])
abstract class RepoDetailsScreenBindingModule {

    @Binds
    @IntoMap
    @ControllerKey(DetailContainerController::class)
    abstract fun bindDetailContainerInjector(builder: DetailContainerControllerComponent.Builder)
        : AndroidInjector.Factory<out Controller>
}