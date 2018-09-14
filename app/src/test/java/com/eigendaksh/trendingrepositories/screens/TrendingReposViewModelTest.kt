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
        val testObserver = viewModel.getLoading().test()

        viewModel.setLoading().accept(true)
        viewModel.setLoading().accept(false)

        testObserver.assertValues(true, false)
    }

    @Test
    fun testReposLoading() {
        val response = TestUtils.loadJson("mock/get_trending_repos_java.json", TrendingReposResponse::class.java)

        viewModel.setRepos().accept(response.repos)

        viewModel.getRepos().test().assertValue(response.repos)

    }

    @Test
    fun testError() {
        val testObserver = viewModel.getError().test()

        viewModel.onError().accept(IOException())
        viewModel.setRepos().accept(emptyList())

        testObserver.assertValues(R.string.api_error_repos, -1)
    }


}