package com.example.cinegoapp.data.entity

data class Movie(
    val id: Int,
    val name: String,
    val image: String,
    val price: Int,
    val category: String,
    val rating: Double,
    val year: Int,
    val director: String,
    val description: String
)