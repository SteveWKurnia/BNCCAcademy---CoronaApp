package com.example.bnccfinalproject.lookup

import java.lang.Exception

interface LookupContract {
    interface View {
        fun onGetDataSuccess(list: List<LookUpData>)
        fun onError(e: Exception)
    }

    interface Presenter {
        fun getData()
    }
}