package com.example.bnccfinalproject

import okhttp3.Callback

interface PVContract {
    interface Presenter {
        fun getData(): Callback
    }

    interface View {
        fun updateData(listData: List<*>)
        fun showError(e: Exception)
    }
}