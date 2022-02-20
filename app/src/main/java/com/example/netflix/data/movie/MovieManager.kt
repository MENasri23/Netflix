package com.example.netflix.data.movie

import com.example.netflix.model.Movie
import java.util.concurrent.Executors
import java.util.concurrent.Future

class MovieManager {
    private val executor = Executors.newSingleThreadExecutor()

    private var _movies = getMovies()
    val movies: List<Movie> get() = _movies

    fun toggleFavorite(
        movieId: String, callback: (movies: List<Movie>) -> Unit
    ): Future<List<Movie>> {
        return executor.submit {
            _movies.copy(movieId) { it.copy(isFavorite = !it.isFavorite) }
                .also {
                    synchronized(this) { _movies = it }
                }
            callback(movies)
        } as Future<List<Movie>>
    }

    private inline fun List<Movie>.copy(
        id: String, crossinline getNewMovie: (oldMovie: Movie) -> Movie
    ): List<Movie> {
        val newList = mutableListOf<Movie>()
        for (movie in this) {
            newList.add(
                if (movie.id == id) getNewMovie(movie) else movie
            )
        }
        return newList
    }

}