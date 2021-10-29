package com.example.pedometer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyAdapter extends ArrayAdapter<String> {
    Context context;
    String[] distances;
    String[] calories;
    public MyAdapter(Context context, String[] array,String[] distances,String[] calories){
        super(context,R.layout.view_list_single,R.id.listDate,array);
        this.context=context;
        this.distances=distances;
        this.calories=calories;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row=inflater.inflate(R.layout.view_list_single,parent,false);

        TextView distance=row.findViewById(R.id.listDistance);
        distance.setText(distances[position]);

        TextView calorie=row.findViewById(R.id.listCalorie);
        calorie.setText(calories[position]);

        return row;
    }
}
