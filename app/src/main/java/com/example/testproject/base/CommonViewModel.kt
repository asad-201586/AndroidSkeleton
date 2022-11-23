package com.example.testproject.base

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.testproject.coroutine.Resource
import com.example.testproject.network.model.HomePageDataResponse
import com.example.testproject.utils.extension.isNotNull
import com.google.gson.Gson

class CommonViewModel: BaseViewModel() {
    private val _homeData = MutableLiveData<HomePageDataResponse>()
    val homeData: LiveData<HomePageDataResponse> = _homeData

    fun getHomePageData() = liveData<Resource<HomePageDataResponse>> {
        try {
            emit(Resource.Loading)
            val avatar = getHomeRepo().getHomePageData()
            emit(Resource.Success(avatar))
            _homeData.value = avatar
            Log.d("share_db", "getHomePageData(original): ${Gson().toJson(avatar)}")
        } catch (exception: Exception) {
            emit(Resource.Error(exception.localizedMessage))
        }
    }

    fun isHomePageDataAvailable() = homeData.isNotNull()
}