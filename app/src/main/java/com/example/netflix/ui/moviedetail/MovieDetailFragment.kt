package com.example.netflix.ui.moviedetail

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.support.v4.media.session.PlaybackStateCompat
import android.util.Property
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.netflix.R
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MovieDetailFragment : Fragment() {

    private lateinit var viewModel: MovieDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(MovieDetailViewModel::class.java)

        return inflater.inflate(R.layout.movie_detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val appBar = view.findViewById<AppBarLayout>(R.id.appBarLayout)
        val btnFavorite = view.findViewById<FloatingActionButton>(R.id.btnFavorite)

        /*appBar.addOnOffsetChangedListener(
            AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
                val range = appBarLayout.totalScrollRange.toFloat()
                val progress = 1f - (-verticalOffset / range)


                if (progress < 0.75 && btnFavorite.scaleX > 0f) {
                    val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 0f)
                    val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 0f)
                    val animator =
                        ObjectAnimator.ofPropertyValuesHolder(btnFavorite, scaleX, scaleY).apply {
                            repeatCount = 1
                            repeatMode = ObjectAnimator.REVERSE
                        }
                    animator.start()
                }
            })*/

        appBar.addOnOffsetChangedListener(
            object : AppBarStateChangeListener() {
                override fun onStateChanged(appBarLayout: AppBarLayout?, state: State?) {
                    when (state) {
                        State.EXPANDED -> btnFavorite.animateScale(1f)
                        State.COLLAPSED -> btnFavorite.animateScale(0f)
                        State.IDLE -> {}
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
}