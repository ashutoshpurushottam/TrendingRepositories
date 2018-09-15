package com.eigendaksh.trendingrepositories.data

import com.eigendaksh.trendingrepositories.model.Repo
import com.eigendaksh.trendingrepositories.model.TrendingReposResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface RepoService {

    @GET("search/repositories?q=language:java&order=desc&sort=stars")
    fun getTrendingJavaRepos(): Single<TrendingReposResponse>

    @GET("search/repositories?q=language:swift&order=desc&sort=stars")
    fun getTrendingSwiftRepos(): Single<TrendingReposResponse>

    @GET("search/repositories?q=language:javascript&order=desc&sort=stars")
    fun getTrendingJavascriptRepos(): Single<TrendingReposResponse>

    @GET("repos/{owner}/{name}")
    fun getTrendingRepo(@Path("owner") repoOwner: String, @Path("name")repoName: String):
            Single<Repo>

}