package com.issue_tracker.android.ui.home.repo

import com.issue_tracker.android.network.ApiInterface
import javax.inject.Inject

class HomeRepo @Inject constructor(
    private val apiHitter: ApiInterface
) {
    suspend fun getHomePageData() = apiHitter.getHomepageData()

    suspend fun getIssueList(page: Int) = apiHitter.getIssueList(page = page)

}