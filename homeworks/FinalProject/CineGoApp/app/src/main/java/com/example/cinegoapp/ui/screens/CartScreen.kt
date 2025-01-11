package com.example.cinegoapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons.Default
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cinegoapp.R
import com.example.cinegoapp.retrofit.ApiUtils.Companion.BASE_USERNAME
import com.example.cinegoapp.ui.components.BottomBar
import com.example.cinegoapp.ui.components.CartItemCard
import com.example.cinegoapp.ui.theme.Background
import com.example.cinegoapp.ui.theme.CartColor
import com.example.cinegoapp.ui.theme.MainColor
import com.example.cinegoapp.ui.theme.TextColor
import com.example.cinegoapp.ui.theme.tektur
import com.example.cinegoapp.ui.viewmodel.CartScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(navController: NavController, cartScreenViewModel: CartScreenViewModel) {
    val cartMovies by cartScreenViewModel.cartMovies.collectAsState(initial = emptyList())
    val movies by cartScreenViewModel.moviesList.observeAsState(initial = emptyList())
    // Aynı ada sahip filmleri birleştirip toplam miktarları hesaplar
    val mergedCartMovies by remember {
        derivedStateOf {
            cartMovies.groupBy { it.name }.map { (name, movies) ->
                movies.reduce { acc, movie ->
                    acc.copy(orderAmount = acc.orderAmount + movie.orderAmount)
                }
            }
        }
    }
    // Toplam maliyeti hesaplar
    val totalCost = remember(mergedCartMovies) { mergedCartMovies.sumOf { it.price * it.orderAmount } }
    val selectedItem = remember { mutableStateOf(1) }
    var showConfirmation by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }

    // Sepet filmlerini ve tüm filmleri yükler
    LaunchedEffect(Unit) {
        cartScreenViewModel.fetchCartMovies()
        cartScreenViewModel.fetchMovies()
    }

    // Ödeme onayı gösterildiğinde snackbar tetikler
    LaunchedEffect(showConfirmation) {
        if (showConfirmation) {
            snackbarHostState.showSnackbar("Payment has been confirmed.")
            kotlinx.coroutines.delay(3000)
            showConfirmation = false
        }
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
                            imageVector = Default.ArrowBack,
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
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState) {
                Snackbar(
                    modifier = Modifier.padding(16.dp),
                    containerColor = MainColor,
                    action = {
                        TextButton(onClick = { snackbarHostState.currentSnackbarData?.dismiss() }) {
                            Text(text = "Close", color = TextColor, fontFamily = tektur)
                        }
                    }
                ) {
                    Text(
                        text = "Your cart has been checked out.",
                        color = TextColor,
                        fontFamily = tektur,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(paddingValues)
        ) {
            if (mergedCartMovies.isEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Default.ShoppingCart,
                        contentDescription = "Empty Cart",
                        modifier = Modifier.size(64.dp),
                        tint = CartColor
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Cart is empty!",
                        fontFamily = tektur,
                        fontSize = 22.sp,
                        color = CartColor
                    )
                }
            } else {
                Text(
                    text = "CART",
                    fontFamily = tektur,
                    fontSize = 20.sp,
                    color = TextColor,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 8.dp)
                )
                Spacer(modifier = Modifier.height(12.dp))
                LazyColumn(modifier = Modifier.weight(1f)) {
                    items(mergedCartMovies) { movieCart ->
                        CartItemCard(
                            movieCart = movieCart,
                            onRemove = {
                                cartMovies
                                    .filter { it.name == movieCart.name }
                                    .forEach { cartScreenViewModel.removeMovieFromCart(it.cartId, BASE_USERNAME) }
                            },
                            onClick = {
                                val matchedMovie = movies?.find { it.name == movieCart.name }
                                matchedMovie?.let {
                                    navController.navigate("movieDetailScreen/${it.id}")
                                }
                            }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Total: ₺$totalCost",
                    fontFamily = tektur,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextColor
                )
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = { showConfirmation = true },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            brush = Brush.horizontalGradient(
                                listOf(MainColor, CartColor)
                            ),
                            shape = RoundedCornerShape(16.dp)
                        ),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                ) {
                    Text(
                        text = "Checkout",
                        color = TextColor,
                        fontFamily = tektur,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}
