package com.todolist

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.todolist.databinding.ItemTaskrowBinding

class ItemAdapter(private val context: Context, private val items: ArrayList<TDataModel>) :
    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemTaskrowBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemTaskrowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder.binding.tvTaskTitle.text = item.Task
        holder.binding.tvDes.text = item.Description

        // Updating the background color according to the odd/even positions in list.
        if (position % 2 == 0) {
            holder.binding.lltRow.setBackgroundColor(
                Color.parseColor("#EBEBEB")
            )
        } else {
            holder.binding.lltRow.setBackgroundColor(
                Color.parseColor("#FFFFFF")
            )
        }

        holder.binding.ivEdit.setOnClickListener{

            if(context is MainActivity){
                context.updateRecordDialog(item)
            }
        }

        holder.binding.ivDelete.setOnClickListener{

            if(context is MainActivity){
                context.deleteRecordDialog(item)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }


}