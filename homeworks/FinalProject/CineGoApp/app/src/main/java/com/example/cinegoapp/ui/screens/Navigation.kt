package com.example.cinegoapp.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.cinegoapp.retrofit.ApiUtils.Companion.BASE_USERNAME
import com.example.cinegoapp.ui.viewmodel.CartScreenViewModel
import com.example.cinegoapp.ui.viewmodel.FavoritesViewModel
import com.example.cinegoapp.ui.viewmodel.HomeScreenViewModel

@Composable
//Sayfalar arası geçiş yapan navigasyon
fun Navigation(
    homeScreenViewModel: HomeScreenViewModel,
    cartScreenViewModel: CartScreenViewModel,
    favoritesViewModel: FavoritesViewModel
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        // Ana ekran rotası
        composable("home") {
            HomeScreen(
                navController = navController,
                homeScreenViewModel = homeScreenViewModel,
                favoritesViewModel = favoritesViewModel,
            )
        }
        // Film detay ekranı rotası
        composable(
            "movieDetailScreen/{movieId}",
            arguments = listOf(navArgument("movieId") { type = NavType.IntType })
        ) {
            val movieId = it.arguments?.getInt("movieId")
            val movie = homeScreenViewModel.moviesList.value?.find { it.id == movieId }
            if (movie == null) {
                navController.popBackStack()
                return@composable
            }
            MovieDetailScreen(
                movie = movie,
                onAddToCart = { selectedMovie, quantity ->
                    cartScreenViewModel.addMovieToCart(
                        selectedMovie, quantity, BASE_USERNAME
                    )
                },
                onBack = { navController.popBackStack() },
                navController = navController
            )
        }
        // Sepet ekranı rotası
        composable("cart") {
            CartScreen(
                navController = navController,
                cartScreenViewModel = cartScreenViewModel
            )
        }
        // Favoriler ekranı rotası
        composable("favorites") {
            FavoriteMoviesScreen(
                navController = navController,
                favoritesViewModel = favoritesViewModel,
            )
        }
    }
}