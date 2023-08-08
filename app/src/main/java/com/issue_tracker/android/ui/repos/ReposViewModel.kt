package com.issue_tracker.android.ui.repos

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.issue_tracker.android.coroutine.Resource
import com.issue_tracker.android.network.model.ReposResponse
import com.issue_tracker.android.repo.AppRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ReposViewModel @Inject constructor(
    private val appRepo: AppRepo
): ViewModel() {

    val isLoading = MutableLiveData<Boolean>()

    fun getRepos(page: Int) = liveData<Resource<ReposResponse>> {
        emit(Resource.Loading)
        try {
            val data = appRepo.getRepos(page)
            emit(Resource.Success(data))
        } catch (exp: Exception) {
            emit(Resource.Error(exp.message))
        }
    }

    fun getReposPaged(): Flow<PagingData<ReposResponse.ReposResponseItem>> {
        return Pager(
            config =  PagingConfig(pageSize = 20)
        ) {
            ReposPagingSource(
                viewModel = this,
                repository = appRepo
            )
        }
            .flow
            .cachedIn(viewModelScope)
    }

}