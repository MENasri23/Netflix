package com.example.netflix.ui.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.netflix.R

@BindingAdapter("favorite")
fun ImageView.favoriteItem(favorite: Boolean) {
    setImageResource(
        if (favorite) R.drawable.ic_favorite_back else R.drawable.ic_favorite_border_black
    )
}