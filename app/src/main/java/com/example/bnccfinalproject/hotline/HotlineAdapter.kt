package com.example.bnccfinalproject.hotline

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bnccfinalproject.R
import kotlinx.android.synthetic.main.hotline_recycler_items.view.*

class HotlineAdapter: RecyclerView.Adapter<HotlineAdapter.HotlineViewHolder>() {

    private val data = mutableListOf<Hotline>()

    fun setItems(data: List<Hotline>) {
        this.data.apply {
            clear()
            addAll(data)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotlineViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.hotline_recycler_items, parent, false)
        return HotlineViewHolder(
            view
        )
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: HotlineViewHolder, position: Int) = holder.bind(data.get(position))

    class HotlineViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(hotline: Hotline?) {
            itemView.cl_item?.setOnClickListener {
                val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${hotline?.phoneNumber.orEmpty()}"))
                it.context.startActivity(intent)
            }
            itemView.tv_phone_number?.text = hotline?.phoneNumber.orEmpty()
            itemView.tv_description?.text = hotline?.description.orEmpty()
            Glide.with(itemView)
                .load(hotline?.img)
                .into(itemView.iv_logo)
        }

    }
}
