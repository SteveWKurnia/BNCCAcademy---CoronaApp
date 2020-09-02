package com.example.bnccfinalproject

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.modal_hotline_bottom_sheet.*

class HotlineDialogFragment: BottomSheetDialogFragment() {

    private lateinit var adapter: HotlineAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =  inflater.inflate(R.layout.modal_hotline_bottom_sheet, container, false)


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupRecycler()
        setupCloseButton()
        adapter.setItems(mockData())
    }

    private fun setupCloseButton() {
        iv_close?.setOnClickListener{
            dismiss()
        }
    }

    private fun setupRecycler() {
        setupAdapter()
        rv_hotline?.apply {
            adapter = this@HotlineDialogFragment.adapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun setupAdapter() {
        adapter = HotlineAdapter()
    }

    private fun mockData(): List<Hotline> =
        listOf<Hotline>(
            Hotline("021-5210-411","Kementrian Kesehatan"),
            Hotline("0812-1212-3119","Kementrian Kesehatan"),
            Hotline("112","Pemprov DKI Jakarta"),
            Hotline("0813-8837-6955","Pemprov DKI Jakarta"),
            Hotline("024-358-0713","Pemprov Jawa Tengah"),
            Hotline("0823-1360-0560","Pemprov Jawa Tengah"),
            Hotline("031-843-0313","Pemprov Jawa Timur"),
            Hotline("0813-3436-7800","Pemprov Jawa Timur"),
            Hotline("119","Pemprov Jawa Barat"),
            Hotline("0811-209-3306","Pemprov Jawa Barat"),
            Hotline("0274-555-585","Pemprov D.I Yogyakarta"),
            Hotline("0811-2764-800","Pemprov D.I Yogyakarta")
        )

    companion object {

        fun show(fragmentManager: FragmentManager) {
            val dialogFragment = HotlineDialogFragment()
            dialogFragment.show(fragmentManager, dialogFragment.tag)
        }

    }
}