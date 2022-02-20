package com.example.netflix.ui.viewholder

import android.view.*
import com.example.netflix.databinding.ListMovieItemViewBinding
import com.example.netflix.model.Movie
import com.example.netflix.ui.home.*
import com.example.netflix.ui.viewholder.eventhandler.MovieItemEventHandler

class MovieItemViewHolder private constructor(
    private val binding: ListMovieItemViewBinding,
    private val eventHandler: MovieItemEventHandler
) : BaseViewHolder<ListItem.MovieItem>(binding.root) {

    private val listener = ClickListener()

    init {
        with(binding) {
            btnFavorite.setOnClickListener(listener)
            btnGoWebsite.setOnClickListener(listener)
            btnShare.setOnClickListener(listener)
            cardViewMovie.setOnClickListener(listener)
        }
    }

    override fun bind(item: ListItem.MovieItem) {
        val movie = item.movie
        listener.currentMovie = movie
        with(binding) {
            this.movie = movie
            movieImage.setImageResource(movie.imageId)
            overview.text = overflowTextOf(movie.overview)
            executePendingBindings()
        }
    }

    private fun overflowTextOf(overview: String) = buildString {
        if (overview.length < 150) append(overview)
        else append(overview.substring(0..150)).append("...")
    }


    private inner class ClickListener : View.OnClickListener {
        var currentMovie: Movie? = null

        override fun onClick(v: View?) {
            val movie = currentMovie ?: return

            with(binding) {
                when (v) {
                    btnFavorite -> eventHandler.toggleFavorite(movie.id)
                    btnGoWebsite -> eventHandler.goWebsite(movie.url)
                    btnShare -> eventHandler.share(movie)
                    cardViewMovie -> eventHandler.onCLick(movie.id)
                }
            }
        }
    }

    companion object {

        @Suppress("UNCHECKED_CAST")
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