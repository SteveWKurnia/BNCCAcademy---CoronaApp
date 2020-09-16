package com.example.bnccfinalproject.util

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val hotlineInstance: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(UrlUtils.HOTLINE_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val coronaInstance: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(UrlUtils.KAWAL_CORONA_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}