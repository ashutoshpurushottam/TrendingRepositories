package com.eigendaksh.trendingrepositories.details

import android.os.Bundle
import com.bluelinelabs.conductor.Controller
import com.eigendaksh.trendingrepositories.R
import com.eigendaksh.trendingrepositories.base.BaseController

class DetailContainerController(bundle: Bundle) : BaseController(bundle) {

    override fun layoutRes(): Int = R.layout.screen_repo_details

    companion object {

        const val REPO_NAME_KEY = "repo_name"
        const val REPO_OWNER_KEY = "repo_owner"

        fun newInstance(repoName: String, repoOwner: String): Controller {
            val bundle = Bundle()
            bundle.putString(REPO_NAME_KEY, repoName)
            bundle.putString(REPO_OWNER_KEY, repoOwner)
            return DetailContainerController(bundle)
        }

    }


}