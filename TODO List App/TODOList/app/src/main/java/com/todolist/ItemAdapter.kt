package com.todolist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.todolist.databinding.ItemTaskrowBinding

class ItemAdapter(private val items: MainActivity, private val context: ArrayList<String>): RecyclerView.Adapter<ItemAdapter.ViewHolder>(){

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