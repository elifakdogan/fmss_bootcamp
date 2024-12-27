package com.example.cineverseclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.cineverseclone.ui.screens.Homepage
import com.example.cineverseclone.ui.theme.CineverseCloneTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CineverseCloneTheme {
                Homepage()
            }
        }
    }
}

