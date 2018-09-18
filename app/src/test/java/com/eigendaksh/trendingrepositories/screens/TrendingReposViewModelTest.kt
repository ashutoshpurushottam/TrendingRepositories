package com.eigendaksh.trendingrepositories.screens

import com.eigendaksh.trendingrepositories.R
import com.eigendaksh.trendingrepositories.model.TrendingReposResponse
import com.eigendaksh.trendingrepositories.testutils.TestUtils

import org.junit.Before
import org.junit.Test

import io.reactivex.observers.TestObserver
import kotlinx.android.synthetic.main.screen_trending_repos.view.*
import java.io.IOException

class TrendingReposViewModelTest {

    private lateinit var viewModel: TrendingReposViewModel

    @Before
    fun setUp() {
        viewModel = TrendingReposViewModel()
    }

    @Test
    fun testLoading() {
    }

    @Test
    fun testReposLoading() {

    }

    @Test
    fun testError() {
    }


}