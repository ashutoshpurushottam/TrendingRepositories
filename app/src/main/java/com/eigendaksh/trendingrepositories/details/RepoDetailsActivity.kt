package com.eigendaksh.trendingrepositories.details

import android.os.Bundle
import com.bluelinelabs.conductor.Controller
import com.eigendaksh.trendingrepositories.R
import com.eigendaksh.trendingrepositories.base.BaseActivity

class RepoDetailsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        intent.extras.getString("repo_name")
//        intent.extras.getString("repo_owner")

    }

    override fun layoutRes(): Int = R.layout.activity_main

    override fun initialScreen(): Controller {

        val bundle = Bundle()
        // TODO: replace with repo_owner and repo_name coming from Main Activity
        bundle.putString("dummy1", "dummy1")
        bundle.putString("dummy2", "dummy2")

        return DetailContainerController(bundle)
    }

}
