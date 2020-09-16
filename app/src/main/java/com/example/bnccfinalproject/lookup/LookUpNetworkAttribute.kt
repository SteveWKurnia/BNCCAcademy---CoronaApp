package com.example.bnccfinalproject.lookup

import com.google.gson.annotations.SerializedName

data class LookUpNetworkAttribute (
    @SerializedName("attributes")
    val attribute: LookUpData
)