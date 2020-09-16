package com.example.bnccfinalproject.lookup

import com.google.gson.annotations.SerializedName

data class LookUpData(
    @SerializedName("Provinsi")
    val provinceName: String,
    @SerializedName("Kasus_Posi")
    var numberOfPositiveCases: Int = 0,
    @SerializedName("Kasus_Semb")
    var numberOfRecoveredCases: Int = 0,
    @SerializedName("Kasus_Meni")
    var numberOfDeathCases: Int = 0
)