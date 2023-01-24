package fr.latchi.latchisapp

import fr.latchi.latchisapp.ui.theme.Navigation
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import fr.latchi.latchisapp.ui.theme.LaTchisAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LaTchisAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigation()
                }
            }
        }
    }
}

@Preview(
    showBackground = false,
    showSystemUi = true
)
@Composable
fun BirthdayCardPreview() {
    LaTchisAppTheme {
        //GreetingWithImage(brand = "LaTchi", firstname = "Cibee")
    }
}
