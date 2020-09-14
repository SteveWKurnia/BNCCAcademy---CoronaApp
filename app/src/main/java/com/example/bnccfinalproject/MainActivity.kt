package com.example.bnccfinalproject

import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bnccfinalproject.hotline.HotlineDialogFragment
import com.example.bnccfinalproject.lookup.LookUpActivity
import android.view.View
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_persistent_bottom_sheet.*
import okhttp3.*
import org.json.JSONArray
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private val okHttpClient = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val request = Request.Builder()
            .url("https://api.kawalcorona.com/indonesia/")
            .build()

        okHttpClient.newCall(request).enqueue(getCallback())
    }

    private fun openLookUpActivity(){
        val intent = Intent(this,
            LookUpActivity::class.java)
        startActivity(intent)
    }

    override fun onStart() {
        super.onStart()
        setupBottomSheet()
        setupHotlineButton()
        setupLookUpButton()
        setupInfoDialog()
    }

    private fun setupBottomSheet() {
        BottomSheetBehavior.from(bottom_sheet).apply {
            isHideable = false
            peekHeight = Resources.getSystem().displayMetrics.heightPixels / 3 * 2
        }
    }

    private fun setupHotlineButton() {
        rl_hotline?.setOnClickListener {
            HotlineDialogFragment.show(supportFragmentManager)
        }
    }

    private fun setupLookUpButton() {
        rl_look_up?.setOnClickListener{
            openLookUpActivity()
        }
    }

    private fun setupInfoDialog() {
        iv_info?.setOnClickListener{
            //no implementation yet
        }
    }

    override fun onBackPressed() {
        if (BottomSheetBehavior.from(bottom_sheet).state == BottomSheetBehavior.STATE_EXPANDED) {
            BottomSheetBehavior.from(bottom_sheet).state = BottomSheetBehavior.STATE_COLLAPSED
        } else {
            super.onBackPressed()
        }
    }

    private fun getCallback(): Callback
    {
        return object : Callback
        {
            override fun onFailure(call: Call, e: IOException) {
                this@MainActivity.runOnUiThread {
                    Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onResponse(call: Call, response: Response) {
                try {
                    val jsonString = response.body?.string()
                    val jsonArray = JSONArray(jsonString)
                    val homeListFromNetwork = mutableListOf<HomeData>()

                    for (i in 0 until jsonArray.length())
                    {
                        val positive = jsonArray.getJSONObject(i).getString("positif")
                        val posInt = positive.replace(",", "").toInt()

                        val recovered = jsonArray.getJSONObject(i).getString("sembuh")
                        val recInt = recovered.replace(",", "").toInt()

                        val death = jsonArray.getJSONObject(i).getString("meninggal")
                        val deathInt = death.replace(",", "").toInt()

                        homeListFromNetwork.add(
                            HomeData(
                                positiveCases = posInt,
                                recoveredCases = recInt,
                                deathCases = deathInt,
                                totalCases = posInt + recInt + deathInt
                            )
                        )

                        this@MainActivity.runOnUiThread {
                            tv_positive?.text = homeListFromNetwork[i].positiveCases.toString()
                            tv_recovered?.text = homeListFromNetwork[i].recoveredCases.toString()
                            tv_deaths?.text = homeListFromNetwork[i].deathCases.toString()
                            tv_total_case?.text = homeListFromNetwork[i].totalCases.toString()
                        }
                    }
                } catch (e: Exception) {
                    this@MainActivity.runOnUiThread {
                        Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_SHORT).show()
                    }
                }

            }

        }
    }

}