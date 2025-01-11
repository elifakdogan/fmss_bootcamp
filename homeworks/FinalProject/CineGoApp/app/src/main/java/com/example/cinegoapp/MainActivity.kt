package com.example.cinegoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.cinegoapp.ui.screens.Navigation
import com.example.cinegoapp.ui.theme.CineGoAppTheme
import com.example.cinegoapp.ui.viewmodel.CartScreenViewModel
import com.example.cinegoapp.ui.viewmodel.FavoritesViewModel
import com.example.cinegoapp.ui.viewmodel.HomeScreenViewModel
import com.example.cinegoapp.ui.viewmodel.MovieDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val homeScreenViewModel:HomeScreenViewModel by viewModels()
    val cartScreenViewModel:CartScreenViewModel by viewModels()
    val favoritesViewModel:FavoritesViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CineGoAppTheme {
                Navigation(
                    homeScreenViewModel = homeScreenViewModel,
                    cartScreenViewModel = cartScreenViewModel,
                    favoritesViewModel = favoritesViewModel
                )
            }
        }
    }
}
