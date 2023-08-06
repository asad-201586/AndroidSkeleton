package com.issue_tracker.android.network

import com.issue_tracker.android.network.model.HomePageData
import retrofit2.http.GET


interface ApiInterface {
    @GET(HOMEPAGE_DATA)
    suspend fun getHomepageData(): HomePageData
}

