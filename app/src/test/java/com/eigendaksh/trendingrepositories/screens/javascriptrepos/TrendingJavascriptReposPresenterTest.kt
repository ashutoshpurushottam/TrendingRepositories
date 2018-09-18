package com.eigendaksh.trendingrepositories.screens.javascriptrepos

import com.eigendaksh.trendingrepositories.data.RepoRepository
import com.eigendaksh.trendingrepositories.data.RepoRequester
import com.eigendaksh.trendingrepositories.model.Repo
import com.eigendaksh.trendingrepositories.model.TrendingReposResponse
import com.eigendaksh.trendingrepositories.screens.TrendingReposViewModel
import com.eigendaksh.trendingrepositories.screens.javarepos.TrendingJavaReposPresenter
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

class TrendingJavascriptReposPresenterTest {

    @Mock lateinit var repoRepository: RepoRepository
    @Mock lateinit var viewModel: TrendingReposViewModel
    @Mock lateinit var loadingConsumer: Consumer<Boolean>
    @Mock lateinit var successConsumer: Consumer<List<Repo>>
    @Mock lateinit var errorConsumer: Consumer<Throwable>

    private var presenter: TrendingJavascriptReposPresenter? = null

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
        val response = TestUtils.loadJson("mock/get_trending_repos_javascript.json", TrendingReposResponse::class.java)

        val repos = response.repos
        Mockito.`when`(repoRepository.getTrendingJavaScriptRepos()).thenReturn(Single.just(repos))

        return repos
    }

    private fun setUpFailure(): Throwable {
        val error = IOException()
        Mockito.`when`(repoRepository.getTrendingJavaScriptRepos()).thenReturn(Single.error(error))
        return error
    }

    private fun initializePresenter() {
//        presenter = TrendingJavascriptReposPresenter(viewModel, repoRepository)
    }

}