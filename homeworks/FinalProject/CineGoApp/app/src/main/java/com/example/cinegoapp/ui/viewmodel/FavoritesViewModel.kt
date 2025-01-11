package com.example.cinegoapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cinegoapp.data.entity.Movie

class FavoritesViewModel : ViewModel() {

    // Favori filmleri tutan değişken
    private val _favoriteMoviesList = MutableLiveData<MutableList<Movie>>(mutableListOf())

    val favoriteMoviesList: MutableLiveData<MutableList<Movie>> get() = _favoriteMoviesList

    // Filmi favorilere ekler
    fun addFavorite(movie: Movie) {
        val currentList = _favoriteMoviesList.value ?: mutableListOf()
        if (!currentList.contains(movie)) {
            currentList.add(movie)
            _favoriteMoviesList.value = currentList
        }
    }

    // Filmi favorilerden siler
    fun removeFavorite(movie: Movie) {
        val currentList = _favoriteMoviesList.value ?: mutableListOf()
        if (currentList.contains(movie)) {
            currentList.remove(movie)
            _favoriteMoviesList.value = currentList
        }
    }

    // Bir filmin favorilerde olup olmadığını kontrol eder
    fun isFavorite(movie: Movie): Boolean {
        return _favoriteMoviesList.value?.contains(movie) == true
    }
}
