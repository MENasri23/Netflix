package com.example.netflix.ui.moviedetail

import android.animation.*
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.netflix.R
import com.example.netflix.data.Injector
import com.example.netflix.databinding.MovieDetailFragmentBinding
import com.example.netflix.ui.home.MovieHomeViewModelFactory
import com.example.netflix.ui.util.dataBindings
import com.google.android.material.appbar.AppBarLayout

class MovieDetailFragment : Fragment(R.layout.movie_detail_fragment) {

    private val viewModel by viewModels<MovieDetailViewModel> {
        MovieHomeViewModelFactory(Injector.provideMovieManager())
    }
    private val binding by dataBindings(MovieDetailFragmentBinding::bind)
    private val navArgs by navArgs<MovieDetailFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieId = navArgs.movieId


        binding.appBarLayout.setupWithScrollStateChangeListener()
    }


    fun <T : View> T.animateScale(end: Float, start: Float = this.scaleX) {
//       First solution
        val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, start, end)
        val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, start, end)
        ObjectAnimator.ofPropertyValuesHolder(this, scaleX, scaleY).start()

//        second solution
//        binding.tvGenre.animate().scaleX(0.4f).scaleY(0.4f).start()
    }

    fun animateAlpha(vararg views: View, start: Float, end: Float) {
        val animateSet = AnimatorSet()
        animateSet.playTogether(
            views.map { ObjectAnimator.ofFloat(it, "alpha", start, end) }
        )
        animateSet.start()

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
}
