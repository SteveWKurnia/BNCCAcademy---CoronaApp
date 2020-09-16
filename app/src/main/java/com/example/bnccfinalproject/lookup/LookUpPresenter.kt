package com.example.bnccfinalproject.lookup

import com.example.bnccfinalproject.PVContract
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import org.json.JSONArray
import java.io.IOException

class LookUpPresenter(val lookUpModel: LookUpModel, val view: PVContract.View<LookUpData>) :
    PVContract.Presenter {
    fun fetchData() {
        lookUpModel.fetchData(getData())
    }

    override fun getData(): Callback {
        return object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                view.showError(e)
            }

            override fun onResponse(call: Call, response: Response) {
                try {
                    val jsonString = response.body?.string()
                    val jsonArray = JSONArray(jsonString)
                    val lookupDataFromNetwork = mutableListOf<LookUpData>()

                    for (i in 0 until jsonArray.length()) {
                        val attribute = jsonArray.getJSONObject(i).getJSONObject("attributes")
                        lookupDataFromNetwork.add(
                            LookUpData(
                                provinceID = attribute.getInt("Kode_Provi"),
                                provinceName = attribute.getString("Provinsi"),
                                numberOfPositiveCases = attribute.getInt("Kasus_Posi"),
                                numberOfRecoveredCases = attribute.getInt("Kasus_Semb"),
                                numberOfDeathCases = attribute.getInt("Kasus_Meni")
                            )
                        )
                    }

                    view.updateData(lookupDataFromNetwork)
                } catch (e: Exception) {
                    view.showError(e)
                }
            }
        }
    }

}