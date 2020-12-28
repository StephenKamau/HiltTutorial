package com.main.hilttutorial.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.main.hilttutorial.model.Blog

@BindingAdapter("glideSrc")
fun ImageView.bindGlideSrc(
    blog: Blog
) {
    Glide.with(context).load(blog.image)
        .centerCrop().into(this)
}