package com.example.netflix.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.netflix.R
import com.example.netflix.data.Injector
import com.example.netflix.databinding.HomeFragmentBinding
import com.example.netflix.ui.util.*
import com.example.netflix.ui.viewholder.eventhandler.movieItemEvents

class HomeFragment : Fragment(R.layout.home_fragment) {

    private val viewModel by viewModels<HomeViewModel> {
        MovieHomeViewModelFactory(Injector.provideMovieManager())
    }
    private val binding by dataBindings(HomeFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val movieEventHandler = movieItemEvents {
            onFavorite = { id -> viewModel.toggleFavorite(id) }
            browseWebsite = { movieUrl -> requireActivity().shareMovieViaBrowser (movieUrl) }
            shareMovie = { movie -> requireActivity().shareMovieWithApps(movie) }
            onMovieClicked = { id ->
                findNavController().navigate(
                    HomeFragmentDirections.toMovieDetailFragment(id)
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
    }


    companion object {
        const val TAG = "HomeFragmentTag"
    }

}