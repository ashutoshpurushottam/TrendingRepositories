package com.eigendaksh.trendingrepositories.screens

import com.eigendaksh.trendingrepositories.R
import com.eigendaksh.trendingrepositories.di.ScreenScope
import com.eigendaksh.trendingrepositories.model.Repo
import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.Observable
import io.reactivex.functions.Consumer
import timber.log.Timber
import javax.inject.Inject

@ScreenScope
class TrendingReposViewModel @Inject constructor() {

    private val reposRelay = BehaviorRelay.create<List<Repo>>()
    private val errorRelay = BehaviorRelay.create<Int>()
    private val loadingRelay = BehaviorRelay.create<Boolean>()

    // Expose as Observables to View(s)
    fun getLoading(): Observable<Boolean> = loadingRelay

    fun getRepos(): Observable<List<Repo>> = reposRelay

    fun getError(): Observable<Int> = errorRelay


    // Expose as consumer to outside
    fun setLoading(): Consumer<Boolean> = loadingRelay

    fun setRepos(): Consumer<List<Repo>> {
        errorRelay.accept(-1)
        return reposRelay
    }

    fun onError(): Consumer<Throwable> = Consumer {
        Timber.e(it, "Error loading repos")
        errorRelay.accept(R.string.api_error_repos)
    }
}