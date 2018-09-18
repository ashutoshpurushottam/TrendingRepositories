package com.eigendaksh.trendingrepositories.state

data class RepoDetailsState constructor(val loading: Boolean = false,
                                        val name: String? = null,
                                        val description: String? = null,
                                        val createdDate: String? = null,
                                        val updatedDate: String? = null,
                                        val errorRes: Int = -1) {

    fun isSuccess(): Boolean = errorRes == -1
}