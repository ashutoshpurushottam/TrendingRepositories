package com.eigendaksh.trendingrepositories.home

import com.bluelinelabs.conductor.Controller
import com.eigendaksh.trendingrepositories.di.ActivityScope
import com.eigendaksh.trendingrepositories.di.ControllerKey
import com.eigendaksh.trendingrepositories.screens.javarepos.JavaRepositoriesController
import com.eigendaksh.trendingrepositories.screens.javarepos.JavaRepositoriesControllerComponent
import com.eigendaksh.trendingrepositories.screens.javascriptrepos.JavascriptRepositoriesController
import com.eigendaksh.trendingrepositories.screens.javascriptrepos.JavascriptRepositoriesControllerComponent
import com.eigendaksh.trendingrepositories.screens.swiftrepos.SwiftRepositoriesController
import com.eigendaksh.trendingrepositories.screens.swiftrepos.SwiftRepositoriesControllerComponent
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

@Module(subcomponents = [
    ContainerControllerComponent::class,
    JavaRepositoriesControllerComponent::class,
    SwiftRepositoriesControllerComponent::class,
    JavascriptRepositoriesControllerComponent::class
])
abstract class MainScreenBindingModule {

    @Binds
    @IntoMap
    @ControllerKey(ContainerController::class)
    abstract fun bindContainerInjector(builder: ContainerControllerComponent.Builder)
            : AndroidInjector.Factory<out Controller>

    @Binds
    @IntoMap
    @ControllerKey(JavaRepositoriesController::class)
    abstract fun bindJavaInjector(builder: JavaRepositoriesControllerComponent.Builder)
            : AndroidInjector.Factory<out Controller>

    @Binds
    @IntoMap
    @ControllerKey(SwiftRepositoriesController::class)
    abstract fun bindPythonInjector(builder: SwiftRepositoriesControllerComponent.Builder)
            : AndroidInjector.Factory<out Controller>

    @Binds
    @IntoMap
    @ControllerKey(JavascriptRepositoriesController::class)
    abstract fun bindJavaScriptInjector(builder: JavascriptRepositoriesControllerComponent.Builder)
            : AndroidInjector.Factory<out Controller>

}