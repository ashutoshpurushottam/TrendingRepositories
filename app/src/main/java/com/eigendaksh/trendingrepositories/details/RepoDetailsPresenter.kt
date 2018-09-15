package com.eigendaksh.trendingrepositories.details

import com.eigendaksh.trendingrepositories.data.RepoRepository
import com.eigendaksh.trendingrepositories.di.ScreenScope
import com.eigendaksh.trendingrepositories.model.Repo
import io.reactivex.Single
import io.reactivex.functions.Consumer
import javax.inject.Inject
import javax.inject.Named

@ScreenScope
class RepoDetailsPresenter @Inject constructor(
        val repoType: RepoType,
        @Named("repo_name") val repoName: String,
        @Named("repo_owner") val repoOwner: String,
        val repoRepository: RepoRepository,
        val repoDetailsViewModel: RepoDetailsViewModel) {

    init {
        val repoSingle : Single<Repo>
        when(repoType) {
            RepoType.Java -> repoSingle = repoRepository.getJavaRepo(repoOwner, repoName)
            RepoType.Swift -> repoSingle = repoRepository.getSwiftRepo(repoOwner, repoName)
            RepoType.JavaScript -> repoSingle = repoRepository.getJavaScriptRepo(repoOwner, repoName)
        }

        repoSingle
                .doOnSuccess(repoDetailsViewModel.processRepo())
                .doOnError(repoDetailsViewModel.detailsError())
                .flatMap {
                    repoRepository.getContributors(it.contributorsUrl)
                            .doOnError(repoDetailsViewModel.contributorsError())
                }
                .subscribe(repoDetailsViewModel.processContributors(), Consumer {
                    // Logging is handled in viewmodel for error
                } )
    }


}