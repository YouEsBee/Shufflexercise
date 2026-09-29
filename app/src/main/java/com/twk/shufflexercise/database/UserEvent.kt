package com.twk.shufflexercise.database

sealed interface UserEvent {
    object CreateUser: UserEvent
    data class SetUserName(val userName: String): UserEvent
    data class SortUser(val sortType: SortType): UserEvent
    data class DeleteUser(val user: User): UserEvent
}