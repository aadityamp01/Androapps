package com.todolist

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.todolist.databinding.ItemTaskrowBinding

class ItemAdapter(private val items: ArrayList<TDataModel>, private val context: Context): RecyclerView.Adapter<ItemAdapter.ViewHolder>(){

    class ViewHolder(val binding: ItemTaskrowBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemTaskrowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return items.size
    }


}