package com.example.NFTSalesViewer.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.NFTSalesViewer.data.NFT
import com.example.NFTSalesViewer.databinding.RowLayoutBinding

class MyAdapter : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    private var oldData = emptyList<NFT>()

    class MyViewHolder(val binding: RowLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            RowLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.tvSaleId.text = oldData[position].salesId.toString()
        holder.binding.tvOwners.text = oldData[position].Owners
        holder.binding.tvSales.text = oldData[position].Sales
        holder.binding.tvBuyers.text = oldData[position].Buyers
        holder.binding.tvCollections.text = oldData[position].Collections
        holder.binding.tvTxns.text = oldData[position].Txns
    }

    override fun getItemCount(): Int {
        return oldData.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newData: List<NFT>){
        oldData = newData
        notifyDataSetChanged()
    }

}
