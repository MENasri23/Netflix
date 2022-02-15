package com.example.netflix.ui.moviedetail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.netflix.R

class MovieDetailFragment : Fragment() {

    private lateinit var viewModel: MovieDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(MovieDetailViewModel::class.java)

        return inflater.inflate(R.layout.movie_detail_fragment, container, false)
    }

}