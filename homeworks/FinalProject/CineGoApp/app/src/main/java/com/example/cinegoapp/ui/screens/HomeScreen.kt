package com.example.cinegoapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons.Default
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cinegoapp.R
import com.example.cinegoapp.ui.components.BottomBar
import com.example.cinegoapp.ui.components.MovieCard
import com.example.cinegoapp.ui.theme.Background
import com.example.cinegoapp.ui.theme.MainColor
import com.example.cinegoapp.ui.theme.TextColor
import com.example.cinegoapp.ui.theme.tektur
import com.example.cinegoapp.ui.viewmodel.FavoritesViewModel
import com.example.cinegoapp.ui.viewmodel.HomeScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    homeScreenViewModel: HomeScreenViewModel,
    favoritesViewModel: FavoritesViewModel,
) {
    val movies by homeScreenViewModel.moviesList.observeAsState()
    val isLoading by homeScreenViewModel.isLoading.observeAsState(initial = true)
    val searchQuery = remember { mutableStateOf("") }
    val isSearching = remember { mutableStateOf(false) }
    val selectedItem = remember { mutableStateOf(0) }
    val isFilterMenuExpanded = remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        homeScreenViewModel.fetchMovies()
    }

    Scaffold(
        containerColor = Background,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Background,
                    titleContentColor = TextColor
                ),
                title = {
                    if (isSearching.value) {
                        // Arama çubuğu
                        TextField(
                            value = searchQuery.value, // Arama sorgusu
                            onValueChange = {
                                searchQuery.value = it
                                homeScreenViewModel.searchMovies(it)
                            },
                            label = { Text(text = "Search movies", fontFamily = tektur,) },
                            modifier = Modifier
                                .fillMaxWidth(0.9f)
                                .height(52.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(Background),
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = MainColor,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                focusedLabelColor = TextColor,
                                unfocusedLabelColor = TextColor
                            ),
                            singleLine = true,
                            shape = RoundedCornerShape(12.dp)
                        )
                    } else {
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
                                    modifier = Modifier.size(150.dp).padding(start = 20.dp)
                                )
                            }
                        }
                    }
                },
                actions = {
                    if (isSearching.value) {
                        IconButton(onClick = {
                            isSearching.value = false
                            searchQuery.value = ""
                            homeScreenViewModel.fetchMovies()
                        }) {
                            Icon(
                                imageVector = Default.Close,
                                contentDescription = "Close search",
                                tint = TextColor,
                            )
                        }
                    } else {
                        IconButton(onClick = { isSearching.value = true }) {
                            Icon(
                                imageVector = Default.Search,
                                contentDescription = "Search",
                                tint = TextColor,
                            )
                        }
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
            if (selectedItem.value == 0) { // Home sekmesi
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    when {
                        isLoading -> {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.padding(16.dp)
                            ) {
                                CircularProgressIndicator(
                                    color = MainColor,
                                    modifier = Modifier.size(50.dp)
                                )
                                Text(
                                    text = "Fetching movies, please wait...",
                                    fontFamily = tektur,
                                    fontSize = 16.sp,
                                    color = TextColor,
                                    modifier = Modifier.padding(top = 16.dp)
                                )
                            }
                        }
                        movies.isNullOrEmpty() -> {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.padding(16.dp)
                            ) {
                                Icon(
                                    imageVector = Default.Warning,
                                    contentDescription = "No movies",
                                    modifier = Modifier.size(80.dp),
                                    tint = TextColor
                                )
                                Text(
                                    text = "No movies available right now.",
                                    fontFamily = tektur,
                                    fontSize = 16.sp,
                                    color = TextColor,
                                    modifier = Modifier.padding(top = 16.dp)
                                )
                            }
                        }
                        else -> {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(horizontal = 12.dp, vertical = 16.dp),
                            ) {
                                Row(
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.fillMaxWidth().padding(start = 12.dp, end = 10.dp)
                                ) {
                                    Text(
                                        text = "MOVIES",
                                        fontFamily = tektur,
                                        fontSize = 20.sp,
                                        color = TextColor,
                                        fontWeight = FontWeight.Bold,
                                    )
                                    Box(
                                        contentAlignment = Alignment.TopEnd,
                                        modifier = Modifier
                                            .border(1.dp, MainColor, RoundedCornerShape(8.dp))
                                            .clickable { isFilterMenuExpanded.value = true }
                                            .padding(horizontal = 12.dp, vertical = 8.dp)
                                    ) {
                                        Text(
                                            text = "Sort",
                                            fontFamily = tektur,
                                            fontSize = 14.sp,
                                            color = TextColor
                                        )
                                        DropdownMenu(
                                            expanded = isFilterMenuExpanded.value,
                                            onDismissRequest = { isFilterMenuExpanded.value = false },
                                            modifier = Modifier.background(MainColor)
                                        ) {
                                            DropdownMenuItem(
                                                text = { Text("A-Z", color = TextColor, fontFamily = tektur) },
                                                onClick = {
                                                    homeScreenViewModel.sortMoviesAZ()
                                                    isFilterMenuExpanded.value = false
                                                }
                                            )
                                            DropdownMenuItem(
                                                text = { Text("Z-A", color = TextColor, fontFamily = tektur) },
                                                onClick = {
                                                    homeScreenViewModel.sortMoviesZA()
                                                    isFilterMenuExpanded.value = false
                                                }
                                            )
                                            DropdownMenuItem(
                                                text = { Text("IMDB: Highest First", color = TextColor, fontFamily = tektur) },
                                                onClick = {
                                                    homeScreenViewModel.sortMoviesByRatingDescending()
                                                    isFilterMenuExpanded.value = false
                                                }
                                            )
                                            DropdownMenuItem(
                                                text = { Text("IMDB: Lowest First", color = TextColor, fontFamily = tektur) },
                                                onClick = {
                                                    homeScreenViewModel.sortMoviesByRatingAscending()
                                                    isFilterMenuExpanded.value = false
                                                }
                                            )
                                        }
                                    }
                                }
                                Spacer(modifier = Modifier.height(12.dp))
                                LazyVerticalGrid(
                                    columns = GridCells.Fixed(2),
                                    modifier = Modifier.fillMaxSize(),
                                    content = {
                                        items(movies!!) { movie ->
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
        }
    }
}