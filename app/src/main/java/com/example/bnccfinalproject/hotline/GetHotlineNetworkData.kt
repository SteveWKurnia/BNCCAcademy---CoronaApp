package com.example.bnccfinalproject.hotline

import retrofit2.Call
import retrofit2.http.GET

interface GetHotlineNetworkData {

    @GET("hotlines.json")
    fun getHotlineDatas(): Call<List<Hotline>>

}