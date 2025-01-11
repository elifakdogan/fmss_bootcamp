package com.example.cinegoapp.data.datasource

import android.util.Log
import com.example.cinegoapp.data.entity.Movie
import com.example.cinegoapp.data.entity.MovieCart
import com.example.cinegoapp.retrofit.MovieDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieDataSource(private val movieDao: MovieDao) {

    // Film listesini alır
    suspend fun fetchMovies(): List<Movie> = withContext(Dispatchers.IO) {
        return@withContext try {
            // DAO üzerinden filmleri alır
            movieDao.fetchMovies().movies
        } catch (e: Exception) {
            // Hata durumunda log kaydı yapar ve boş bir liste döndürür
            Log.e("MovieDataSource", "Film alınırken hata oluştu: ${e.message}", e)
            emptyList() // Hata durumunda boş bir liste döndürür
        }
    }

    suspend fun addMovieToCart(
        name: String,
        image: String,
        price: Int,
        category: String,
        rating: Double,
        year: Int,
        director: String,
        description: String,
        orderAmount: Int,
        userName: String
    ) {
        withContext(Dispatchers.IO) {
            try {
                Log.d("MovieDataSource", """
                Sepete film ekleniyor:
                Adı: $name
                Görsel: $image
                Fiyat: $price
                Kategori: $category
                Puan: $rating
                Yıl: $year
                Yönetmen: $director
                Açıklama: $description
                Sipariş Miktarı: $orderAmount
                Kullanıcı Adı: $userName
            """.trimIndent())

                movieDao.addMovieToCart(
                    name = name,
                    image = image,
                    price = price,
                    category = category,
                    rating = rating,
                    year = year,
                    director = director,
                    description = description,
                    orderAmount = orderAmount,
                    userName = userName
                )

                Log.d("MovieDataSource", "Film başarıyla sepete eklendi: $name")
            } catch (e: Exception) {
                Log.e("MovieDataSource", "Film sepete eklenirken hata oluştu: ${e.message}", e)
            }
        }
    }

    // Kullanıcının sepetindeki filmleri getirir
    suspend fun fetchCartMovies(userName: String): List<MovieCart> = withContext(Dispatchers.IO) {
        return@withContext try {
            Log.d("CartDataSource", "Kullanıcı için sepet filmleri alınıyor: $userName")
            val response = movieDao.fetchCartMovies(userName)
            Log.d("CartDataSource", "Sepetten alınan filmler: ${response.movieCart}")
            response.movieCart
        } catch (e: Exception) {
            // Hata durumunda log kaydı yapar ve boş bir liste döndürür
            Log.e("CartDataSource", "Sepet filmleri alınırken hata oluştu: ${e.message}", e)
            emptyList()
        }
    }

    // Sepetten bir filmi cartId ve kullanıcı adına göre siler
    suspend fun deleteMovieFromCart(cartId: Int, userName: String) {
        withContext(Dispatchers.IO) {
            try {
                // Silme işlemini gerçekleştirir
                movieDao.deleteFromCart(cartId, userName)
                Log.d("MovieDataSource", "Film başarıyla sepetten silindi: cartId=$cartId")
            } catch (e: Exception) {
                Log.e("MovieDataSource", "Film sepetten silinirken hata oluştu: ${e.message}", e)
            }
        }
    }
}
