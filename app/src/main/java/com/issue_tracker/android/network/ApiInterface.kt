package com.issue_tracker.android.network

import com.issue_tracker.android.network.model.HomePageData
import com.issue_tracker.android.network.model.IssueListResponse
import com.issue_tracker.android.network.model.ReposResponse
import retrofit2.http.*


interface ApiInterface {
    @GET(HOMEPAGE_DATA)
    suspend fun getHomepageData(): HomePageData

    @GET("repos/$OWNER/{repo_name}/issues")
    suspend fun getIssueList(
        @HeaderMap headers: Map<String,String> = hashMapOf(
            "Authorization" to "Bearer $GITHUB_TOKEN",
            "Content-Type" to "application/json"
        ),
        @Path("repo_name") repoName: String,
        @Query("per_page") pageSize: Int = 20,
        @Query("page") page: Int,
    ): IssueListResponse

    @GET(REPO_LIST)
    suspend fun getRepositories(
        @HeaderMap headers: Map<String,String> = hashMapOf(
            "Authorization" to "Bearer $GITHUB_TOKEN",
        ),
        @Query("per_page") pageSize: Int = 20,
        @Query("page") page: Int
    ): ReposResponse
}

