package com.example.netflix.ui.moviedetail

import android.animation.*
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.animation.addListener
import com.example.netflix.databinding.MovieDetailFragmentBinding
import com.google.android.material.appbar.AppBarLayout

class MovieDetailFragment : Fragment() {

    private lateinit var viewModel: MovieDetailViewModel
    private lateinit var binding: MovieDetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(MovieDetailViewModel::class.java)

        binding = MovieDetailFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.appBarLayout.addOnOffsetChangedListener(
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
                    }
                }
            }
        )

    }

    fun <T : View> T.animateScale(end: Float, start: Float = this.scaleX) {
        val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, start, end)
        val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, start, end)
        ObjectAnimator.ofPropertyValuesHolder(this, scaleX, scaleY).start()
    }

    fun animateAlpha(vararg views: View, start: Float, end: Float) {
        val animateSet = AnimatorSet()
        animateSet.playTogether(
            views.map { ObjectAnimator.ofFloat(it, "alpha", start, end) }
        )
        animateSet.start()

    }


    fun fadeInContentHeadline() {
        with(binding) {
            AnimatorSet().apply {
                playTogether(
                    ObjectAnimator.ofFloat(tvTitle, "alpha", 1f),
                    ObjectAnimator.ofFloat(tvRuntime, "alpha", 1f),
                    ObjectAnimator.ofFloat(tvGenre, "alpha", 1f),
                    ObjectAnimator.ofFloat(tvDate, "alpha", 1f),
                )
                removeAllListeners()
                start()
            }
        }
    }

    fun fadeOutContentHeadline() {
        with(binding) {
            AnimatorSet().apply {
                playTogether(
                    ObjectAnimator.ofFloat(tvTitle, "alpha", 0f),
                    ObjectAnimator.ofFloat(tvRuntime, "alpha", 0f),
                    ObjectAnimator.ofFloat(tvGenre, "alpha", 0f),
                    ObjectAnimator.ofFloat(tvDate, "alpha", 0f),
                )
                addListener(onEnd = {
                    tvTitle.visibility = View.GONE
                    tvRuntime.visibility = View.GONE
                    tvGenre.visibility = View.GONE
                    tvDate.visibility = View.GONE
                })
                start()
            }
        }
    }


}