package com.eigendaksh.trendingrepositories.details

import com.eigendaksh.trendingrepositories.R
import com.eigendaksh.trendingrepositories.di.ScreenScope
import com.eigendaksh.trendingrepositories.model.Contributor
import com.eigendaksh.trendingrepositories.model.ContributorState
import com.eigendaksh.trendingrepositories.model.Repo
import com.eigendaksh.trendingrepositories.model.RepoDetailsState
import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.Observable
import io.reactivex.functions.Consumer
import org.threeten.bp.format.DateTimeFormatter
import timber.log.Timber
import javax.inject.Inject

@ScreenScope
class RepoDetailsViewModel @Inject constructor() {

    private val detailStateRelay: BehaviorRelay<RepoDetailsState> = BehaviorRelay.create()
    private val contributorStateRelay: BehaviorRelay<ContributorState> = BehaviorRelay.create()

    init {
        detailStateRelay.accept(RepoDetailsState(loading = true))
        contributorStateRelay.accept(ContributorState(loading = true))
    }

    fun details(): Observable<RepoDetailsState> = detailStateRelay

    fun contributors(): Observable<ContributorState> = contributorStateRelay

    fun processRepo(): Consumer<Repo> =
            Consumer {it ->
                detailStateRelay.accept(
                        RepoDetailsState(
                                loading = false,
                                name = it.name,
                                description = it.description,
                                createdDate = it.createdDate.format(DATE_FORMATTER),
                                updatedDate = it.updatedTime.format(DATE_FORMATTER)
                        )
                )
            }

    fun processContributors(): Consumer<List<Contributor>> =
            Consumer {it ->
                contributorStateRelay.accept(
                        ContributorState(
                                loading = false,
                                contributors = it
                        )
                )
            }

    fun detailsError(): Consumer<Throwable> =
            Consumer {it ->
                Timber.e(it, "Error loading repo details")
                detailStateRelay.accept(
                        RepoDetailsState(
                                loading = false,
                                errorRes = R.string.api_error_single_repo
                        )
                )
            }

    fun contributorsError(): Consumer<Throwable> =
            Consumer {it ->
                Timber.e(it, "Error loading contributors")
                contributorStateRelay.accept(
                        ContributorState(
                                loading = false,
                                errorRes = R.string.api_error_contributors
                        )
                )
            }

    private companion object {
        val DATE_FORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd, yyyy")
    }
}