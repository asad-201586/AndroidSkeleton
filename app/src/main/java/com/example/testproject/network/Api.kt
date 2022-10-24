package com.example.testproject.network

import com.example.testproject.app.App
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class Api {
    private fun getChuckerInterceptor(): ChuckerInterceptor {
        return ChuckerInterceptor.Builder(App.staticContext)
            .collector(ChuckerCollector(App.staticContext))
            .maxContentLength(250000L)
            .redactHeaders(emptySet())
            .alwaysReadResponseBody(false)
            .build()
    }

    private fun getClient(): OkHttpClient.Builder {
        return OkHttpClient().newBuilder()
            .callTimeout(3, TimeUnit.MINUTES)
            .connectTimeout(3, TimeUnit.MINUTES)
            .readTimeout(3, TimeUnit.MINUTES)
            .writeTimeout(3, TimeUnit.MINUTES)
            .addInterceptor(getChuckerInterceptor())
            .addInterceptor(getHttpLoggingInterceptor())
    }

    private fun getHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    fun createService(): ApiInterface {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
            .client(getClient().build())
            .build()

        return retrofit.create(ApiInterface::class.java)
    }

    companion object {

        const val BASE_URL = "https://rflbestbuy.com/"

        private val service by lazy { Api().createService() }

        @JvmName("getLoginService1")
        fun getService() = service

    }

}

fun apiHitter(): ApiInterface {
    return Api.getService()
}
