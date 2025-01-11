package com.example.cinegoapp.data.entity

import com.google.gson.annotations.SerializedName

data class CartResponse(
    @SerializedName("movie_cart") val movieCart: List<MovieCart>,
    val success: Int
)

