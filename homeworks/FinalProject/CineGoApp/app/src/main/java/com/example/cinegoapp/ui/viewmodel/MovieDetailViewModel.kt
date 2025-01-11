package com.example.cinegoapp.ui.viewmodel

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
class MovieDetailViewModel @Inject constructor(private val movieRepository: MovieRepository) : ViewModel() {

    private val _selectedMovie = MutableLiveData<Movie?>()
    val selectedMovie: LiveData<Movie?> = _selectedMovie

    fun addMovieToCart(movie: Movie, orderAmount: Int, userName: String) {
        viewModelScope.launch {
            movieRepository.addToCart(movie, orderAmount, userName)
        }
    }
}