package com.example.bnccfinalproject.lookup

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bnccfinalproject.R
import kotlinx.android.synthetic.main.activity_look_up.*
import java.lang.Exception

class LookUpActivity : AppCompatActivity(), LookUpContract.View {
    private val mockLookUpList = mutableListOf(
        LookUpData(provinceName = "Loading...")
    )

    private val presenter = LookUpPresenter(this)
    private val lookUpAdapter = LookUpAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_look_up)
        setupBackButton()
        setupAdapter()

        presenter.getData()
        setupSearch()
        setupSwipeRefresh()
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

    private fun setupSwipeRefresh() {
        srlLookUpData.setOnRefreshListener {
            presenter.getData()

            searchView.apply {
                setQuery("", false)
                clearFocus()
            }
        }
    }

    override fun onGetDataSuccess(list: List<LookUpData>) {
        lookUpAdapter.setData(list)
        srlLookUpData.isRefreshing = false
        pbLookUp.visibility = View.GONE
        srlLookUpData.visibility = View.VISIBLE
    }

    override fun onError(e: Exception) {
        Toast.makeText(this@LookUpActivity, e.message, Toast.LENGTH_SHORT).show()
        Log.e("QUERY FAILURE", Log.getStackTraceString(e), e.cause)
    }
}

