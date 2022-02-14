package com.example.netflix.ui.home

import androidx.lifecycle.*
import com.example.netflix.data.movie.getMovies
import com.example.netflix.model.Movie
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _movies = MutableLiveData<List<Movie>?>(null)
    val movies: LiveData<List<Movie>?> get() = _movies

    fun fetchMovies() {
        _movies.value = getMovies().shuffled()
    }

    fun toggleFavorite(movieId: String) {
        val oldList = movies.value ?: return

        val newList = mutableListOf<Movie>()
        for (movie in oldList) {
            if (movie.id == movieId) {
                newList.add(movie.copy(isFavorite = !movie.isFavorite))
            } else {
                newList.add(movie)
            }
        }
        _movies.value = newList

    }
}