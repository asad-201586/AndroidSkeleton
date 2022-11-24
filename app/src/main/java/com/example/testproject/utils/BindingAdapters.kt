package com.example.testproject.utils

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.testproject.R
import com.example.testproject.utils.extension.isNotNull

class BindingAdapters {
    companion object {
        @JvmStatic
        @BindingAdapter(value = ["imageUrl","placeholder"], requireAll = false)
        fun loadImage(view: ImageView, url: String?,placeholder: Drawable?) {
            if (!url.isNullOrEmpty()) {
                if (placeholder.isNotNull()) {
                    Glide.with(view.context)
                        .load(url)
                        .skipMemoryCache(false)
                        .apply(
                            RequestOptions()
                                .placeholder(placeholder)
                                .dontAnimate()
                        )
                        .into(view)
                } else {
                    Glide.with(view.context)
                        .load(url)
                        .skipMemoryCache(false)
                        .apply(
                            RequestOptions()
                                .placeholder(R.drawable.placeholder)
                                .dontAnimate()
                        )
                        .into(view)
                }
            }
            else {
                Glide.with(view.context)
                    .load(R.drawable.placeholder)
                    .skipMemoryCache(false)
                    .apply(
                        RequestOptions()
                            .placeholder(placeholder)
                            .dontAnimate()
                    )
                    .into(view)
            }
        }
    }

}