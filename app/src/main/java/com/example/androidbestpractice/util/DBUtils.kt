package com.example.androidbestpractice.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.androidbestpractice.R

object DBUtils {
    @BindingAdapter("imageSimpleLoad")
    @JvmStatic
    fun loadImage(imageView: ImageView, imageURL: String?) {
        Glide.with(imageView.context)
            .load("https://image.tmdb.org/t/p/original$imageURL")
            .placeholder(R.drawable.ic_place_holder)
            .error(R.drawable.ic_baseline_close_24)
            .into(imageView)
    }
}