package com.twk.shufflexercise

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room3.Room
import com.twk.shufflexercise.createProfile.CreateProfileScreen
import com.twk.shufflexercise.createProfile.CreateProfileViewModel
import com.twk.shufflexercise.database.UserDatabase
import com.twk.shufflexercise.login.LoginScreen
import com.twk.shufflexercise.ui.theme.ShufflexerciseTheme
import com.twk.shufflexercise.welcome.WelcomeScreen

class MainActivity : ComponentActivity() {

    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            UserDatabase::class.java,
            "users.db"
        ).build()
    }

    private val viewModel by viewModels<CreateProfileViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    @Suppress("UNCHECKED_CAST")
                    return CreateProfileViewModel(db.dao) as T
                }
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShufflexerciseTheme {
                val navController = rememberNavController()
                val state by viewModel.state.collectAsState()

                NavHost(
                    navController = navController,
                    startDestination = "welcome"
                ) {
                    composable("welcome") {
                        WelcomeScreen(
                            onContinueSuccess = {
                                navController.navigate("login")
                            }
                        )
                    }
                    composable("login") {
                        LoginScreen(
                            onCreateProfileSuccess = {
                                navController.navigate("createProfile")
                            },
                            onBack = {
                                navController.popBackStack()
                            },
                            state = state,
                            onEvent = viewModel::onEvent
                        )
                    }
                    composable("createProfile") {
                        CreateProfileScreen(
                            onBack = {
                                navController.popBackStack()
                            },
                            state = state,
                            onEvent = viewModel::onEvent
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun MainBoldText(message: String, desc: String, modifier: Modifier = Modifier) {
    val bigFontSize = 50
    val smallFontSize = 40
    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = message,
            fontSize = (bigFontSize).sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            lineHeight = (bigFontSize + 10).sp
        )
        Text(
            text = desc,
            fontSize = (smallFontSize).sp,
            textAlign = TextAlign.Center,
            lineHeight = (smallFontSize + 10).sp
        )
    }
}

@Composable
fun MainTextButton(label: String, onClick: () -> Unit) {
    Column(
        Modifier
        .fillMaxWidth()
        .absolutePadding(30.dp, 120.dp, 30.dp, 0.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { onClick() }) {
            Text(
                text = label,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun BackButton(modifier: Modifier = Modifier, label: String = "Back", onClick: () -> Unit) {
    Column(
        modifier = modifier
    ) {
        TextButton(
            onClick = { onClick() }
        ) {
            Text(
                text = "< $label",
                fontSize = 30.sp
            )
        }
    }

}

@Composable
fun AltButton(label: String, onClick: () -> Unit) {
    TextButton(
        onClick = { onClick() }
    ) {
        Text(
            text = label,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
        )
    }
}

@Composable
fun TextInputField(label: String, value: String, onValueChange: (String) -> Unit, modifier: Modifier = Modifier, placeholder: String = "") {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(
                text = label,
                fontSize = 30.sp
            )
        },
        placeholder = {
            Text(
                text = placeholder,
                fontSize = 40.sp
            )
        },
        modifier = modifier,
        textStyle = LocalTextStyle.current.copy(fontSize = 40.sp)
    )
}