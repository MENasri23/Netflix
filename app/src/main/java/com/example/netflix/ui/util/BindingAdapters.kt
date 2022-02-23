package com.example.netflix.ui.util

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.example.netflix.R

@BindingAdapter("favorite")
fun ImageView.favoriteItem(favorite: Boolean) {
    setImageResource(
        if (favorite) R.drawable.ic_favorite_back else R.drawable.ic_favorite_border_black
    )
}

@BindingAdapter("imageResId")
fun ImageView.setImage(@DrawableRes resId: Int) {
    setImageResource(resId)
}

@BindingAdapter("imageLocalUrl")
fun ImageView.setImageFromUri(url: String?) {
    if (url == null) {
        setImageResource(R.drawable.ic_person_24)
    } else {
        setImageURI(url.toUri())
    }
}