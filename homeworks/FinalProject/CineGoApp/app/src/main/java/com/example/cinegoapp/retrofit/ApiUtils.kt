package com.example.cinegoapp.retrofit

class ApiUtils {
    companion object {
        const val BASE_URL = "http://kasimadalan.pe.hu/"
        const val BASE_USERNAME = "elif_akdogan"

        fun getMovieDao(): MovieDao {
            return RetrofitClient.getClient(BASE_URL).create(MovieDao::class.java)
        }
    }
}