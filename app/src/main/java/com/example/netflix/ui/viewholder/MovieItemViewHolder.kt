package com.example.netflix.ui.viewholder

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import com.example.netflix.databinding.ListMovieItemViewBinding
import com.example.netflix.model.Movie
import com.example.netflix.ui.home.*
import com.example.netflix.ui.util.favoriteItem
import com.example.netflix.ui.viewholder.eventhandler.MovieItemEventHandler

class MovieItemViewHolder private constructor(
    private val binding: ListMovieItemViewBinding,
    private val eventHandler: MovieItemEventHandler
) : BaseViewHolder<ListItem.MovieItem>(binding.root) {

    override fun bind(item: ListItem.MovieItem) {
        val movie = item.movie
        with(binding) {
            this.movie = movie
            movieImage.setImageResource(movie.imageId)
            overview.text = overflowTextOf(movie.overview)
            executePendingBindings()
        }
        bindWithListeners(movie)
    }

    private fun bindWithListeners(movie: Movie) {
        with(binding) {
            btnGoWebsite.setOnClickListener { eventHandler.goWebsite(movie.url) }
            btnShare.setOnClickListener { eventHandler.share(movie) }
            btnFavorite.setOnClickListener {
                eventHandler.toggleFavorite(movie.id)
                (it as ImageView).favoriteItem(!movie.isFavorite)
            }
        }
    }

    private fun overflowTextOf(overview: String) = buildString {
        if (overview.length < 150) append(overview)
        else append(overview.substring(0..150)).append("...")
    }


    companion object {

        fun createFrom(
            parent: ViewGroup,
            eventHandler: MovieItemEventHandler
        ): BaseViewHolder<ListItem> {
            val inflater = LayoutInflater.from(parent.context)
            return MovieItemViewHolder(
                ListMovieItemViewBinding.inflate(inflater, parent, false),
                eventHandler
            ) as BaseViewHolder<ListItem>
        }
    }
}