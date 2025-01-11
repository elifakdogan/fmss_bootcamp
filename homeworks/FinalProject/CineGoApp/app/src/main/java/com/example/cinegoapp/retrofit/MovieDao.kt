package com.example.cinegoapp.retrofit

import com.example.cinegoapp.data.entity.CartResponse
import com.example.cinegoapp.data.entity.MovieResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface MovieDao {
    @GET("movies/getAllMovies.php")
    suspend fun fetchMovies(): MovieResponse

    @POST("movies/insertMovie.php")
    @FormUrlEncoded
    suspend fun addMovieToCart(
        @Field("name") name: String,
        @Field("image") image: String,
        @Field("price") price: Int,
        @Field("category") category: String,
        @Field("rating") rating: Double,
        @Field("year") year: Int,
        @Field("director") director: String,
        @Field("description") description: String,
        @Field("orderAmount") orderAmount: Int,
        @Field("userName") userName: String
    )

    @POST("movies/getMovieCart.php")
    @FormUrlEncoded
    suspend fun fetchCartMovies(
        @Field("userName") userName: String
    ) : CartResponse

    @POST("movies/deleteMovie.php")
    @FormUrlEncoded
    suspend fun deleteFromCart(
        @Field("cartId") cartId: Int,
        @Field("userName") userName: String
    )
}