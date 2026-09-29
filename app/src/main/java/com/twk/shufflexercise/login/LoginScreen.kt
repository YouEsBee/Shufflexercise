package com.twk.shufflexercise.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.IconButton
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
import com.twk.shufflexercise.database.UserEvent
import com.twk.shufflexercise.database.UserState

@Composable
fun LoginScreen(
    onCreateProfileSuccess: () -> Unit,
    state: UserState,
    onEvent: (UserEvent) -> Unit,
    onBack: () -> Unit
) {
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

                LazyColumn(
                    contentPadding = innerPadding,
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(state.users) { user ->
                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(
                                modifier = Modifier.weight(1f)
                            ) {
                                // profile pic to be inserted here
                                ///////// ---------- /////////
                                Text (
                                    text = user.userName,
                                    fontSize = 20.sp
                                )
                            }
                            IconButton(onClick = {
                                onEvent(UserEvent.DeleteUser(user))
                            }) {
                                Text("Delete")
                            }
                        }
                    }
                }

                Text(
                    text = "Not these users?",
                    fontSize = 30.sp,
                    modifier = Modifier.padding(innerPadding)
                )

                AltButton(
                    label = "Create Profile"
                ) { onCreateProfileSuccess() }
            }
        }
    }
}