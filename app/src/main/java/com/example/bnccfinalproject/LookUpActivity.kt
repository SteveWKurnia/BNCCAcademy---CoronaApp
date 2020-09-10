package com.example.bnccfinalproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.widget.Adapter
import android.widget.EditText
import android.widget.Filterable
import android.widget.SearchView
import androidx.core.view.isNotEmpty
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_look_up.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_look_up.*
import kotlin.collections.contains as cont

class LookUpActivity : AppCompatActivity() {

    private val mockLookUpList = mutableListOf<LookUpData>(
        LookUpData(provinceName = "Aceh", numberOfPositiveCases = 16538, numberOfRecoveredCases = 10444, numberOfDeathCases = 736),
        LookUpData(provinceName = "Sumatera Utara", numberOfPositiveCases = 16538, numberOfRecoveredCases = 10444, numberOfDeathCases = 736),
        LookUpData(provinceName = "Sumatera Barat", numberOfPositiveCases = 16538, numberOfRecoveredCases = 10444, numberOfDeathCases = 736),
        LookUpData(provinceName = "Riau", numberOfPositiveCases = 16538, numberOfRecoveredCases = 10444, numberOfDeathCases = 736),
        LookUpData(provinceName = "Kepulauan Riau", numberOfPositiveCases = 16538, numberOfRecoveredCases = 10444, numberOfDeathCases = 736),
        LookUpData(provinceName = "Jambi", numberOfPositiveCases = 16538, numberOfRecoveredCases = 10444, numberOfDeathCases = 736),
        LookUpData(provinceName = "Sumatera Selatan", numberOfPositiveCases = 16538, numberOfRecoveredCases = 10444, numberOfDeathCases = 736),
        LookUpData(provinceName = "Bengkulu", numberOfPositiveCases = 16538, numberOfRecoveredCases = 10444, numberOfDeathCases = 736),
        LookUpData(provinceName = "Bangka Belitung", numberOfPositiveCases = 16538, numberOfRecoveredCases = 10444, numberOfDeathCases = 736),
        LookUpData(provinceName = "Lampung", numberOfPositiveCases = 16538, numberOfRecoveredCases = 10444, numberOfDeathCases = 736),
        LookUpData(provinceName = "Banten", numberOfPositiveCases = 16538, numberOfRecoveredCases = 10444, numberOfDeathCases = 736),
        LookUpData(provinceName = "DKI Jakarta", numberOfPositiveCases = 16538, numberOfRecoveredCases = 10444, numberOfDeathCases = 736),
        LookUpData(provinceName = "Jawa Barat", numberOfPositiveCases = 16538, numberOfRecoveredCases = 10444, numberOfDeathCases = 736),
        LookUpData(provinceName = "Jawa Tengah", numberOfPositiveCases = 16538, numberOfRecoveredCases = 10444, numberOfDeathCases = 736),
        LookUpData(provinceName = "Jawa Timur", numberOfPositiveCases = 16538, numberOfRecoveredCases = 10444, numberOfDeathCases = 736),
        LookUpData(provinceName = "Yogyakarta", numberOfPositiveCases = 16538, numberOfRecoveredCases = 10444, numberOfDeathCases = 736),
        LookUpData(provinceName = "Bali", numberOfPositiveCases = 16538, numberOfRecoveredCases = 10444, numberOfDeathCases = 736),
        LookUpData(provinceName = "Nusa Tenggara Barat", numberOfPositiveCases = 16538, numberOfRecoveredCases = 10444, numberOfDeathCases = 736),
        LookUpData(provinceName = "Nusa Tenggara Timur", numberOfPositiveCases = 16538, numberOfRecoveredCases = 10444, numberOfDeathCases = 736),
        LookUpData(provinceName = "Kalimantan Barat", numberOfPositiveCases = 16538, numberOfRecoveredCases = 10444, numberOfDeathCases = 736),
        LookUpData(provinceName = "Kalimantan Tengah", numberOfPositiveCases = 16538, numberOfRecoveredCases = 10444, numberOfDeathCases = 736),
        LookUpData(provinceName = "Kalimantan Selatan", numberOfPositiveCases = 16538, numberOfRecoveredCases = 10444, numberOfDeathCases = 736),
        LookUpData(provinceName = "Kalimantan Timur", numberOfPositiveCases = 16538, numberOfRecoveredCases = 10444, numberOfDeathCases = 736),
        LookUpData(provinceName = "Kalimantan Utara", numberOfPositiveCases = 16538, numberOfRecoveredCases = 10444, numberOfDeathCases = 736),
        LookUpData(provinceName = "Sulawesi Utara", numberOfPositiveCases = 16538, numberOfRecoveredCases = 10444, numberOfDeathCases = 736),
        LookUpData(provinceName = "Gorontalo", numberOfPositiveCases = 16538, numberOfRecoveredCases = 10444, numberOfDeathCases = 736),
        LookUpData(provinceName = "Sulawesi Tengah", numberOfPositiveCases = 16538, numberOfRecoveredCases = 10444, numberOfDeathCases = 736),
        LookUpData(provinceName = "Sulawesi Barat", numberOfPositiveCases = 16538, numberOfRecoveredCases = 10444, numberOfDeathCases = 736),
        LookUpData(provinceName = "Sulawesi Tenggara", numberOfPositiveCases = 16538, numberOfRecoveredCases = 10444, numberOfDeathCases = 736),
        LookUpData(provinceName = "Sulawesi Selatan", numberOfPositiveCases = 16538, numberOfRecoveredCases = 10444, numberOfDeathCases = 736),
        LookUpData(provinceName = "Maluku Utara", numberOfPositiveCases = 16538, numberOfRecoveredCases = 10444, numberOfDeathCases = 736),
        LookUpData(provinceName = "Maluku", numberOfPositiveCases = 16538, numberOfRecoveredCases = 10444, numberOfDeathCases = 736),
        LookUpData(provinceName = "Papua Barat", numberOfPositiveCases = 16538, numberOfRecoveredCases = 10444, numberOfDeathCases = 736),
        LookUpData(provinceName = "Papua", numberOfPositiveCases = 16538, numberOfRecoveredCases = 10444, numberOfDeathCases = 736)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_look_up)

        iv_arrow.setOnClickListener{
            openMainActivity()
        }

        val lookUpAdapter = LookUpAdapter()
        lookUpAdapter.setData(mockLookUpList)
        rvLookUp.layoutManager = LinearLayoutManager(this)
        rvLookUp.adapter = lookUpAdapter

        searchView?.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(p0: String?): Boolean {
                lookUpAdapter.filter.filter(p0)
                return false
            }
        })
    }

    private fun openMainActivity(){
        val intent = Intent(this,MainActivity::class.java).apply {
            putExtra("Extras","Helloe")
        }
        startActivity(intent)
    }
}

