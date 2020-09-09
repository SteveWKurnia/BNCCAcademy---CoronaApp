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

//    bikin object baru yg namanya filter
    private val filter = object: Filter(){

    // performFiltering -> bakal kefire pertama
        override fun performFiltering(p0: CharSequence?): FilterResults {
            val filter = mutableListOf<LookUpData>() // buat filtering
            // check kalo p0 ada isi atau nggak

            if(p0.isNullOrBlank()){
                // tidak ada filtering yg dilakukan
                filter.addAll(lookUpListFull)
            }
            else{
                // filtering
                val filterPattern = p0.toString().toLowerCase(Locale.ROOT).trim() //locale.root ngecek ke hp bahasa yg dipake
                for(i in lookUpListFull.indices){
                    // indices untuk ngeloop index dari lookuplist
                    // kalo pake tembak index bisa ngehandle pake null safety kotlin,

                    if(lookUpListFull.get(i).provinceName.toLowerCase(Locale.ROOT).contains(filterPattern)){
                        filter.add(lookUpListFull.get(i))
                    }

                }
            }

            val filterResults = FilterResults() // buat nampung hasil dari filterannya
            filterResults.values = filter

            return filterResults
        }

    // lakuin action dan callback si publishResults
        override fun publishResults(p0: CharSequence?, p1: FilterResults?) {

            lookUpList.apply {
                clear()
                addAll(p1?.values as List<LookUpData>)
            }
            notifyDataSetChanged()
        }
    }

    fun setData(LookUpData:List<LookUpData>){
        // set LookUpList
        lookUpList.apply {
            clear()
            addAll(LookUpData)
        }

        lookUpListFull.apply {
            clear()
            addAll(LookUpData)
        }


        // ngefire kalo ada perubahan
        notifyDataSetChanged()
    }



}