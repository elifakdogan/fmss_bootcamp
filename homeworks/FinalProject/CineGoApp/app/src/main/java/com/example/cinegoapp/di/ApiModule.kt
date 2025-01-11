package com.example.cinegoapp.di

import com.example.cinegoapp.data.datasource.MovieDataSource
import com.example.cinegoapp.data.repository.MovieRepository
import com.example.cinegoapp.retrofit.ApiUtils
import com.example.cinegoapp.retrofit.MovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    @Singleton
    fun provideMovieDao(): MovieDao = ApiUtils.getMovieDao()

    @Provides
    @Singleton
    fun provideMovieDataSource(movieDao: MovieDao): MovieDataSource {
        return MovieDataSource(movieDao)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(movieDataSource: MovieDataSource): MovieRepository {
        return MovieRepository(movieDataSource)
    }
}