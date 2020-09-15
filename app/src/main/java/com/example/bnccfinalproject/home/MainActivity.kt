package com.example.bnccfinalproject

import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import android.util.Log
import com.example.bnccfinalproject.home.HomeData
import com.example.bnccfinalproject.home.HomeViewModel
import com.example.bnccfinalproject.hotline.HotlineDialogFragment
import com.example.bnccfinalproject.infodialog.InformationDialog
import com.example.bnccfinalproject.lookup.LookUpActivity
import com.example.bnccfinalproject.util.removeComma
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_persistent_bottom_sheet.*

class MainActivity : AppCompatActivity() {

    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.e("Activity", "LookUpActivity:onCreate")
        viewModel.getIndonesiaCoronaCase()
    }

    private fun openLookUpActivity() {
        val intent = Intent(this, LookUpActivity::class.java).apply {
        }
        startActivity(intent)
    }

    override fun onStart() {
        super.onStart()
        setupBottomSheet()
        setupHotlineButton()
        setupLookUpButton()
        setupInfoDialog()
        viewModel.coronaCase.observe(this, observer)
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
            var dialog = InformationDialog()
            dialog.show(supportFragmentManager, "customDialog")
        }
    }

    override fun onBackPressed() {
        if (BottomSheetBehavior.from(bottom_sheet).state == BottomSheetBehavior.STATE_EXPANDED) {
            BottomSheetBehavior.from(bottom_sheet).state = BottomSheetBehavior.STATE_COLLAPSED
        } else {
            super.onBackPressed()
        }
    }

    private val observer: Observer<HomeData> = Observer {
        setupTotalCaseVisibility()
        setupDeathCaseVisibility()
        setupPositiveCaseVisibility()
        setupRecoveredCaseVisibility()
        tv_total_case?.text = it.totalCase.removeComma()
        tv_positive?.text = it.positive.removeComma()
        tv_recovered?.text = it.recovered.removeComma()
        tv_deaths?.text = it.death.removeComma()
    }

    private fun setupTotalCaseVisibility() {
        pb_total_case?.visibility = View.GONE
        group_total_case?.visibility = View.VISIBLE
    }

    private fun setupDeathCaseVisibility() {
        pb_deaths?.visibility = View.GONE
        group_deaths?.visibility = View.VISIBLE
    }

    private fun setupPositiveCaseVisibility() {
        pb_positive?.visibility = View.GONE
        group_positive?.visibility = View.VISIBLE
    }

    private fun setupRecoveredCaseVisibility() {
        pb_recovered?.visibility = View.GONE
        group_recovered?.visibility = View.VISIBLE
    }

}