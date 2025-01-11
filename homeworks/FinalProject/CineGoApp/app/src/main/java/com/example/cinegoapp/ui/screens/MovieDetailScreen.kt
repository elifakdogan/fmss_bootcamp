package com.example.cinegoapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cinegoapp.R
import com.example.cinegoapp.data.entity.Movie
import com.example.cinegoapp.retrofit.ApiUtils.Companion.BASE_URL
import com.example.cinegoapp.ui.components.BottomBar
import com.example.cinegoapp.ui.theme.Background
import com.example.cinegoapp.ui.theme.CartColor
import com.example.cinegoapp.ui.theme.MainColor
import com.example.cinegoapp.ui.theme.TextColor
import com.example.cinegoapp.ui.theme.tektur
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
//Film detayının gösterildiği sayfa
fun MovieDetailScreen(
    navController: NavController,
    movie: Movie,
    onAddToCart: (Movie, Int) -> Unit,
    onBack: () -> Unit
) {
    var quantity by remember { mutableIntStateOf(1) }
    val selectedItem = remember { mutableIntStateOf(3) }
    var totalPrice by remember { mutableStateOf(movie.price) }
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

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
                    IconButton(onClick = { onBack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White)
                    }
                }
            )
        },
        bottomBar = {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "\$${"%.2f".format(totalPrice.toDouble())}",
                        fontFamily = tektur,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextColor)

                    Button(
                        onClick = {
                            onAddToCart(movie, quantity)
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar("Product has been added to cart.")
                                delay(3000)
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = MainColor),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            text = "Add to Cart",
                            color = Color.White,
                            fontFamily = tektur,
                            fontWeight = FontWeight.Bold)
                    }
                }
                BottomBar(
                    navController = navController,
                    selectedItem = selectedItem.value,
                    onItemSelected = { selectedItem.value = it }
                )
            }
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState) {
                Snackbar(
                    modifier = Modifier.padding(16.dp),
                    action = {
                        TextButton(onClick = { snackbarHostState.currentSnackbarData?.dismiss() }) {
                            Text(
                                text = "Close",
                                fontFamily = tektur,
                                color = TextColor
                            )
                        }
                    },
                    containerColor = MainColor
                ) {
                    Text(
                        text = " Product has been added to cart.",
                        fontFamily = tektur,
                        color = TextColor,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
        ) {
            Text(
                text = movie.name.uppercase(),
                fontFamily = tektur,
                fontSize = 20.sp,
                color = TextColor,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 17.dp, top = 15.dp)
            )
            Spacer(modifier = Modifier.height(6.dp))
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item {
                    GlideImage(
                        imageModel = "${BASE_URL}movies/images/${movie.image}",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(240.dp)
                            .clip(RoundedCornerShape(16.dp)),
                        contentScale = ContentScale.Crop
                    )
                }
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = movie.category,
                            fontFamily = tektur,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = TextColor,
                            textAlign = TextAlign.Start
                        )
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            repeat(5) { index ->
                                Icon(
                                    imageVector = Icons.Filled.Star,
                                    contentDescription = "Star icon",
                                    tint = if (index < movie.rating.toInt() / 2) Color.Yellow else CartColor,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "(${movie.rating})",
                                fontFamily = tektur,
                                fontSize = 14.sp,
                                color = TextColor
                            )
                        }
                    }
                }
                item {
                    Text(
                        text = "Year: ${movie.year}",
                        fontFamily = tektur,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 0.dp),
                        color = TextColor
                    )
                    Text(
                        text = "Director: ${movie.director}",
                        fontFamily = tektur,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 0.dp),
                        color = TextColor
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = movie.description,
                        fontFamily = tektur,
                        fontSize = 14.sp,
                        lineHeight = 20.sp,
                        color = TextColor
                    )
                }
                item {
                    // Sipariş miktarı kontrolü
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = {
                            if (quantity > 1) {
                                quantity--
                                totalPrice = movie.price * quantity
                            }
                        }) {
                            Icon(Icons.Default.KeyboardArrowDown, contentDescription = "Decrease quantity", tint = TextColor)
                        }
                        Text(
                            text = "$quantity",
                            fontFamily = tektur,
                            fontSize = 18.sp,
                            modifier = Modifier.padding(horizontal = 16.dp),
                            color = TextColor
                        )
                        IconButton(onClick = {
                            quantity++
                            totalPrice = movie.price * quantity
                        }) {
                            Icon(
                                Icons.Default.KeyboardArrowUp,
                                contentDescription = "Increase quantity",
                                tint = TextColor
                            )
                        }
                    }
                }
            }
        }
    }
}
