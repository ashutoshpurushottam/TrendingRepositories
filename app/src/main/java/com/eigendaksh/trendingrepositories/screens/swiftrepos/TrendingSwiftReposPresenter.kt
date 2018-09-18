package com.eigendaksh.trendingrepositories.screens.swiftrepos

import android.content.Context
import com.eigendaksh.trendingrepositories.data.RepoRepository
import com.eigendaksh.trendingrepositories.data.RepoRequester
import com.eigendaksh.trendingrepositories.details.RepoType
import com.eigendaksh.trendingrepositories.di.ScreenScope
import com.eigendaksh.trendingrepositories.model.Repo
import com.eigendaksh.trendingrepositories.screens.RepoAdapter
import com.eigendaksh.trendingrepositories.screens.TrendingReposViewModel
import com.eigendaksh.trendingrepositories.ui.ScreenNavigator
import timber.log.Timber
import javax.inject.Inject

@ScreenScope
class TrendingSwiftReposPresenter  @Inject constructor(
        private val viewModel: TrendingReposViewModel,
        private val repoRepository: RepoRepository,
        private val screenNavigator: ScreenNavigator) : RepoAdapter.RepoClickedListener {

    init {
        loadRepos()
    }

    private fun loadRepos() {
        repoRepository.getTrendingSwiftRepos()
                .doOnSubscribe {
                    viewModel.setLoading().accept(true)
                }
                .doOnEvent { _, _ ->
                    viewModel.setLoading().accept(false)
                }
                .subscribe(viewModel.setRepos(), viewModel.onError())
    }

    override fun onRepoClicked(context: Context, repo: Repo) {
        screenNavigator.goToRepoDetails(context, RepoType.Swift, repo.owner.login, repo.name)
    }
}