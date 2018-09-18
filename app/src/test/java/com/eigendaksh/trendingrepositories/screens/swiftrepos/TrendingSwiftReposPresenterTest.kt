package com.eigendaksh.trendingrepositories.screens.swiftrepos

import com.eigendaksh.trendingrepositories.data.RepoRepository
import com.eigendaksh.trendingrepositories.data.RepoRequester
import com.eigendaksh.trendingrepositories.model.Repo
import com.eigendaksh.trendingrepositories.model.TrendingReposResponse
import com.eigendaksh.trendingrepositories.screens.TrendingReposViewModel
import com.eigendaksh.trendingrepositories.screens.javascriptrepos.TrendingJavascriptReposPresenter
import com.eigendaksh.trendingrepositories.testutils.TestUtils
import io.reactivex.Single
import io.reactivex.functions.Consumer
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.io.IOException

class TrendingSwiftReposPresenterTest {

    @Before
    fun setUp() {
    }

    @Test
    fun testReposLoaded() {
    }

    @Test
    @Throws(Exception::class)
    fun testReposLoadedError() {
    }

    @Test
    @Throws(Exception::class)
    fun testLoadingForSuccess() {
    }

    @Test
    @Throws(Exception::class)
    fun testLoadingForFailure() {
    }

    @Test
    @Throws(Exception::class)
    fun testRepoClicked() {
    }

    private fun setUpSuccess(): List<Repo> {
        return emptyList();
    }

    private fun setUpFailure(): Throwable {
        val error = IOException()
        return error
    }

    private fun initializePresenter() {
    }

}