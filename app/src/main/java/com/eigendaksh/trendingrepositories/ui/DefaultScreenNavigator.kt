package com.eigendaksh.trendingrepositories.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.eigendaksh.trendingrepositories.details.DetailContainerController
import com.eigendaksh.trendingrepositories.details.RepoDetailsActivity
import com.eigendaksh.trendingrepositories.details.RepoType
import com.eigendaksh.trendingrepositories.di.ActivityScope
import com.eigendaksh.trendingrepositories.utils.REPO_NAME_KEY
import com.eigendaksh.trendingrepositories.utils.REPO_OWNER_KEY
import com.eigendaksh.trendingrepositories.utils.REPO_TYPE_KEY
import javax.inject.Inject

@ActivityScope
class DefaultScreenNavigator @Inject constructor() : ScreenNavigator {

    private var router: Router? = null

    override fun initWithRouter(router: Router, rootScreen: Controller) {
        this.router = router
        if(!router.hasRootController()) {
            router.setRoot(RouterTransaction.with(rootScreen))
        }
    }

    override fun pop(): Boolean = router?.handleBack() ?: false

    override fun goToRepoDetails(context: Context, repoType: RepoType, repoOwner: String, repoName: String) {
        val bundle = Bundle()

        bundle.putInt(REPO_TYPE_KEY, repoType.ordinal)
        bundle.putString(REPO_OWNER_KEY, repoOwner)
        bundle.putString(REPO_NAME_KEY, repoName)

        val intent = Intent(context, RepoDetailsActivity::class.java)
        intent.putExtras(bundle)
        context.startActivity(intent)
    }


    override fun clear() {
        router = null
    }


}