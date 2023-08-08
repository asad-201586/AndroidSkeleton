package com.issue_tracker.android.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.issue_tracker.android.databinding.ItemIssueBinding
import com.issue_tracker.android.network.model.IssueListResponse

class IssueListAdapter: ListAdapter<IssueListResponse.IssueListResponseItem,IssueListAdapter.IssueListViewHolder>(DIFF_CALLBACK) {

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<IssueListResponse.IssueListResponseItem>() {
            override fun areItemsTheSame(oldItem: IssueListResponse.IssueListResponseItem, newItem: IssueListResponse.IssueListResponseItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: IssueListResponse.IssueListResponseItem, newItem: IssueListResponse.IssueListResponseItem): Boolean {
                return oldItem == newItem
            }
        }

    }

    class IssueListViewHolder private constructor(
        private val binding: ItemIssueBinding,
    ): RecyclerView.ViewHolder(binding.root){
        fun bind(item: IssueListResponse.IssueListResponseItem) {
            binding.data = item
            binding.executePendingBindings()

        }

        companion object {
            fun from(
                parent: ViewGroup,
            ): IssueListViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemIssueBinding.inflate(layoutInflater, parent, false)
                return IssueListViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IssueListViewHolder {
        return IssueListViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: IssueListViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

}