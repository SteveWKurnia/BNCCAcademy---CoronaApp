package com.example.bnccfinalproject.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bnccfinalproject.util.RetrofitInstance
import com.example.bnccfinalproject.util.UrlUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel: ViewModel() {

    private var mutableCoronaCase = MutableLiveData<HomeData>()

    val coronaCase: LiveData<HomeData>
        get() = mutableCoronaCase

    fun getIndonesiaCoronaCase() {
        RetrofitInstance.coronaInstance.create(GetHomeData::class.java).getIndonesiaCoronaCases()
            .enqueue(object : Callback<List<HomeData>> {
                override fun onFailure(call: Call<List<HomeData>>, t: Throwable) {
                    TODO("Not yet implemented")
                }

                override fun onResponse(
                    call: Call<List<HomeData>>,
                    response: Response<List<HomeData>>
                ) {
                    if (!response.isSuccessful) Log.e("ERROR_NETWORK", response.message())
                    else {
                        val networkData = response.body()
                        mutableCoronaCase.postValue(networkData?.get(0))
                    }
                }
            })
    }

}