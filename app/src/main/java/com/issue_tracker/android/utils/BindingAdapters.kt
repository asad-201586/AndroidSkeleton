package com.issue_tracker.android.utils

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.issue_tracker.android.R
import com.issue_tracker.android.utils.extension.isNotNull
import com.issue_tracker.android.utils.extension.isNull
import timber.log.Timber

class BindingAdapters {
    companion object {
        @JvmStatic
        @BindingAdapter(value = ["imageUrl","placeholder"], requireAll = false)
        fun loadImage(view: ImageView, url: String?,placeholder: Drawable?) {
            if (!url.isNullOrEmpty()) {
                if (placeholder.isNotNull()) {
                    Timber.i("binding_ ----------- yes")
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
                    Timber.i("binding_ ----------- no")
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
                if (placeholder.isNull()) {
                    Glide.with(view.context)
                        .load(R.drawable.placeholder)
                        .skipMemoryCache(false)
                        .apply(
                            RequestOptions()
                                .placeholder(placeholder)
                                .dontAnimate()
                        )
                        .into(view)
                } else {
                    Glide.with(view.context)
                        .load(placeholder)
                        .skipMemoryCache(false)
                        .apply(
                            RequestOptions()
                                .placeholder(R.drawable.placeholder)
                                .dontAnimate()
                        )
                        .into(view)
                }

            }
        }
    }




}

