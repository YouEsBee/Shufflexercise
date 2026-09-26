package com.twk.shufflexercise

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.twk.shufflexercise.ui.theme.ShufflexerciseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
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
                        GreetingText(
                            message = "Shufflexercise",
                            desc = "Get Moving Today!",
                            modifier = Modifier.padding(8.dp)
                        )
                        MainButton(
                            label = "Continue"
                        ) {}
                    }
                }
            }
        }
    }
}

@Composable
fun GreetingText(message: String, desc: String, modifier: Modifier = Modifier) {
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
fun MainButton(label: String, onClick: () -> Unit) {
    Column(Modifier
        .fillMaxWidth()
        .absolutePadding(30.dp, 120.dp, 30.dp, 0.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = { onClick() }) {
            Text(
                text = label,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

// preview code
//@Preview(
//    showBackground = true,
//    showSystemUi = true
//)
//@Composable
//fun GreetingPreview() {
//    ShufflexerciseTheme {
//        GreetingText(message = "Shufflexercise", desc = "Get Moving Today!")
//    }
//}