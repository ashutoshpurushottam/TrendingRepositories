package com.eigendaksh.trendingrepositories.data

import com.eigendaksh.trendingrepositories.model.Repo
import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class RepoRepository @Inject constructor(
        private val repoRequestProvider: Provider<RepoRequester>) {

    private val cachedTrendingJavaRepos = mutableListOf<Repo>()
    private val cachedTrendingSwiftRepos = mutableListOf<Repo>()
    private val cachedTrendingJavaScriptRepos = mutableListOf<Repo>()


    // -------------------- Java ------------------------------- //

    fun getTrendingJavaRepos() : Single<List<Repo>> {
        return Maybe.concat(
                cachedTrendingJavaRepos(),
                apiTrendingJavaRepos())
                .firstOrError()
                .subscribeOn(Schedulers.io())
    }

    private fun cachedTrendingJavaRepos() : Maybe<List<Repo>> {
       return Maybe.create<List<Repo>> {
           if(!cachedTrendingJavaRepos.isEmpty()) {
               it.onSuccess(cachedTrendingJavaRepos)
           }
           it.onComplete()
       }
    }

    private fun apiTrendingJavaRepos() : Maybe<List<Repo>> {
        return repoRequestProvider.get().getTrendingJavaRepos()
                .doOnSuccess {
                    cachedTrendingJavaRepos.clear()
                    cachedTrendingJavaRepos.addAll(it)
                }
                .toMaybe()
    }

    // -------------------- SWIFT --------------------------//

    fun getTrendingSwiftRepos() : Single<List<Repo>> {
        return Maybe.concat(
                cachedTrendingSwiftRepos(),
                apiTrendingSwiftRepos())
                .firstOrError()
                .subscribeOn(Schedulers.io())
    }

    private fun cachedTrendingSwiftRepos() : Maybe<List<Repo>> {
        return Maybe.create<List<Repo>> {
            if(!cachedTrendingSwiftRepos.isEmpty()) {
                it.onSuccess(cachedTrendingSwiftRepos)
            }
            it.onComplete()
        }
    }

    private fun apiTrendingSwiftRepos() : Maybe<List<Repo>> {
        return repoRequestProvider.get().getTrendingSwiftRepos()
                .doOnSuccess {
                    cachedTrendingSwiftRepos.clear()
                    cachedTrendingSwiftRepos.addAll(it)
                }
                .toMaybe()
    }

    // -------------------- JAVASCRIPT --------------------------//

    fun getTrendingJavaScriptRepos() : Single<List<Repo>> {
        return Maybe.concat(
                cachedTrendingJavaScriptRepos(),
                apiTrendingJavaScriptRepos())
                .firstOrError()
                .subscribeOn(Schedulers.io())
    }

    private fun cachedTrendingJavaScriptRepos() : Maybe<List<Repo>> {
        return Maybe.create<List<Repo>> {
            if(!cachedTrendingJavaScriptRepos.isEmpty()) {
                it.onSuccess(cachedTrendingJavaScriptRepos)
            }
            it.onComplete()
        }
    }

    private fun apiTrendingJavaScriptRepos() : Maybe<List<Repo>> {
        return repoRequestProvider.get().getTrendingJavaScriptRepos()
                .doOnSuccess {
                    cachedTrendingJavaScriptRepos.clear()
                    cachedTrendingJavaScriptRepos.addAll(it)
                }
                .toMaybe()
    }

    // --------------------- Single Repo (Java) ---------------------------------//

    fun getJavaRepo(repoOwner: String, repoName: String) : Single<Repo> {
        return Maybe.concat(
                cachedJavaRepo(repoOwner, repoName),
                apiRepo(repoOwner, repoName))
                .firstOrError()

    }

    private fun cachedJavaRepo(repoOwner: String, repoName: String) : Maybe<Repo> {
        return Maybe.create<Repo> { it ->
            val repo: Repo? = cachedTrendingJavaRepos
                    .filter { it.name == repoName }
                    .firstOrNull { it.owner.login == repoOwner }
            repo?.run { it.onSuccess(this) } ?: it.onComplete()
        }
    }

    // --------------------- Single Repo (Swift) ---------------------------------//

    fun getSwiftRepo(repoOwner: String, repoName: String) : Single<Repo> {
        return Maybe.concat(
                cachedSwiftRepo(repoOwner, repoName),
                apiRepo(repoOwner, repoName))
                .firstOrError()

    }

    private fun cachedSwiftRepo(repoOwner: String, repoName: String) : Maybe<Repo> {
        return Maybe.create<Repo> { it ->
            val repo: Repo? = cachedTrendingSwiftRepos
                    .filter { it.name == repoName }
                    .firstOrNull { it.owner.login == repoOwner }
            repo?.run { it.onSuccess(this) } ?: it.onComplete()
        }
    }

    // --------------------- Single Repo (JavaScript) ---------------------------------//

    fun getJavaScriptRepo(repoOwner: String, repoName: String) : Single<Repo> {
        return Maybe.concat(
                cachedJavaScriptRepo(repoOwner, repoName),
                apiRepo(repoOwner, repoName))
                .firstOrError()

    }

    private fun cachedJavaScriptRepo(repoOwner: String, repoName: String) : Maybe<Repo> {
        return Maybe.create<Repo> { it ->
            val repo: Repo? = cachedTrendingJavaScriptRepos
                    .filter { it.name == repoName }
                    .firstOrNull { it.owner.login == repoOwner }
            repo?.run { it.onSuccess(this) } ?: it.onComplete()
        }
    }


    // ---------------------- API Call for Repo --------------------------------------//

    private fun apiRepo(repoOwner: String, repoName: String) : Maybe<Repo> =
        repoRequestProvider.get().getRepo(repoOwner, repoName).toMaybe()

}