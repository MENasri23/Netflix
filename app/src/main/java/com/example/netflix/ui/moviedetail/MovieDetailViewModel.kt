package com.example.netflix.ui.moviedetail

import androidx.lifecycle.*
import com.example.netflix.data.movie.MovieManager
import com.example.netflix.model.Movie

class MovieDetailViewModel(
    private val movieManager: MovieManager
) : ViewModel() {

    private val _movie = MutableLiveData<Movie?>()
    val movie: LiveData<Movie?> get() = _movie

    fun updateUiStates(movieId: String) {
        movieManager.findMovieById(movieId) {
            _movie.postValue(it)
        }
    }


}