package com.example.bnccfinalproject.lookup

import okhttp3.*

class LookUpModel {
    private val okHttpClient = OkHttpClient()

    fun fetchData(callback: Callback) {
        val request: Request = Request.Builder().url(LookUpActivity.lookupDataApiURL).build()
        okHttpClient.newCall(request).enqueue(callback)
    }
}