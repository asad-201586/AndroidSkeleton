package com.issue_tracker.android.ui.repos

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.issue_tracker.android.network.model.ReposResponse
import com.issue_tracker.android.repo.AppRepo
import com.issue_tracker.android.utils.interfaces.ApiException
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

class ReposPagingSource(
    private val viewModel: ReposViewModel,
    private val repository: AppRepo,
): PagingSource<Int, ReposResponse.ReposResponseItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ReposResponse.ReposResponseItem> {
        val pageNumber = params.key ?: 1

        return try {
            Timber.i("search_paging--------------- paging source: ")
            if (pageNumber == 1) viewModel.isLoading.value = true
            var response: ReposResponse?
            try {
                response = repository.getRepos(pageNumber)
            } catch (exception: Exception) {
                response = null
                Timber.i("search_paging -------error----- ${exception.message}")
            }

            viewModel.isLoading.value = false


            Timber.i("search_paging ----data----- $response")

            val result = response?:ArrayList()

            Timber.i("search_paging ----result----- ${result.isEmpty()}")

            run {
                val nextKey = if (result.isEmpty()) {
                    null
                } else {
                    pageNumber + 1
                }

                LoadResult.Page(
                    data = result,
                    prevKey = if (pageNumber == 1) null else pageNumber - 1,
                    nextKey = nextKey
                )
            }
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        } catch (exception: ApiException) {
            return LoadResult.Error(exception)
        }
    }
    override fun getRefreshKey(state: PagingState<Int, ReposResponse.ReposResponseItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

}