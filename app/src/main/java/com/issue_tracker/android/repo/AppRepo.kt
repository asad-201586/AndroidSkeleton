package com.issue_tracker.android.repo

import com.issue_tracker.android.network.ApiInterface
import javax.inject.Inject

class AppRepo @Inject constructor(
    private val apiHitter: ApiInterface
) {
    suspend fun getHomePageData() = apiHitter.getHomepageData()

    suspend fun getIssueList(page: Int,repoName: String) = apiHitter.getIssueList(page = page, repoName = repoName)
    suspend fun getRepos(page: Int) = apiHitter.getRepositories(page = page)

}