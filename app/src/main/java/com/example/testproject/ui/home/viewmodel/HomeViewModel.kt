package com.example.testproject.ui.home.viewmodel

import androidx.lifecycle.liveData
import com.example.testproject.base.BaseViewModel
import com.example.testproject.coroutine.Resource
import com.example.testproject.network.model.HomePageDataResponse

class HomeViewModel: BaseViewModel() {

    fun getHomePageData(customerId: String) = liveData<Resource<HomePageDataResponse>> {
        try {
            emit(Resource.Loading)
            val avatar = getHomeRepo().getHomePageData(customerId)
            emit(Resource.Success(avatar))
        } catch (exception: Exception) {
            emit(Resource.Error(exception.localizedMessage))
        }
    }

}