package com.example.netflix.ui.home

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.netflix.R
import com.example.netflix.databinding.HomeFragmentBinding
import com.example.netflix.model.Movie
import com.example.netflix.ui.viewholder.eventhandler.movieItemEvents

class HomeFragment : Fragment() {

    private val viewModel by viewModels<HomeViewModel>()
    private lateinit var binding: HomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil
            .inflate(layoutInflater, R.layout.home_fragment, container, false)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val movieEventHandler = movieItemEvents {
            onFavorite = { id -> viewModel.toggleFavorite(id) }
            browseWebsite = { movieUrl -> shareViaBrowser(movieUrl) }
            shareMovie = { movie -> shareWithApps(movie) }
            onMovieClicked = { id ->
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToMovieDetailFragment(id)
                )
            }
        }

        val adapter = MovieAdapter(movieEventHandler)
        binding.movieList.adapter = adapter

        viewModel.movies.observe(viewLifecycleOwner) {
            it?.let { movies ->
                adapter.submitList(movies.map(::asMovieItem))
            }
        }

        viewModel.fetchMovies()

        return binding.root
    }

    private fun shareViaBrowser(movieUrl: String) {
        val uri = Uri.parse(movieUrl)
        val intent = Intent().apply {
            action = Intent.ACTION_VIEW
            data = uri
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }

        startActivity(Intent.createChooser(intent, getString(R.string.share_movie)))
    }

    private fun shareWithApps(movie: Movie) {
        val share = Intent.createChooser(Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Intent.EXTRA_TITLE, "Introducing content previews")
            putExtra(Intent.EXTRA_STREAM, Uri.parse(movie.url))
        }, null)
        startActivity(share)
    }

    companion object {
        const val TAG = "HomeFragmentTag"
    }

}