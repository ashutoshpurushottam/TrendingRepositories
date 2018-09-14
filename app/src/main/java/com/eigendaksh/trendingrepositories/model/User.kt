package com.eigendaksh.trendingrepositories.model

import se.ansman.kotshi.JsonSerializable

@JsonSerializable
data class User(
        val id: Long,
        val login: String)