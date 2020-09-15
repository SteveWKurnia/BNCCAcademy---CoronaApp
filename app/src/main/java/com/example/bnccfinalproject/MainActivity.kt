package com.example.bnccfinalproject

import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bnccfinalproject.hotline.HotlineDialogFragment
import com.example.bnccfinalproject.lookup.LookUpActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_persistent_bottom_sheet.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
}