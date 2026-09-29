package com.twk.shufflexercise.welcome

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.twk.shufflexercise.MainBoldText
import com.twk.shufflexercise.MainTextButton
import com.twk.shufflexercise.ui.theme.ShufflexerciseTheme


@Composable
fun WelcomeScreen(
    onContinueSuccess: () -> Unit,
    viewModel: WelcomeViewModel = viewModel()
) {

    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(uiState.isContinue) {
        if (uiState.isContinue) {
            onContinueSuccess()
        }
    }

    ShufflexerciseTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                MainBoldText(
                    message = "Shufflexercise",
                    desc = "Get Moving Today!",
                    modifier = Modifier.padding(8.dp)
                )
                MainTextButton(
                    label = "Continue",
                    onClick = {
                        onContinueSuccess()
                    }
                )
            }
        }
    }
}