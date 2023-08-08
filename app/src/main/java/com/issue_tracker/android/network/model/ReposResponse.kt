package com.issue_tracker.android.network.model


import com.google.gson.annotations.SerializedName

class ReposResponse : ArrayList<ReposResponse.ReposResponseItem>(){
    data class ReposResponseItem(
        @SerializedName("name")
        val name: String? = "",
        @SerializedName("visibility") // private or public
        val visibility: String? = "",
        @SerializedName("created_at")
        val createdAt: String? = "",
        @SerializedName("id")
        val id: Int? = 0,
        @SerializedName("open_issues_count")
        val openIssueCount: Int? = 0
    )
}