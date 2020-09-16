package com.example.bnccfinalproject.infodialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.example.bnccfinalproject.R
import kotlinx.android.synthetic.main.dialog_card.view.*

class InformationDialog: DialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView: View = inflater.inflate(R.layout.dialog_card, container, false)

        rootView.close.setOnClickListener {
            dismiss()
        }

        return rootView
    }

    private fun setupDialogSize() {
        dialog?.window?.setBackgroundDrawable(this.context?.let { ContextCompat.getDrawable(it, R.drawable.dialog_background) })
    }

    override fun onStart() {
        super.onStart()
        setupDialogSize()
    }

}