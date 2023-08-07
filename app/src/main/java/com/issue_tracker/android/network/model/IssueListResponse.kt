package com.issue_tracker.android.network.model


import com.google.gson.annotations.SerializedName

class IssueListResponse : ArrayList<IssueListResponse.IssueListResponseItem>(){
    data class IssueListResponseItem(
        @SerializedName("body")
        val body: String? = "",
        @SerializedName("closed_at")
        val closedAt: Any? = Any(),
        @SerializedName("created_at")
        val createdAt: String? = "",
        @SerializedName("id")
        val id: Int? = 0,
        @SerializedName("number")
        val number: Int? = 0,
        @SerializedName("state")
        val state: String? = "",
        @SerializedName("title")
        val title: String? = "",
        @SerializedName("updated_at")
        val updatedAt: String? = "",
        @SerializedName("user")
        val user: User? = User()
    ) {
        data class User(
            @SerializedName("avatar_url")
            val avatarUrl: String? = "",
            @SerializedName("id")
            val id: Int? = 0,
            @SerializedName("login")
            val login: String? = ""
        )
    }
}