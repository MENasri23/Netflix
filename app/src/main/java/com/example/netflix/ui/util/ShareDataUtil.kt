package com.example.netflix.ui.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.example.netflix.R
import com.example.netflix.data.model.Movie

fun Context.shareMovieViaBrowser(movieUrl: String) {
    val uri = Uri.parse(movieUrl)
    val intent = Intent().apply {
        action = Intent.ACTION_VIEW
        data = uri
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }

    startActivity(Intent.createChooser(intent, getString(R.string.share_movie)))
}

fun Context.shareMovieWithApps(movie: Movie) {
    val share = Intent.createChooser(Intent().apply {
        action = Intent.ACTION_SEND
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, movie.overview)
    }, getString(R.string.share_movie))

    startActivity(share)
}