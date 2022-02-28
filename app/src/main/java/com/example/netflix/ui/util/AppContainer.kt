package com.example.netflix.ui.util

import com.example.netflix.data.repository.MovieRepository
import com.example.netflix.ui.authentication.UserManager

object AppContainer {
    private var movieRepository: MovieRepository? = null
    private var UserManager: UserManager? = null

    fun provideMovieRepository() = movieRepository ?: synchronized(this) {
        return movieRepository ?: MovieRepository().also { movieRepository = it }
    }

    fun provideUserManager() = UserManager ?: synchronized(this) {
        return UserManager ?: UserManager().also { UserManager = it }
    }

}