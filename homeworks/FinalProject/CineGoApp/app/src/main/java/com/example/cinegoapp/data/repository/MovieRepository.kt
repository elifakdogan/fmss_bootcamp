package com.example.cinegoapp.data.repository

import com.example.cinegoapp.data.datasource.MovieDataSource
import com.example.cinegoapp.data.entity.Movie
import com.example.cinegoapp.data.entity.MovieCart
import com.example.cinegoapp.retrofit.ApiUtils.Companion.BASE_USERNAME

class MovieRepository(private val movieDataSource: MovieDataSource) {

    // Filmlerin listesini alır
    suspend fun getMovies(): List<Movie> = movieDataSource.fetchMovies()

    //Filmi sepete ekler
    suspend fun addToCart(movie: Movie, orderAmount: Int, userName: String) {
        movieDataSource.addMovieToCart(
            name = movie.name,
            image = movie.image,
            price = movie.price,
            category = movie.category,
            rating = movie.rating,
            year = movie.year,
            director = movie.director,
            description = movie.description,
            orderAmount = orderAmount,
            userName = userName
        )
    }

    // Kullanıcının sepetindeki filmleri alır
    suspend fun getCartMovies(): List<MovieCart> = movieDataSource.fetchCartMovies(
        userName = BASE_USERNAME // Varsayılan kullanıcı adı
    )

    // Sepetten bir filmi siler
    suspend fun removeFromCart(cartId: Int, userName: String) = movieDataSource.deleteMovieFromCart(cartId, userName)

}
