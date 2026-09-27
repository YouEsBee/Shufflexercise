package com.twk.shufflexercise.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WelcomeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(WelcomeUiState())
    val uiState: StateFlow<WelcomeUiState> = _uiState

    fun onContinueClick() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(isContinue = true)
            }
        }
    }
}