package com.twk.shufflexercise

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.twk.shufflexercise.login.LoginScreen
import com.twk.shufflexercise.ui.theme.ShufflexerciseTheme
import com.twk.shufflexercise.welcome.WelcomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShufflexerciseTheme {
                val navController = rememberNavController()

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
                        LoginScreen(onBack = { navController.popBackStack() })
                    }
                }
            }
        }
    }
}

@Composable
fun MainBoldText(message: String, desc: String, modifier: Modifier = Modifier) {
    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = message,
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Text(
            text = desc,
            fontSize = 30.sp,
            textAlign = TextAlign.Center
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
                text = label,
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