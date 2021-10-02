package com.example.newsapp.adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newsapp.R;

import com.example.newsapp.model.NewsData;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.TextViewHolder>{

    ArrayList<NewsData> dataList;
    Context context;

    public RecyclerAdapter(ArrayList <NewsData> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;

    }

    @NonNull
    @Override
    public TextViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_single_item,parent, false);
        return new TextViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TextViewHolder holder, int position) {
       NewsData nd = dataList.get(position);
       holder.binding.txttitle.setText(nd.getTitle());
       holder.binding.txtcontent.setText(nd.getContent());
        Glide.with(context).load(nd.getImgUrl()).into(holder.binding.imgnews);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class TextViewHolder extends RecyclerView.ViewHolder{

        RecyclerSingleItemBinding binding;

        public TextViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = RecyclerSingleItemBinding.bind(itemView);
        }
    }
}
