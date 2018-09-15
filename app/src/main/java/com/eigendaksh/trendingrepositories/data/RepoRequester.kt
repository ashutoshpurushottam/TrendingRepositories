package com.eigendaksh.trendingrepositories.data

import com.eigendaksh.trendingrepositories.model.Contributor
import com.eigendaksh.trendingrepositories.model.Repo
import com.eigendaksh.trendingrepositories.model.TrendingReposResponse
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RepoRequester @Inject constructor(private val service: RepoService) {

    fun getTrendingJavaRepos(): Single<List<Repo>> =
            service.getTrendingJavaRepos()
                    .map(TrendingReposResponse::repos)
                    //.subscribeOn(Schedulers.io())

    fun getTrendingSwiftRepos(): Single<List<Repo>> =
            service.getTrendingSwiftRepos()
                    .map(TrendingReposResponse::repos)
                    //.subscribeOn(Schedulers.io())

    fun getTrendingJavaScriptRepos(): Single<List<Repo>> =
            service.getTrendingJavascriptRepos()
                    .map(TrendingReposResponse::repos)
                    //.subscribeOn(Schedulers.io())

    fun getRepo(repoOwner: String, repoName: String) : Single<Repo> =
            service.getTrendingRepo(repoOwner, repoName)
                    //.subscribeOn(Schedulers.io())

    fun getContributors(url: String): Single<List<Contributor>> =
            service.getContributors(url)
                    //.subscribeOn(Schedulers.io())

}