package com.example.testproject.network

import com.example.testproject.network.model.HomePageCategoryResponse
import com.example.testproject.network.model.HomePageDataResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiInterface {
    @GET(HOME_PAGE_DATA)
    suspend fun getHomePageData(
        @Header("Authorization") token: String = Api.TOKEN,
        @Query("width") width: Int = 720,
        @Query("lang_code") langCode: String = "en",
        @Query("currency_code") currencyCode: String = "BDT",
        @Query("storefront_id") storeFrontId: String = Api.STORE_FRONT_ID,
        @Query("city_code") cityCode: String = "",
        @Query("area") area: String = "",
    ): HomePageDataResponse

    @GET(ALL_CATEGORY)
    suspend fun getAllCategory(
        @Header("Authorization") token: String = Api.TOKEN,
        @Query("storefront_id") storeFrontId: String = Api.STORE_FRONT_ID,
    ): HomePageCategoryResponse
}

