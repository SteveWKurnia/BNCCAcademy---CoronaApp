package com.example.bnccfinalproject.home

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface GetHomeData {

    @GET("indonesia")
    fun getIndonesiaCoronaCases(): Call<List<HomeData>>
}