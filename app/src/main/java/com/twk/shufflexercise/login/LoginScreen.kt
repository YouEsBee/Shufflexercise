package com.twk.shufflexercise.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.twk.shufflexercise.AltButton
import com.twk.shufflexercise.BackButton
import com.twk.shufflexercise.MainBoldText

@Composable
fun LoginScreen(onBack: () -> Unit) {
    Scaffold(
        contentWindowInsets = WindowInsets.safeDrawing
    ) { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(8.dp)
        ) {
            BackButton(
                modifier = Modifier.padding(innerPadding)
            ) {
                onBack()
            }
            Column(
                modifier = Modifier.fillMaxSize().padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                MainBoldText(
                    message = "Select Profile",
                    desc = "Choose which user you are",
                    modifier = Modifier.padding(innerPadding)
                )

                Text(
                    text = "Not these users?",
                    fontSize = 30.sp,
                    modifier = Modifier.padding(innerPadding)
                )

                AltButton(
                    label = "Create Profile"
                ) {}
            }
        }
    }
}