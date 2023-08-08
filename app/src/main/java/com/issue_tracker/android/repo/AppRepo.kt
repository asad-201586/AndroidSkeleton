package com.issue_tracker.android.repo

import com.issue_tracker.android.network.ApiInterface
import javax.inject.Inject

class AppRepo @Inject constructor(
    private val apiHitter: ApiInterface
) {
    suspend fun getHomePageData() = apiHitter.getHomepageData()

    suspend fun getIssueList(page: Int) = apiHitter.getIssueList(page = page)
    suspend fun getRepos(page: Int) = apiHitter.getRepositories(page = page)

}