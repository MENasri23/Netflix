package com.example.netflix.ui.home

import com.example.netflix.model.Movie

sealed class ListItem {
    class MovieItem(val movie: Movie) : ListItem() {
        override val id: String get() = movie.id
    }

    object HeaderItem : ListItem() {
        override val id: String? get() = null
    }

    abstract val id: String?
}

fun asMovieItem(movie: Movie) = ListItem.MovieItem(movie)
