package com.eigendaksh.trendingrepositories.screens.javarepos

import com.eigendaksh.trendingrepositories.data.RepoRepository
import com.eigendaksh.trendingrepositories.data.RepoRequester
import com.eigendaksh.trendingrepositories.model.Repo
import com.eigendaksh.trendingrepositories.model.TrendingReposResponse
import com.eigendaksh.trendingrepositories.screens.TrendingReposViewModel
import com.eigendaksh.trendingrepositories.testutils.TestUtils
import com.eigendaksh.trendingrepositories.ui.ScreenNavigator


import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations

import java.io.IOException

import io.reactivex.Single
import io.reactivex.functions.Consumer
import org.mockito.Mockito
import org.mockito.Mockito.*

class TrendingJavaReposPresenterTest {

    @Mock lateinit var repoRepository: RepoRepository
    @Mock lateinit var viewModel: TrendingReposViewModel
    @Mock lateinit var loadingConsumer: Consumer<Boolean>
    @Mock lateinit var successConsumer: Consumer<List<Repo>>
    @Mock lateinit var errorConsumer: Consumer<Throwable>
    @Mock lateinit var screenNavigator: ScreenNavigator

    private var presenter: TrendingJavaReposPresenter? = null

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        `when`(viewModel.setLoading()).thenReturn(loadingConsumer)
        `when`(viewModel.onError()).thenReturn(errorConsumer)
        `when`(viewModel.setRepos()).thenReturn(successConsumer)
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
        val response = TestUtils.loadJson("mock/get_trending_repos_java.json", TrendingReposResponse::class.java)

        val repos = response.repos
        `when`(repoRepository.getTrendingJavaRepos()).thenReturn(Single.just(repos))

        return repos
    }

    private fun setUpFailure(): Throwable {
        val error = IOException()
        `when`(repoRepository.getTrendingJavaRepos()).thenReturn(Single.error(error))
        return error
    }

    private fun initializePresenter() {
        presenter = TrendingJavaReposPresenter(viewModel, repoRepository, screenNavigator)
    }


}
