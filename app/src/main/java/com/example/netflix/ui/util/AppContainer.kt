package com.example.netflix.ui.util

import com.example.netflix.data.movie.MovieManager
import com.example.netflix.ui.authentication.UserManager

object AppContainer {
    private var movieManager: MovieManager? = null
    private var UserManager: UserManager? = null

    fun provideMovieManager() = movieManager ?: synchronized(this) {
        return movieManager ?: MovieManager().also { movieManager = it }
    }

    fun provideUserManager() = UserManager ?: synchronized(this) {
        return UserManager ?: UserManager().also { UserManager = it }
    }

}