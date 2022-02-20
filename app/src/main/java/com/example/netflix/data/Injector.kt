package com.example.netflix.data

import com.example.netflix.data.movie.MovieManager

object Injector {
    private var movieManager: MovieManager? = null

    fun provideMovieManager() = movieManager ?: synchronized(this) {
        return movieManager ?: MovieManager().also { movieManager = it }
    }
}