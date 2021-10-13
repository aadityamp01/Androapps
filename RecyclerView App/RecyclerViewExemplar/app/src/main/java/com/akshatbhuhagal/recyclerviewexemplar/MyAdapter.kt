package com.akshatbhuhagal.recyclerviewexemplar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.rv_items.view.*

class MyAdapter (private val items : ArrayList<String>, private val listner : ItemClicked) : RecyclerView.Adapter<MyAdapter.PlaceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_items, parent, false)

        val viewHolder = PlaceViewHolder(view)

        view.setOnClickListener {
            listner.onItemClicked(items[viewHolder.adapterPosition])
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        val currentItem = items[position]
        holder.titleView.text = currentItem
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class PlaceViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val titleView : TextView = itemView.tvTitle
    }

    interface ItemClicked {
        fun onItemClicked(item : String)
    }


}