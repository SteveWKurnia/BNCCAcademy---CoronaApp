package com.example.bnccfinalproject.lookup

data class LookUpData(
    val provinceName: String,
    var numberOfPositiveCases: Int = 0,
    var numberOfRecoveredCases: Int = 0,
    var numberOfDeathCases: Int = 0,
    val provinceID: Int = -1
)