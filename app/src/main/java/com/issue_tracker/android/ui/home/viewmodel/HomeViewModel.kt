package com.issue_tracker.android.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.issue_tracker.android.coroutine.Resource
import com.issue_tracker.android.network.model.HomePageData
import com.issue_tracker.android.network.model.IssueListResponse
import com.issue_tracker.android.repo.AppRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val appRepo: AppRepo
): ViewModel() {

    fun getHomePageData() = liveData<Resource<HomePageData>> {
        try {
            emit(Resource.Loading)
            val avatar = appRepo.getHomePageData()
            emit(Resource.Success(avatar))
        } catch (exception: Exception) {
            emit(Resource.Error(exception.localizedMessage))
        }
    }

    fun getIssueList(page: Int) = liveData<Resource<IssueListResponse>> {
        try {
            emit(Resource.Loading)
            val issues = appRepo.getIssueList(page)
            emit(Resource.Success(issues))
        } catch (exp: Exception) {
            emit(Resource.Error(exp.message))
            Timber.i("issue_error -------------- ${exp.message}")
        }
    }

}