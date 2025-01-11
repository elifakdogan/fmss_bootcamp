package com.example.cinegoapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinegoapp.data.entity.Movie
import com.example.cinegoapp.data.entity.MovieCart
import com.example.cinegoapp.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartScreenViewModel @Inject constructor(private val movieRepository: MovieRepository) : ViewModel() {

    private val _moviesList = MutableLiveData<List<Movie>?>(null) // Tüm filmleri tutan değişken
    val moviesList: LiveData<List<Movie>?> = _moviesList

    private val _isLoading = MutableLiveData(false) // Yükleme durumunu takip eden değişken
    val isLoading: LiveData<Boolean> = _isLoading

    private var allMovies: List<Movie> = emptyList()

    private val _cartMovies = MutableStateFlow<List<MovieCart>>(emptyList()) // Sepetteki filmleri tutan değişken
    val cartMovies: StateFlow<List<MovieCart>> get() = _cartMovies

    // Sepetteki filmleri API'den alır ve günceller
    fun fetchCartMovies() {
        viewModelScope.launch {
            _cartMovies.value = movieRepository.getCartMovies()
        }
    }

    // Tüm filmleri API'den alır ve LiveData'ya atar
    fun fetchMovies() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val fetchedMovies = movieRepository.getMovies()
                allMovies = fetchedMovies // Tüm filmleri sakla
                _moviesList.value = fetchedMovies
                _isLoading.value = false
                Log.d("HomeScreenViewModel", "Movies fetched: $fetchedMovies")
            } catch (e: Exception) {
                Log.e("HomeScreenViewModel", "Error fetching movies: ${e.message}")
            }
        }
    }

    // Filmi sepete ekler
    fun addMovieToCart(movie: Movie, orderAmount: Int, userName: String) {
        viewModelScope.launch {
            movieRepository.addToCart(movie, orderAmount, userName)
            fetchCartMovies() // Sepeti güncellemek için tekrar çağırılır
        }
    }

    // Sepetten filmi siler
    fun removeMovieFromCart(cartId: Int, userName: String) {
        viewModelScope.launch {
            movieRepository.removeFromCart(cartId, userName)
            _cartMovies.value = _cartMovies.value.filter { it.cartId != cartId } // Listeyi filtreleyerek güncelle
        }
    }


}
