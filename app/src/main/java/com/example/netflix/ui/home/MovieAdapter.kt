package com.example.netflix.ui.home

import android.view.ViewGroup
import androidx.recyclerview.widget.*
import com.example.netflix.ui.viewholder.BaseViewHolder
import com.example.netflix.ui.viewholder.MovieItemViewHolder
import com.example.netflix.ui.viewholder.eventhandler.MovieItemEventHandler


class MovieAdapter(
    private val movieItemEventHandler: MovieItemEventHandler
) : ListAdapter<ListItem, BaseViewHolder<ListItem>>(DataItemDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<ListItem> {
        return MovieItemViewHolder.createFrom(parent, movieItemEventHandler)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<ListItem>, position: Int) {
        holder.bind(getItem(position))
    }

    class DataItemDiffCallback : DiffUtil.ItemCallback<ListItem>() {
        override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
            return oldItem == newItem
        }
    }
}