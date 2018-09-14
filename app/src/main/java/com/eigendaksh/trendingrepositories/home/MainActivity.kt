package com.eigendaksh.trendingrepositories.home

import com.bluelinelabs.conductor.Controller
import com.eigendaksh.trendingrepositories.R
import com.eigendaksh.trendingrepositories.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun layoutRes(): Int = R.layout.activity_main
    override fun initialScreen(): Controller = ContainerController()

}
