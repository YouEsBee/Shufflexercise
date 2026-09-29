package com.twk.shufflexercise.createProfile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.twk.shufflexercise.database.SortType
import com.twk.shufflexercise.database.User
import com.twk.shufflexercise.database.UserDao
import com.twk.shufflexercise.database.UserEvent
import com.twk.shufflexercise.database.UserState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CreateProfileViewModel(
    private val dao: UserDao
): ViewModel() {
    private val _sortType = MutableStateFlow(SortType.USER_NAME)

    @OptIn(ExperimentalCoroutinesApi::class)
    private val _users = _sortType
        .flatMapLatest { sortType ->
            when(sortType) {
                SortType.USER_NAME -> dao.orderUserByUserName()
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val _state = MutableStateFlow(UserState())
    val state = combine(_state, _sortType, _users) { state, sortType, users ->
        state.copy(
            users = users,
            sortType = sortType
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), UserState())

    fun onEvent(event: UserEvent) {
        when (event) {
            UserEvent.CreateUser -> {
                val userName = state.value.userName

                if (userName.isBlank()) return

                val user = User(
                    userName = userName
                )

                viewModelScope.launch {
                    dao.insertUser(user)
                }

                _state.update { it.copy(
                    userName = "",
                    profilePicture = ""
                ) }
            }
            is UserEvent.DeleteUser -> {
                viewModelScope.launch {
                    dao.deleteUser(event.user)
                }
            }
            is UserEvent.SetUserName -> {
                _state.update { it.copy(
                    userName = event.userName
                ) }
            }
            is UserEvent.SortUser -> {
                _sortType.value = event.sortType
            }
        }
    }
}