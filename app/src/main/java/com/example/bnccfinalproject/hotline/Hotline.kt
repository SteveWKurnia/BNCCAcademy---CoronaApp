package com.example.bnccfinalproject.hotline

import com.google.gson.annotations.SerializedName

data class Hotline(
    @SerializedName("img_icon")
    val img: String,
    @SerializedName("phone")
    var phoneNumber: String,
    @SerializedName("name")
    var description: String
)