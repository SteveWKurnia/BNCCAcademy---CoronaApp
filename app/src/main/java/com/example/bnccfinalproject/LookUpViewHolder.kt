package com.example.bnccfinalproject

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_look_up.view.*

class LookUpViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){

    fun bind(data: LookUpData){
        itemView.tv_look_up_province_name.text =   data.provinceName
        itemView.tv_look_up_confirmed_case.text = "${data.numberOfPositiveCases}"
        itemView.tv_look_up_recovered_case.text = "${data.numberOfRecoveredCases}"
        itemView.tv_look_up_death_case.text = "${data.numberOfDeathCases}"
    }
}