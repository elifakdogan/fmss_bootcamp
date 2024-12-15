package com.example.pageflow.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun SelectedPage () {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "Homepage") {
        composable("Homepage") {
            Homepage(navController = navController)
        }
        composable("PageA") {
            PageA(navController = navController)
        }
        composable("PageB") {
            PageB(navController = navController)
        }
        composable("PageX") {
            PageX(navController = navController)
        }
        composable("PageY") {
            PageY(navController = navController)
        }
    }
}