package com.twk.shufflexercise.database

data class UserState(
    val users: List<User> = emptyList(),
    val userName: String = "",
    val profilePicture: String? = "",
    val sortType: SortType = SortType.USER_NAME
)