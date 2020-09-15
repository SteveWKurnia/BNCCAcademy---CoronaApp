package com.example.bnccfinalproject.lookup

import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request

class LookUpModel {
    private val okHttpClient = OkHttpClient()

    companion object {
        const val lookupDataApiURL = "https://api.kawalcorona.com/indonesia/provinsi/"
    }

    fun fetchData(callback: Callback) {
        val request: Request = Request.Builder().url(lookupDataApiURL).build()
        okHttpClient.newCall(request).enqueue(callback)
    }
}