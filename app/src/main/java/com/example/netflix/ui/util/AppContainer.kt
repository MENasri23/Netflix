package com.example.netflix.ui.util

import com.example.netflix.data.movie.MovieManager

object AppContainer {
    private var movieManager: MovieManager? = null

    fun provideMovieManager() = movieManager ?: synchronized(this) {
        return movieManager ?: MovieManager().also { movieManager = it }
    }
}