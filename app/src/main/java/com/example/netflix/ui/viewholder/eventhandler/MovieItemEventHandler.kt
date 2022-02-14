package com.example.netflix.ui.viewholder.eventhandler

import com.example.netflix.model.Movie

data class MovieItemEventHandler(
    val toggleFavorite: (String) -> Unit,
    val goWebsite: (String) -> Unit,
    val share: (Movie) -> Unit
)

class MovieItemEventHandlerBuilder {
    var onFavorite: (movieId: String) -> Unit = {}
    var browesWebsite: (url: String) -> Unit = {}
    var shareMovie: (movie: Movie) -> Unit = {}


    fun build(): MovieItemEventHandler {
        return MovieItemEventHandler(
            onFavorite,
            browesWebsite,
            shareMovie
        )
    }
}

fun movieItemEvents(
    movieEventHandlerBuilder: MovieItemEventHandlerBuilder.() -> Unit
): MovieItemEventHandler {
    return MovieItemEventHandlerBuilder()
        .apply(movieEventHandlerBuilder)
        .build()
}
