package com.example.netflix.ui.moviedetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.netflix.data.movie.MovieManager
import java.lang.IllegalArgumentException

class MovieDetailViewModelFactory(
    private val movieManager: MovieManager
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(MovieDetailViewModel::class.java)) {
            return MovieDetailViewModel(movieManager) as T
        }
        throw IllegalArgumentException("Unknown viewModel: ${modelClass.simpleName}")
    }
}