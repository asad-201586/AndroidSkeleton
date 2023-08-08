package com.issue_tracker.android.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.issue_tracker.android.databinding.ItemRepoBinding
import com.issue_tracker.android.network.model.ReposResponse
import com.issue_tracker.android.ui.home.HomeActivity
import com.issue_tracker.android.utils.extension.onClick
import com.issue_tracker.android.utils.extension.openActivity
import timber.log.Timber

class ReposAdapterPaged: PagingDataAdapter<ReposResponse.ReposResponseItem, ReposAdapterPaged.ListViewHolder>(DIFF_CALLBACK) {

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ReposResponse.ReposResponseItem>() {
            override fun areItemsTheSame(
                oldItem: ReposResponse.ReposResponseItem,
                newItem: ReposResponse.ReposResponseItem
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: ReposResponse.ReposResponseItem,
                newItem: ReposResponse.ReposResponseItem
            ): Boolean {
                return oldItem == newItem
            }
        }

    }


    class ListViewHolder private constructor(
        private val binding: ItemRepoBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ReposResponse.ReposResponseItem?) {
            binding.data = item

            binding.root onClick {
                it.context.openActivity<HomeActivity> {
                    putExtra("repo_name",item?.name?:"")
                }
                Timber.i("clicked_db -------------- ${item?.name}")
            }
        }

        companion object {
            fun from(
                parent: ViewGroup,
            ): ListViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemRepoBinding.inflate(layoutInflater, parent, false)
                return ListViewHolder(binding)
            }
        }

    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder.from(parent)
    }

}