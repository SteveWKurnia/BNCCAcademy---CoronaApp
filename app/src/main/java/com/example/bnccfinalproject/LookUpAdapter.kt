package com.example.bnccfinalproject

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class LookUpAdapter():  RecyclerView.Adapter<LookUpViewHolder>(),Filterable{
    private val lookUpListFull = mutableListOf<LookUpData>()
    private val lookUpList = mutableListOf<LookUpData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LookUpViewHolder {
        return LookUpViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_look_up,parent,false))
    }

    override fun getItemCount(): Int {
        return lookUpList.size
    }

    override fun onBindViewHolder(holder: LookUpViewHolder, position: Int) {
            holder.bind(lookUpList[position])
    }

    override fun getFilter(): Filter = filter

    private val filter = object: Filter(){

        override fun performFiltering(p0: CharSequence?): FilterResults {
            val filter = mutableListOf<LookUpData>()

            if(p0.isNullOrBlank()){
                filter.addAll(lookUpListFull)
            }
            else{
                val filterPattern = p0.toString().toLowerCase(Locale.ROOT).trim()
                for(i in lookUpListFull.indices){

                    if(lookUpListFull.get(i).provinceName.toLowerCase(Locale.ROOT).contains(filterPattern)){
                        filter.add(lookUpListFull.get(i))
                    }
                }
            }

            val filterResults = FilterResults()
            filterResults.values = filter

            return filterResults
        }

        override fun publishResults(p0: CharSequence?, p1: FilterResults?) {

            lookUpList.apply {
                clear()
                addAll(p1?.values as List<LookUpData>)
            }
            notifyDataSetChanged()
        }
    }

    fun setData(LookUpData:List<LookUpData>){
        lookUpList.apply {
            clear()
            addAll(LookUpData)
        }

        lookUpListFull.apply {
            clear()
            addAll(LookUpData)
        }

        notifyDataSetChanged()
    }

}