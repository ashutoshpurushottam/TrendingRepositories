package com.eigendaksh.trendingrepositories.data

import com.eigendaksh.trendingrepositories.model.TrendingReposResponse
import io.reactivex.Single
import retrofit2.http.GET

interface RepoService {

    @GET("search/repositories?q=language:java&order=desc&sort=stars")
    fun getTrendingJavaRepos(): Single<TrendingReposResponse>
}