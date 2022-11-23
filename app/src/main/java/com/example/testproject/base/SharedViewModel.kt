package com.example.testproject.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.testproject.coroutine.Resource
import com.example.testproject.network.model.HomePageCategoryResponse
import com.example.testproject.network.model.HomePageDataResponse
import com.example.testproject.utils.extension.isNotNull

class SharedViewModel: BaseViewModel() {
    private val _homeData = MutableLiveData<HomePageDataResponse>()
    val homeData: LiveData<HomePageDataResponse> = _homeData

    private val _categoryData = MutableLiveData<HomePageCategoryResponse>()
    val categoryData: LiveData<HomePageCategoryResponse> = _categoryData

    fun isHomePageDataAvailable() = _homeData.value.isNotNull()
    fun isCategoryDataAvailable() = _categoryData.value.isNotNull()

    fun getHomePageData() = liveData<Resource<HomePageDataResponse>> {
        try {
            emit(Resource.Loading)
            val avatar = getHomeRepo().getHomePageData()
            emit(Resource.Success(avatar))
            _homeData.value = avatar
        } catch (exception: Exception) {
            emit(Resource.Error(exception.localizedMessage))
        }
    }



    fun getAllCategory() = liveData<Resource<HomePageCategoryResponse>> {
        try {
            emit(Resource.Loading)
            val avatar = getHomeRepo().getAllCategoryData()
            emit(Resource.Success(avatar))
            _categoryData.value = avatar
        } catch (exception: Exception) {
            emit(Resource.Error(exception.localizedMessage))
        }
    }




}