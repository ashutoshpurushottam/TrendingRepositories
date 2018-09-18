package com.eigendaksh.trendingrepositories.ui

import android.content.Context
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.Router
import com.eigendaksh.trendingrepositories.details.RepoType

interface ScreenNavigator {

    fun initWithRouter(router: Router, rootScreen: Controller)
    fun pop() : Boolean
    fun goToRepoDetails(context: Context, repoType: RepoType, repoOwner: String, repoName: String)
    fun clear()
}