package com.example.bnccfinalproject

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val instance: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://bncc-corona-versus.firebaseio.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}