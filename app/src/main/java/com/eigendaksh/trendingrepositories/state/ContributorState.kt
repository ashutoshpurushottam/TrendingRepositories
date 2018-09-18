package com.eigendaksh.trendingrepositories.state

import com.eigendaksh.trendingrepositories.model.Contributor

data class ContributorState public constructor(
        val loading: Boolean = false,
        val contributors: List<Contributor>? = null,
        val errorRes: Int = -1) {

    fun isSuccess(): Boolean = errorRes == -1
}