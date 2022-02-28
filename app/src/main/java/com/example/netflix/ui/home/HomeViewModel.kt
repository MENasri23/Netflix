package com.example.netflix.ui.home

import androidx.lifecycle.*
import com.example.netflix.data.repository.MovieRepository
import com.example.netflix.data.model.Movie

class HomeViewModel(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _movies = MutableLiveData<List<Movie>?>(null)
    val movies: LiveData<List<Movie>?> get() = _movies

    fun fetchMovies() {
        _movies.value = movieRepository.movies
    }

    fun toggleFavorite(movieId: String) {
        movieRepository.toggleFavorite(movieId) {
            _movies.postValue(it)
        }
    }
}