package com.example.testproject.ui.home.serverReq

import androidx.databinding.DataBindingUtil
import com.example.testproject.R
import com.example.testproject.base.BaseAdapter
import com.example.testproject.databinding.ItemFeaturedCategoryBinding
import com.example.testproject.network.model.HomePageCategoryResponse

class CategoryAdapter: BaseAdapter<HomePageCategoryResponse.Data>(R.layout.item_featured_category) {
    override fun onBindViewHolder(holder: IViewHolder, position: Int) {
        val item = list[position]
        //val binding = ItemFeaturedCategoryBinding.bind(holder.itemView)
        val binding: ItemFeaturedCategoryBinding = DataBindingUtil.bind(holder.itemView)!!
        binding.categoryData = item
    }

}