package com.example.netflix.ui.home

import android.view.*
import androidx.recyclerview.widget.RecyclerView
import com.example.netflix.R
import com.example.netflix.databinding.ListMovieItemViewBinding

abstract class BaseViewHolder<T>(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(item: T)
}
