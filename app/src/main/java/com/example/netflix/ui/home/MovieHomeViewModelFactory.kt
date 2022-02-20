package com.example.netflix.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.netflix.data.movie.MovieManager
import com.example.netflix.ui.moviedetail.MovieDetailViewModel
import java.lang.IllegalArgumentException

class MovieHomeViewModelFactory(
    private val movieManager: MovieManager
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(movieManager) as T
        }else if (modelClass.isAssignableFrom(MovieDetailViewModel::class.java)) {
            return MovieDetailViewModel(movieManager) as T
        }
        throw IllegalArgumentException("viewModel: ${modelClass.simpleName}")
    }
}