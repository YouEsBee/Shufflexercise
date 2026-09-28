package com.twk.shufflexercise.createProfile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.twk.shufflexercise.BackButton
import com.twk.shufflexercise.MainBoldText
import com.twk.shufflexercise.MainTextButton
import com.twk.shufflexercise.TextInputField

@Composable
fun CreateProfileScreen(onBack: () -> Unit) {
    var userNameInput by remember { mutableStateOf("") }

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

            MainBoldText(
                message = "Create Your Profile",
                desc = "Enter your details below.",
                modifier = Modifier.padding(innerPadding)
            )

            TextInputField(
                label = "Enter A User Name",
                value = userNameInput,
                onValueChange = { userNameInput = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal=16.dp)
                    .padding(innerPadding)
            )

            // Add select profile pic button later
            /////////// SOME CODE ///////////

            // Add create profile functionality by writing inputs into Room DB
            MainTextButton(
                label = "Create Profile"
            ) { }
        }
    }
}