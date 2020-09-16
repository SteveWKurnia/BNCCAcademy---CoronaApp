package com.example.bnccfinalproject.hotline

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bnccfinalproject.util.RetrofitInstance
import com.example.bnccfinalproject.util.UrlUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HotlineViewModel: ViewModel() {

    private val mutableHotlineDatas = MutableLiveData<List<Hotline>>()

    val hotlineDatas: LiveData<List<Hotline>>
        get() = mutableHotlineDatas

    fun getAllHotlineDatas() {
        RetrofitInstance.hotlineInstance.create(GetHotlineNetworkData::class.java).getHotlineDatas()
            .enqueue(object : Callback<List<Hotline>> {
                override fun onFailure(call: Call<List<Hotline>>, t: Throwable) {
                    Log.e("ERROR_RETRO", t.message.toString())
                }

                override fun onResponse(
                    call: Call<List<Hotline>>,
                    response: Response<List<Hotline>>
                ) {
                    if (!response.isSuccessful) Log.e("ERROR_RETRO", response.message())
                    else mutableHotlineDatas.postValue(response.body())
                }
            })

    }


}