package com.example.cinegoapp.ui.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons.Default
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cinegoapp.ui.theme.MainColor
import com.example.cinegoapp.ui.theme.TextColor
import com.example.cinegoapp.ui.theme.tektur

@Composable
fun BottomBar(navController: NavController, selectedItem: Int, onItemSelected: (Int) -> Unit) {
    // Alt navigasyon çubuğu
    BottomAppBar(
        modifier = Modifier.height(72.dp),
        containerColor = MainColor
    ) {
        // Anasayfa sayfasına yönlendiren buton
        NavigationBarItem(
            selected = selectedItem == 0,
            onClick = {
                onItemSelected(0)
                navController.navigate("home")
            },
            label = {
                if (selectedItem == 0) {
                    Text(text = "Home", color = TextColor, fontFamily = tektur)
                }
            },
            icon = {
                Icon(
                    imageVector = Default.Home,
                    contentDescription = "Empty Cart",
                    modifier = Modifier.size(25.dp),
                    tint = TextColor
                )
            },
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = MainColor
            )
        )
        // Favoriler sayfasına yönlendiren buton
        NavigationBarItem(
            selected = selectedItem == 2,
            onClick = {
                onItemSelected(2)
                navController.navigate("favorites")
            },
            label = {
                if (selectedItem == 2) {
                    Text(text = "Favorites", color = TextColor, fontFamily = tektur)
                }
            },
            icon = {
                Icon(
                    imageVector = Default.Favorite,
                    contentDescription = "Empty Cart",
                    modifier = Modifier.size(25.dp),
                    tint = TextColor
                )
            },
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = MainColor
            )
        )
        // Sepet sayfasına yönlendiren buton
        NavigationBarItem(
            selected = selectedItem == 1,
            onClick = {
                onItemSelected(1)
                navController.navigate("cart")
            },
            label = {
                if (selectedItem == 1) {
                    Text(text = "Cart", color = TextColor, fontFamily = tektur)
                }
            },
            icon = {
                Icon(
                    imageVector = Default.ShoppingCart,
                    contentDescription = "Empty Cart",
                    modifier = Modifier.size(25.dp),
                    tint = TextColor
                )
            },
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = MainColor
            )
        )
    }
}
