package com.example.bnccfinalproject

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.main_persistent_bottom_sheet.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        setupBottomSheet()
        setupHotlineButton()
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

    override fun onBackPressed() {
        if (BottomSheetBehavior.from(bottom_sheet).state == BottomSheetBehavior.STATE_EXPANDED) {
            BottomSheetBehavior.from(bottom_sheet).state = BottomSheetBehavior.STATE_COLLAPSED
        } else {
            super.onBackPressed()
        }
    }
}