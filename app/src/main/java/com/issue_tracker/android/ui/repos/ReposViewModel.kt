package com.issue_tracker.android.ui.repos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.issue_tracker.android.coroutine.Resource
import com.issue_tracker.android.network.model.ReposResponse
import com.issue_tracker.android.repo.AppRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ReposViewModel @Inject constructor(
    private val appRepo: AppRepo
): ViewModel() {

    fun getRepos(page: Int) = liveData<Resource<ReposResponse>> {
        emit(Resource.Loading)
        try {
            val data = appRepo.getRepos(page)
            emit(Resource.Success(data))
        } catch (exp: Exception) {
            emit(Resource.Error(exp.message))
        }
    }

}