package com.example.cinegoapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cinegoapp.data.entity.MovieCart
import com.example.cinegoapp.retrofit.ApiUtils.Companion.BASE_URL
import com.example.cinegoapp.ui.theme.CartColor
import com.example.cinegoapp.ui.theme.MainColor
import com.example.cinegoapp.ui.theme.TextColor
import com.example.cinegoapp.ui.theme.tektur
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun CartItemCard(
    movieCart: MovieCart,
    onRemove: () -> Unit,
    onClick: () -> Unit
) {
    // Sepetteki filmi gösteren card
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = cardColors(containerColor = MainColor)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Film görseli GlideImage kullanılarak yükleniyor
            GlideImage(
                imageModel = "${BASE_URL}movies/images/${movieCart.image}",
                modifier = Modifier
                    .height(100.dp)
                    .aspectRatio(1f)
                    .padding(end = 16.dp),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = movieCart.name,
                    fontSize = 18.sp,
                    fontFamily = tektur,
                    fontWeight = FontWeight.Bold,
                    color = CartColor
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Price: ₺${movieCart.price}",
                    fontSize = 14.sp,
                    fontFamily = tektur,
                    color = TextColor
                )
                Text(
                    text = "Quantity: ${movieCart.orderAmount}",
                    fontSize = 14.sp,
                    fontFamily = tektur,
                    color = TextColor
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Toplam fiyat (fiyat x miktar)
                Text(
                    text = "₺${movieCart.price * movieCart.orderAmount}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = tektur,
                    color = MainColor
                )
                // Silme ikonu
                IconButton(onClick = onRemove) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Remove from Cart",
                        tint = CartColor
                    )
                }
            }
        }
    }
}