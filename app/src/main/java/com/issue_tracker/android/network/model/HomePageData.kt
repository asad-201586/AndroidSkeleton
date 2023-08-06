package com.issue_tracker.android.network.model


import com.google.gson.annotations.SerializedName

class HomePageData : ArrayList<HomePageData.HomePageDataItem>(){
    data class HomePageDataItem(
        @SerializedName("data")
        val `data`: String,
        @SerializedName("type")
        val type: String,
        @SerializedName("id")
        val id: String
    )
}