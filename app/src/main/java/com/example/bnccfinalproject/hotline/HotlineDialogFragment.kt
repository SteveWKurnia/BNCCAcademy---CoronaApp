package com.example.bnccfinalproject.hotline

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bnccfinalproject.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.transition.Hold
import kotlinx.android.synthetic.main.modal_hotline_bottom_sheet.*

class HotlineDialogFragment: BottomSheetDialogFragment() {

    private lateinit var viewModel: HotlineViewModel

    private lateinit var adapter: HotlineAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HotlineViewModel::class.java)
        viewModel.getAllHotlineDatas()
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
        viewModel.hotlineDatas.observe(this,
            Observer<List<Hotline>> { items ->
                run {
                    setVisibility()
                    adapter.setItems(items)
                }
            })
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

    private fun setVisibility() {
        pb_hotline?.visibility = View.GONE
        rv_hotline?.visibility = View.VISIBLE
    }

    companion object {

        fun show(fragmentManager: FragmentManager) {
            val dialogFragment =
                HotlineDialogFragment()
            dialogFragment.show(fragmentManager, dialogFragment.tag)
        }

    }
}