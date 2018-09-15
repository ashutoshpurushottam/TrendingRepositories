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

    @Mock lateinit var repoRepository: RepoRepository
    @Mock lateinit var viewModel: TrendingReposViewModel
    @Mock lateinit var loadingConsumer: Consumer<Boolean>
    @Mock lateinit var successConsumer: Consumer<List<Repo>>
    @Mock lateinit var errorConsumer: Consumer<Throwable>

    private var presenter: TrendingSwiftReposPresenter? = null

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Mockito.`when`(viewModel.setLoading()).thenReturn(loadingConsumer)
        Mockito.`when`(viewModel.onError()).thenReturn(errorConsumer)
        Mockito.`when`(viewModel.setRepos()).thenReturn(successConsumer)
    }

    @Test
    fun testReposLoaded() {
        val repos = setUpSuccess()
        initializePresenter()

        Mockito.verify<RepoRepository>(repoRepository).getTrendingSwiftRepos()
        Mockito.verify<Consumer<List<Repo>>>(successConsumer).accept(repos)
        Mockito.verifyZeroInteractions(errorConsumer)
    }

    @Test
    @Throws(Exception::class)
    fun testReposLoadedError() {
        val throwable = setUpFailure()
        initializePresenter()
        Mockito.verify(errorConsumer).accept(throwable)
        Mockito.verifyZeroInteractions(successConsumer)
    }

    @Test
    @Throws(Exception::class)
    fun testLoadingForSuccess() {
        setUpSuccess()
        initializePresenter()

        val inOrder = Mockito.inOrder(loadingConsumer)
        inOrder.verify(loadingConsumer).accept(true)
        inOrder.verify(loadingConsumer).accept(false)
    }

    @Test
    @Throws(Exception::class)
    fun testLoadingForFailure() {
        setUpFailure()
        initializePresenter()

        val inOrder = Mockito.inOrder(loadingConsumer)
        inOrder.verify(loadingConsumer).accept(true)
        inOrder.verify(loadingConsumer).accept(false)
    }

    @Test
    @Throws(Exception::class)
    fun testRepoClicked() {
        //TODO
    }

    private fun setUpSuccess(): List<Repo> {
        val response = TestUtils.loadJson("mock/get_trending_repos_swift.json", TrendingReposResponse::class.java)

        val repos = response.repos
        Mockito.`when`(repoRepository.getTrendingSwiftRepos()).thenReturn(Single.just(repos))

        return repos
    }

    private fun setUpFailure(): Throwable {
        val error = IOException()
        Mockito.`when`(repoRepository.getTrendingSwiftRepos()).thenReturn(Single.error(error))
        return error
    }

    private fun initializePresenter() {
        presenter = TrendingSwiftReposPresenter(viewModel, repoRepository)
    }

}