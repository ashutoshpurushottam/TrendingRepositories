package com.eigendaksh.trendingrepositories.home

import com.bluelinelabs.conductor.Controller
import com.eigendaksh.trendingrepositories.di.ControllerKey
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

@Module(subcomponents = [
    ContainerControllerComponent::class
])
abstract class MainScreenBindingModule {

    @Binds
    @IntoMap
    @ControllerKey(ContainerController::class)
    abstract fun bindContainerInjector(builder: ContainerControllerComponent.Builder)
            : AndroidInjector.Factory<out Controller>

}