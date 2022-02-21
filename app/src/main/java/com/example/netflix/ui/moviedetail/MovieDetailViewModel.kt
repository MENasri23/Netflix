package com.example.netflix.ui.moviedetail

import androidx.lifecycle.*
import com.example.netflix.data.movie.MovieManager
import com.example.netflix.model.Movie

class MovieDetailViewModel(
    private val movieManager: MovieManager
) : ViewModel() {

    private val _movie = MutableLiveData<Movie?>()
    val movie: LiveData<Movie?> get() = _movie

    val movieUrl: String? get() = movie.value?.url

    fun updateUiStates(movieId: String) {
        movieManager.findMovieById(movieId) {
            _movie.postValue(it)
        }
    }

    fun toggleFavorite() {
        val movie = movie.value ?: return
        _movie.value = movie.copy(isFavorite = !movie.isFavorite)
        movieManager.toggleFavorite(movie.id)
    }

    fun backPressed() {
        _movie.value = null
    }

}