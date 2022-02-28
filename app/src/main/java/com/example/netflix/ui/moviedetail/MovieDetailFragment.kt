package com.example.netflix.ui.moviedetail

import android.animation.*
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.netflix.R
import com.example.netflix.ui.util.AppContainer
import com.example.netflix.databinding.MovieDetailFragmentBinding
import com.example.netflix.ui.util.dataBindings
import com.example.netflix.ui.util.shareMovieViaBrowser
import com.google.android.material.appbar.AppBarLayout

class MovieDetailFragment : Fragment(R.layout.movie_detail_fragment) {

    private val binding by dataBindings(MovieDetailFragmentBinding::bind)
    private val navArgs by navArgs<MovieDetailFragmentArgs>()
    private val viewModel by viewModels<MovieDetailViewModel> {
        MovieDetailViewModelFactory(AppContainer.provideMovieRepository())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieId = navArgs.movieId
        viewModel.updateUiStates(movieId)
        binding.viewModel = viewModel

        with(binding) {
            appBarLayout.setupWithScrollStateChangeListener()

            btnCheckout.setOnClickListener {
                this@MovieDetailFragment.viewModel
                    .movieUrl?.let { url -> requireActivity().shareMovieViaBrowser(url) }
            }

            toolbar.setNavigationOnClickListener { requireActivity().onBackPressed() }
        }


    }

    private fun AppBarLayout.setupWithScrollStateChangeListener() {
        addOnOffsetChangedListener(
            object : AppBarStateChangeListener() {
                override fun onStateChanged(
                    appBarLayout: AppBarLayout?, state: State?, friction: Float
                ) {
                    when (state) {
                        State.EXPANDED -> {
                            binding.group.visibility = View.VISIBLE
                            animateAlpha(
                                binding.tvToolbarTitle,
                                binding.tvToolbarRuntime,
                                start = binding.tvToolbarTitle.alpha,
                                end = 0f
                            )
                        }
                        State.COLLAPSED -> {
                            binding.group.visibility = View.GONE
                            animateAlpha(
                                binding.tvToolbarTitle,
                                binding.tvToolbarRuntime,
                                start = binding.tvToolbarTitle.alpha,
                                end = 1f
                            )
                        }
                        State.IDLE -> {
                            binding.viewCover.alpha = friction.coerceIn(0.6f, 0.8f)
                        }
                        null -> {
                        }
                    }
                }
            }
        )
    }

    fun animateAlpha(vararg views: View, start: Float, end: Float) {
        val animateSet = AnimatorSet()
        animateSet.playTogether(
            views.map { ObjectAnimator.ofFloat(it, "alpha", start, end) }
        )
        animateSet.start()

    }
}
