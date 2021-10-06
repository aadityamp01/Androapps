package com.example.passwordmanager

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.passwordmanager.Data.Key
import com.example.passwordmanager.databinding.RecyclerviewColumnBinding
import kotlinx.android.synthetic.main.recyclerview_column.view.*

class RecyclerViewAdapter(val rvInterface: RVInterface): RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {

    private var Keylist = emptyList<Key>()
    private var _binding:RecyclerviewColumnBinding? = null
    private val binding get() = _binding!!
    private lateinit var context:Context

    inner class RecyclerViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                val data: Key = Keylist[position]
                rvInterface.onViewClick(data)
            }
            itemView.copy_to_clipboard.setOnClickListener {
                val data: Key = Keylist[adapterPosition]
                rvInterface.onClipboardClick(data)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewAdapter.RecyclerViewHolder {
        _binding = RecyclerviewColumnBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        context = parent.context
        return RecyclerViewHolder(binding.root)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: RecyclerViewAdapter.RecyclerViewHolder, position: Int) {
        val currentItem = Keylist[position]

        binding.accountName.text = currentItem.account
        binding.secondaryText.text = currentItem.username
        binding.extraInfoTags.text = currentItem.additional
        //Random colors
        val cardColor = when(currentItem.id%4){
            1-> context.resources.getColor(R.color.green)
            2-> context.resources.getColor(R.color.pink)
            3-> context.resources.getColor(R.color.purple)
            else-> context.resources.getColor(R.color.yellow)
        }
        binding.imageCard.setCardBackgroundColor(cardColor)
        binding.expandView.setOnClickListener {
            if (binding.extraInfoTags.isVisible) {
                it.animate().rotation(180f).setDuration(200).start()
                binding.extraInfoTags.visibility = View.GONE
            }else{
                it.animate().rotation(-180f).setDuration(200).start()
                binding.extraInfoTags.visibility = View.VISIBLE
            }
        }

    }

    override fun getItemCount(): Int {
        return Keylist.size
    }

    fun notifyChanges(KeyList: List<Key>){
        this.Keylist = KeyList
        notifyDataSetChanged()
    }

    fun notifyItemAdded(KeyList: List<Key>, position:Int){
        this.Keylist = Keylist
        notifyItemAdded(KeyList,position)
    }

    interface RVInterface{
        fun onViewClick(key: Key)
        fun onClipboardClick(key: Key)
    }
}