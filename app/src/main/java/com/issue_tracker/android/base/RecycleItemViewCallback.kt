package com.issue_tracker.android.base

import android.view.View

interface RecycleItemViewCallback<T> {
    fun onItemViewClicked(item: T?, position: Int)
    fun onItemViewClicked(item: T?, position: Int, view: View? = null) {
        onItemViewClicked(item, position)
    }
}