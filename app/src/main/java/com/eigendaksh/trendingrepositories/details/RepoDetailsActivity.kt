package com.eigendaksh.trendingrepositories.details

import com.bluelinelabs.conductor.Controller
import com.eigendaksh.trendingrepositories.R
import com.eigendaksh.trendingrepositories.base.BaseActivity
import com.eigendaksh.trendingrepositories.utils.REPO_NAME_KEY
import com.eigendaksh.trendingrepositories.utils.REPO_OWNER_KEY
import com.eigendaksh.trendingrepositories.utils.REPO_TYPE_KEY

class RepoDetailsActivity : BaseActivity() {

    override fun layoutRes(): Int = R.layout.activity_main

    override fun initialScreen(): Controller {

        return DetailContainerController.newInstance(
                intent.extras.getInt(REPO_TYPE_KEY),
                intent.extras.getString(REPO_OWNER_KEY),
                intent.extras.getString(REPO_NAME_KEY)
        )
    }

}
