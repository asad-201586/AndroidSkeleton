package com.example.testproject.ui.home

import com.example.testproject.app.getPref
import com.example.testproject.network.apiHitter
import com.example.testproject.utils.PrefKeys

class HomeRepo {

    private val token = "bWlzNTdAcHJhbmdyb3VwLmNvbTpJWE94N1NVUFYwYUE0Rjg4Nmg4bno5V2I2STUzNTNBQQ=="

    suspend fun getHomePageData() = apiHitter().getHomePageData(
        authkey = token
        , applicationType = "application/json"
        , width = 720
        , langCode = "en"
        , currencyCode = "BDT"
        , storeFrontId = "3"
    )

}