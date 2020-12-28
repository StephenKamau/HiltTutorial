package com.main.hilttutorial.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.main.hilttutorial.databinding.BlogCardBinding
import com.main.hilttutorial.model.Blog

class BlogViewHolder constructor(private val binding: BlogCardBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(
        clickListeners: BlogAdapter.BlogClickListeners,
        blog: Blog
    ) {
        binding.clickListener = clickListeners
        binding.blog = blog
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): BlogViewHolder {
            return BlogViewHolder(
                BlogCardBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }
}