package com.example.bnccfinalproject.lookup

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface GetLookupData {

    @GET("indonesia/provinsi")
    fun getLookupDatas(): Call<List<LookUpNetworkAttribute>>

}