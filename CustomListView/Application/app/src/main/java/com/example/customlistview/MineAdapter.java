package com.example.customlistview;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;
import java.util.Vector;

public class MineAdapter extends ArrayAdapter {

    List<String> text = new Vector<>();
    Context context;
    public MineAdapter(@NonNull Context context, int resource, @NonNull List<String> text) {
        super(context, resource, text);
        this.text=text;
        this.context = context;
    }

    @Nullable
    @Override
    public String getItem(int position) {
        return text.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.ilst_view_item,parent,false);
        TextView textView = convertView.findViewById(R.id.textView);
        textView.setText(getItem(position));
//        convertView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                context.startActivity(new Intent(MainActivity.class,clicked.class));
//            }
//        });
        return convertView;
    }
}
