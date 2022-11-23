package com.example.testproject.network.model


import com.google.gson.annotations.SerializedName

data class HomePageCategoryResponse(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Boolean
) {
    data class Data(
        @SerializedName("category_id")
        val categoryId: String,
        @SerializedName("http_image_path")
        val httpImagePath: String,
        @SerializedName("https_image_path")
        val httpsImagePath: String,
        @SerializedName("name")
        val name: String
    )
}