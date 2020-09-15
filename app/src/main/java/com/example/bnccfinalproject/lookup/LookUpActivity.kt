package com.example.bnccfinalproject.lookup

import com.example.bnccfinalproject.R
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_look_up.*
import okhttp3.*
import org.json.JSONArray
import java.io.IOException

class LookUpActivity : AppCompatActivity() {

    private val mockLookUpList = mutableListOf<LookUpData>(
        LookUpData(provinceName = "Loading...")
    )

    private val okHttpClient = OkHttpClient()

    companion object {
        const val lookupDataApiURL = "https://api.kawalcorona.com/indonesia/provinsi/"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_look_up)

        iv_arrow.setOnClickListener {
            finish()
        }

        val lookUpAdapter = LookUpAdapter()
        lookUpAdapter.setData(mockLookUpList)
        rvLookUp.layoutManager = LinearLayoutManager(this)
        rvLookUp.adapter = lookUpAdapter
        fetchData(lookUpAdapter)

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                lookUpAdapter.filter.filter(p0)
                return false
            }
        })
    }

    private fun fetchData(lookupAdapter: LookUpAdapter) {
        val request: Request = Request.Builder().url(lookupDataApiURL).build()
        okHttpClient.newCall(request).enqueue(getCallback(lookupAdapter))
    }

    private fun getCallback(lookupAdapter: LookUpAdapter): Callback {
        return object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                this@LookUpActivity.runOnUiThread {
                    Toast.makeText(this@LookUpActivity, e.message, Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                }
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

                    this@LookUpActivity.runOnUiThread {
                        lookupAdapter.setData(lookupDataFromNetwork)
                    }
                } catch (e: Exception) {
                    this@LookUpActivity.runOnUiThread {
                        Toast.makeText(this@LookUpActivity, e.message, Toast.LENGTH_SHORT).show()
                        e.printStackTrace()
                    }
                }
            }
        }
    }
}

