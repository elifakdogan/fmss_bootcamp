package com.example.cinegoapp.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cinegoapp.data.entity.Movie
import com.example.cinegoapp.retrofit.ApiUtils.Companion.BASE_URL
import com.example.cinegoapp.ui.theme.MainColor
import com.example.cinegoapp.ui.theme.CartColor
import com.example.cinegoapp.ui.theme.TextColor
import com.example.cinegoapp.ui.theme.tektur
import com.example.cinegoapp.ui.viewmodel.FavoritesViewModel
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun MovieCard(
    movie: Movie, // Gösterilecek film bilgisi
    favoritesViewModel: FavoritesViewModel,
    onClick: () -> Unit,
) {
    val isFavorite = favoritesViewModel.isFavorite(movie) // Film favorilerde mi kontrol edilir
    var favoriteState by remember { mutableStateOf(isFavorite) }
    val scale by animateFloatAsState(targetValue = if (favoriteState) 1.2f else 1f, label = "") // Favori ikonunun büyüme/küçülme animasyonu

    // Filme ait card
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        onClick = onClick,
    ) {
        Column {
            Box(modifier = Modifier.fillMaxWidth()) {
                GlideImage(
                    imageModel = "${BASE_URL}movies/images/${movie.image}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(2f / 3f)
                )
                // Favori ikonunu göstermek ve favori durumunu değiştirmek için
                Icon(
                    imageVector = if (favoriteState) Icons.Filled.Favorite else Icons.Outlined.Favorite,
                    contentDescription = "Favorite Icon",
                    tint = if (favoriteState) Color.Red else Color.Gray,
                    modifier = Modifier
                        .align(Alignment.TopEnd) // Correctly scoped
                        .padding(8.dp)
                        .clickable {
                            favoriteState = !favoriteState
                            if (favoriteState) {
                                favoritesViewModel.addFavorite(movie)
                            } else {
                                favoritesViewModel.removeFavorite(movie)
                            }
                        }
                        .size((24 * scale).dp)
                )
            }
            // Film detaylarını göstermek için
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MainColor)
                    .padding(16.dp)
            ) {
                Text(text = movie.name, fontFamily = tektur, fontSize = 16.sp, color = TextColor)
                // Film puanı ve yıldızlar
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 4.dp)
                ) {
                    repeat(5) { index ->
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = "Star icon",
                            tint = if (index < movie.rating.toInt()/2) Color.Yellow else CartColor, // Puanlamaya göre yıldız rengi
                            modifier = Modifier.size(16.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "(${movie.rating})",
                        fontFamily = tektur,
                        fontSize = 12.sp,
                        color = CartColor
                    )
                }
            }

        }
    }
}

