package com.issue_tracker.android.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.issue_tracker.android.coroutine.Resource
import com.issue_tracker.android.network.model.HomePageData
import com.issue_tracker.android.ui.home.repo.HomeRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepo: HomeRepo
): ViewModel() {

    fun getHomePageData(customerId: String) = liveData<Resource<HomePageData>> {
        try {
            emit(Resource.Loading)
            val avatar = homeRepo.getHomePageData(customerId)
            emit(Resource.Success(avatar))
        } catch (exception: Exception) {
            emit(Resource.Error(exception.localizedMessage))
        }
    }

}