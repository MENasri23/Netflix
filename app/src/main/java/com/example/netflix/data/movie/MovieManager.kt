package com.example.netflix.data.movie

import com.example.netflix.model.Movie
import java.util.concurrent.Executors
import java.util.concurrent.Future

class MovieManager {
    private val executor = Executors.newSingleThreadExecutor()

    private var _movies = getMovies()
    val movies: List<Movie> get() = _movies

    @Suppress("UNCHECKED_CAST")
    fun toggleFavorite(
        movieId: String, callback: (movies: List<Movie>) -> Unit = { }
    ): Future<List<Movie>> {
        return executor.submit {
            _movies.copy(movieId) { it.copy(isFavorite = !it.isFavorite) }
                .also {
                    callback(it)
                    synchronized(this) { _movies = it }
                }
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

    fun findMovieById(movieId: String, callback: (movie: Movie) -> Unit) {
        executor.submit {
           val movie = movies.firstOrNull { it.id == movieId }
            movie?.let(callback)
        }
    }

}