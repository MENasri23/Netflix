package com.example.netflix.ui.viewholder.eventhandler

import com.example.netflix.model.Movie

data class MovieItemEventHandler(
    val toggleFavorite: (String) -> Unit,
    val goWebsite: (String) -> Unit,
    val share: (Movie) -> Unit,
    val onCLick: (String) -> Unit
)

class MovieItemEventHandlerBuilder {
    var onFavorite: ((movieId: String) -> Unit)? = null
    var browseWebsite: ((url: String) -> Unit)? = null
    var shareMovie: ((movie: Movie) -> Unit)? = null
    var onMovieClicked: ((onCLick: String) -> Unit)? = null


    fun build(): MovieItemEventHandler {
        return MovieItemEventHandler(
            onFavorite ?: {},
            browseWebsite ?: {},
            shareMovie ?: {},
            onMovieClicked ?: {}
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
