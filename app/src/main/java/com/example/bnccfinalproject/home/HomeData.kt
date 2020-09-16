package com.example.bnccfinalproject.home

import com.google.gson.annotations.SerializedName

data class HomeData (
    @SerializedName("positif")
    val totalCase: String,
    @SerializedName("meninggal")
    val death: String,
    @SerializedName("sembuh")
    val recovered: String,
    @SerializedName("dirawat")
    val positive: String
)