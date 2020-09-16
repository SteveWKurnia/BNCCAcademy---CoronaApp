package com.example.bnccfinalproject

import okhttp3.Callback

interface PVContract {
    interface Presenter {
        fun getData(): Callback
    }

    interface View<E> {
        fun updateData(listData: List<E>)
        fun showError(e: Exception)
    }
}