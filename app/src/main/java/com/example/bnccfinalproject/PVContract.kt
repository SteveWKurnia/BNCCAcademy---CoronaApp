package com.example.bnccfinalproject

import retrofit2.Callback

interface PVContract {
    interface Presenter<E> {
        fun getData(): Callback<List<E>>
    }

    interface View<E> {
        fun updateData(listData: List<E>)
        fun showError(e: Exception)
    }
}