package com.example.bnccfinalproject.lookup

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.bnccfinalproject.PVContract
import com.example.bnccfinalproject.util.RetrofitInstance
import retrofit2.*
import java.lang.Exception

class LookUpPresenter(val view: LookupContract.View) :
    LookupContract.Presenter {
    override fun getData() {
        RetrofitInstance.coronaInstance.create(GetLookupData::class.java).getLookupDatas().enqueue(
        object : Callback<List<LookUpNetworkAttribute>> {
            override fun onFailure(call: Call<List<LookUpNetworkAttribute>>, t: Throwable) {
                view.onError(t as Exception)
            }

            override fun onResponse(call: Call<List<LookUpNetworkAttribute>>, response: Response<List<LookUpNetworkAttribute>>) {
                if (!response.isSuccessful) Log.e("ERROR_RETRO", response.message())
                else {
                    val listLookUpData = mutableListOf<LookUpData>()
                    for (i in response.body()!!.indices) {
                        response.body()?.get(i)?.attribute.let {
                            if (it != null) {
                                listLookUpData.add(it)
                            }
                        }
                    }
                    view.onGetDataSuccess(listLookUpData)
                }
            }
        })
    }
}