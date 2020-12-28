package com.main.hilttutorial.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.main.hilttutorial.model.Blog
import javax.inject.Inject

class BlogAdapter @Inject constructor(private val clickListener: BlogClickListeners) :
    ListAdapter<Blog, BlogViewHolder>(BlogDiffUtil()) {

    interface BlogClickListeners {
        fun onAddToFavouritesClicked(blog: Blog)
        fun onViewDetailsClicked(blog: Blog)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogViewHolder {
        return BlogViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: BlogViewHolder, position: Int) {
        holder.bind(clickListener, getItem(position)!!)
    }

}

class BlogDiffUtil : DiffUtil.ItemCallback<Blog>() {
    override fun areItemsTheSame(oldItem: Blog, newItem: Blog): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Blog, newItem: Blog): Boolean {
        return oldItem == newItem
    }

}