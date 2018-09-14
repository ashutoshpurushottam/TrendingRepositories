package com.eigendaksh.trendingrepositories.screens.swiftrepos

import com.eigendaksh.trendingrepositories.data.RepoRequester
import com.eigendaksh.trendingrepositories.model.Repo
import com.eigendaksh.trendingrepositories.screens.RepoAdapter
import com.eigendaksh.trendingrepositories.screens.TrendingReposViewModel
import timber.log.Timber
import javax.inject.Inject

class SwiftPythonReposPresenter  @Inject constructor(
        private val viewModel: TrendingReposViewModel,
        private val repoRequester: RepoRequester) : RepoAdapter.RepoClickedListener {

    init {
        loadRepos()
    }

    private fun loadRepos() {
        repoRequester.getTrendingSwiftRepos()
                .doOnSubscribe {
                    viewModel.setLoading().accept(true)
                }
                .doOnEvent { _, _ ->
                    viewModel.setLoading().accept(false)
                }
                .subscribe(viewModel.setRepos(), viewModel.onError())
    }

    override fun onRepoClicked(repo: Repo) {
        Timber.d("Repository clicked")
    }

}