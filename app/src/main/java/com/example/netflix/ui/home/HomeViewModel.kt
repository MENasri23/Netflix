package com.example.netflix.ui.home

import androidx.lifecycle.*
import com.example.netflix.data.movie.MovieManager
import com.example.netflix.model.Movie

class HomeViewModel(
    private val movieManager: MovieManager
) : ViewModel() {

    private val _movies = MutableLiveData<List<Movie>?>(null)
    val movies: LiveData<List<Movie>?> get() = _movies

    fun fetchMovies() {
        _movies.value = movieManager.movies
    }

    fun toggleFavorite(movieId: String) {
        movieManager.toggleFavorite(movieId) {
            _movies.postValue(it)
        }
    }
}