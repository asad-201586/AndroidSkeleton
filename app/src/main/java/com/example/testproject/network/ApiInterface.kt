package com.example.testproject.network

import com.example.testproject.network.model.HomePageDataResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiInterface {
    @GET(HOME_PAGE_DATA)
    suspend fun getHomePageData(
        @Header("Authorization") authkey: String?,
        @Header("ACCEPT") applicationType: String?,
        @Query("width") width: Int,
        @Query("lang_code") langCode: String?,
        @Query("currency_code") currencyCode: String?,
        @Query("storefront_id") storeFrontId: String?,
        @Query("city_code") cityCode: String = "",
        @Query("area") area: String = "",
    ): HomePageDataResponse
}

