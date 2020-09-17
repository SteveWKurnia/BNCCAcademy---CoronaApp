package com.example.bnccfinalproject.lookup

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bnccfinalproject.R
import kotlinx.android.synthetic.main.activity_look_up.*

class LookUpActivity : AppCompatActivity(), LookUpContract.View {
    private val mockLookUpList = mutableListOf(
        LookUpData(provinceName = "Loading...")
    )

    private val presenter = LookUpPresenter(this)
    private val lookUpAdapter = LookUpAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_look_up)
        setupDynamicStatusBarColor()
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
        sv_search?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                lookUpAdapter.filter.filter(p0)
                return false
            }
        })
        setupSearchButton()
        setupClearButton()
        disableSearchViewClear()
    }

    private fun setupSearchButton() {
        iv_search?.setOnClickListener {
            sv_search?.requestFocus()
            (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).toggleSoftInput(InputMethodManager.SHOW_FORCED,0)
        }
    }

    private fun setupClearButton() {
        iv_clear?.setOnClickListener {
            sv_search.apply {
                setQuery("",false)
                clearFocus()
            }
        }
    }

    private fun disableSearchViewClear() {
        val closeBtn = sv_search?.findViewById<ImageView>(R.id.search_close_btn)
        closeBtn?.isEnabled = false
        closeBtn?.setImageDrawable(null)
    }

    private fun setupSwipeRefresh() {
        srlLookUpData.setOnRefreshListener {
            presenter.getData()
            sv_search.apply {
                setQuery("",false)
                clearFocus()
            }
        }
    }

    private fun setupDynamicStatusBarColor() {
        val view = this.window.decorView
        view.systemUiVisibility = view.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        window.statusBarColor = ContextCompat.getColor(this,
            R.color.off_white
        )
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

