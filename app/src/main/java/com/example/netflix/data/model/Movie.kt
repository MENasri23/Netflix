package com.example.netflix.data.model

import androidx.annotation.DrawableRes

data class Movie(
    val id: String,
    val title: String,
    val url: String,
    val genre: Genre,
    val metadata: Metadata,
    val overview: String,
    val isFavorite: Boolean = false,
    @DrawableRes val imageId: Int,
)

data class Metadata(
    val director: Director,
    val releaseDate: String,
    val runtime: String,
    val language: String
)

data class Director(
    val name: String,
    val url: String? = null
)

enum class Genre {
    Action,
    Comedy,
    Drama,
    Fantasy,
    Horror,
    Mystery,
    Romance,
    Thriller,
    Western
}