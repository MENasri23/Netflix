package com.example.netflix.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.netflix.R
import com.example.netflix.databinding.HomeFragmentBinding

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

        return binding.root
    }

}