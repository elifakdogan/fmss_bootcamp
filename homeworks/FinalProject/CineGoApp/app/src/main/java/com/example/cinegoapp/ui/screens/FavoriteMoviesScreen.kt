package com.example.cinegoapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cinegoapp.R
import com.example.cinegoapp.ui.components.BottomBar
import com.example.cinegoapp.ui.components.MovieCard
import com.example.cinegoapp.ui.theme.Background
import com.example.cinegoapp.ui.theme.CartColor
import com.example.cinegoapp.ui.theme.TextColor
import com.example.cinegoapp.ui.theme.tektur
import com.example.cinegoapp.ui.viewmodel.FavoritesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteMoviesScreen(navController: NavController, favoritesViewModel: FavoritesViewModel) {
    val favoriteMovies by favoritesViewModel.favoriteMoviesList.observeAsState(listOf()) // Favori filmler listesini getirir
    val selectedItem = remember { mutableIntStateOf(2) }
    Scaffold(
        containerColor = Background,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Background,
                    titleContentColor = TextColor
                ),
                title = {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.cinego_logo),
                                contentDescription = "App Logo",
                                modifier = Modifier.size(190.dp).padding(end = 60.dp)
                            )
                        }
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        },
        bottomBar = {
            BottomBar(
                navController = navController,
                selectedItem = selectedItem.value,
                onItemSelected = { selectedItem.value = it }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (favoriteMovies.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No favorite movies added yet.",
                        fontFamily = tektur,
                        fontSize = 22.sp,
                        color = CartColor
                    )
                }
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 8.dp, vertical = 16.dp),
                ) {
                    Text(
                        text = "Favorites",
                        fontFamily = tektur,
                        fontSize = 20.sp,
                        color = TextColor,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 10.dp)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier.fillMaxSize(),
                        content = {
                            items(favoriteMovies) { movie ->
                                MovieCard(
                                    movie = movie,
                                    favoritesViewModel = favoritesViewModel,
                                    onClick = {
                                        navController.navigate("movieDetailScreen/${movie.id}")
                                    },
                                )
                            }
                        }
                    )
                }
            }
        }

    }
}