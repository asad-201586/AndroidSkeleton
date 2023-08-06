package com.issue_tracker.android.ui.home.repo

import com.issue_tracker.android.network.ApiInterface
import javax.inject.Inject

class HomeRepo @Inject constructor(
    private val apiHitter: ApiInterface
) {

    private val token = "bWlzNTdAcHJhbmdyb3VwLmNvbTpJWE94N1NVUFYwYUE0Rjg4Nmg4bno5V2I2STUzNTNBQQ=="

    suspend fun getHomePageData(customerId: String) = apiHitter.getHomepageData()

}