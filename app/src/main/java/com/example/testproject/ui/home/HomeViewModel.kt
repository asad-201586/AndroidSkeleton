package com.example.testproject.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.testproject.base.BaseViewModel
import com.example.testproject.coroutine.Resource
import com.example.testproject.network.model.HomePageDataResponse

class HomeViewModel: BaseViewModel() {

    private val _homeData = MutableLiveData<HomePageDataResponse>()
    private val homeData: LiveData<HomePageDataResponse> = _homeData

    fun getHomePageData() = liveData<Resource<HomePageDataResponse>> {
        try {
            emit(Resource.Loading)
            val avatar = getHomeRepo().getHomePageData()
            emit(Resource.Success(avatar))
            //_homeData.value = avatar
        } catch (exception: Exception) {
            emit(Resource.Error(exception.localizedMessage))
        }
    }

}