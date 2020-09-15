package com.example.bnccfinalproject

import com.example.bnccfinalproject.R
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bnccfinalproject.lookup.LookUpAdapter
import com.example.bnccfinalproject.lookup.LookUpData
import com.example.bnccfinalproject.lookup.LookUpModel
import com.example.bnccfinalproject.lookup.LookUpPresenter
import kotlinx.android.synthetic.main.activity_look_up.*
import okhttp3.*
import org.json.JSONArray
import java.io.IOException

class LookUpActivity : AppCompatActivity(), PVContract.View {

    private val mockLookUpList = mutableListOf(
        LookUpData(provinceName = "Loading...")
    )

    private val presenter = LookUpPresenter(LookUpModel(), this)
    private val lookUpAdapter = LookUpAdapter()

    companion object {
        const val lookupDataApiURL = "https://api.kawalcorona.com/indonesia/provinsi/"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_look_up)
        setupBackButton()
        setupAdapter()

        presenter.fetchData()
        setupSearch()
    }

    private fun setupBackButton() {
        iv_arrow.setOnClickListener {
            finish()
        }
    }

    private fun setupAdapter() {
        lookUpAdapter.setData(mockLookUpList)
        rvLookUp.layoutManager = LinearLayoutManager(this)
        rvLookUp.adapter = lookUpAdapter
    }

    private fun setupSearch() {
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

    override fun updateData(listData: List<*>) {
        this@LookUpActivity.runOnUiThread {
            lookUpAdapter.setData(listData as List<LookUpData>)
            pbLookUp.visibility = View.GONE
            rvLookUp.visibility = View.VISIBLE
        }
    }

    override fun showError(e: Exception) {
        this@LookUpActivity.runOnUiThread {
            Toast.makeText(this@LookUpActivity, e.message, Toast.LENGTH_SHORT).show()
            Log.e("QUERY FAILURE", Log.getStackTraceString(e), e.cause)
        }
    }
}

