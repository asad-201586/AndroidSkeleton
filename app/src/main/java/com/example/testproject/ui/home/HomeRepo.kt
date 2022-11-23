package com.example.testproject.ui.home

import com.example.testproject.network.apiHitter

class HomeRepo {

    suspend fun getHomePageData() = apiHitter().getHomePageData()
    suspend fun getAllCategoryData() = apiHitter().getAllCategory()

}