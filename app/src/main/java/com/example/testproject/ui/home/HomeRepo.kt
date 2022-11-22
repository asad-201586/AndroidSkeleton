package com.example.testproject.ui.home

import com.example.testproject.app.getPref
import com.example.testproject.network.apiHitter
import com.example.testproject.utils.PrefKeys

class HomeRepo {

    private val token = "bWlzNTdAcHJhbmdyb3VwLmNvbTpJWE94N1NVUFYwYUE0Rjg4Nmg4bno5V2I2STUzNTNBQQ=="

    suspend fun getHomePageData(customerId: String) = apiHitter().getHomePageData(
        authkey = token
        , customerId = customerId
        , width = 1081
        , langCode = "en"
        , currencyCode = "BD"
        , storeFrontId = "3"
    )

}