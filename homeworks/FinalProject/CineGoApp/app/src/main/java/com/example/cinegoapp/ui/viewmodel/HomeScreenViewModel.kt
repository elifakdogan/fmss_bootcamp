package com.example.cinegoapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinegoapp.data.entity.Movie
import com.example.cinegoapp.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(private val movieRepository: MovieRepository) : ViewModel() {

    // Film listesini tutan değişken
    private val _moviesList = MutableLiveData<List<Movie>?>(null)
    val moviesList: LiveData<List<Movie>?> = _moviesList

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private var allMovies: List<Movie> = emptyList()

    // API'den filmleri getirir ve LiveData'ya aktarır
    fun fetchMovies() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val fetchedMovies = movieRepository.getMovies()
                allMovies = fetchedMovies
                _moviesList.value = fetchedMovies
                _isLoading.value = false
                Log.d("HomeScreenViewModel", "Movies fetched: $fetchedMovies")
            } catch (e: Exception) {
                Log.e("HomeScreenViewModel", "Error fetching movies: ${e.message}")
            }
        }
    }

    // Filmleri arama sorgusuna göre filtreler
    fun searchMovies(searchQuery: String) {
        viewModelScope.launch {
            try {
                if (searchQuery.isBlank()) {
                    _moviesList.value = allMovies
                } else {
                    val filteredMovies = allMovies.filter {
                        it.name.contains(searchQuery, ignoreCase = true) ||
                                it.category.contains(searchQuery, ignoreCase = true)
                    }
                    _moviesList.value = filteredMovies
                }
            } catch (e: Exception) {
                Log.e("HomeScreenViewModel", "Error searching movies: ${e.message}")
            }
        }
    }

    // Filmleri A-Z sıralar
    fun sortMoviesAZ() {
        _moviesList.value = moviesList.value?.sortedBy { it.name }
    }

    // Filmleri Z-A sıralar
    fun sortMoviesZA() {
        _moviesList.value = moviesList.value?.sortedByDescending { it.name }
    }

    // Filmleri IMDB puanına göre azalan sırada sıralar
    fun sortMoviesByRatingDescending() {
        _moviesList.value = moviesList.value?.sortedByDescending { it.rating }
    }

    // Filmleri IMDB puanına göre artan sırada sıralar
    fun sortMoviesByRatingAscending() {
        _moviesList.value = moviesList.value?.sortedBy { it.rating }
    }
}