package com.example.weatherapp;

import static androidx.appcompat.content.res.AppCompatResources.getDrawable;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;

public class CityAdapter extends BaseAdapter {
    ArrayList<WeatherData> weatherDataList;
    Context context;

    public CityAdapter(Context context, ArrayList<WeatherData> weatherDataList){
        this.context = context;
        this.weatherDataList = weatherDataList;
    }
    @Override
    public int getCount() {
        return weatherDataList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @SuppressLint({"SetTextI18n", "InflateParams"})
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        WeatherData data = weatherDataList.get(i);
        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.activity_history_list,null);
        }
        TextView city = view.findViewById(R.id.city);
        TextView time = view.findViewById(R.id.time);
        TextView temp = view.findViewById(R.id.temp);

        city.setText(data.city);
        time.setText(data.timeStr + " - " + data.weather);
        temp.setText(data.tempC + "°");
        setBackground(data.hour, view);
        return view;
    }

//     gets time and changes img src based on time
    @SuppressLint("UseCompatLoadingForDrawables")
    void setBackground(int hour, View view){
        Drawable src;

        if(hour >= 4 && hour < 7 ) {
            src = getDrawable(context, R.drawable.dawn_);
        } else if(hour >=7 && hour < 16){
            src = getDrawable(context ,R.drawable.day_);
        } else if(hour >= 16 && hour < 19 ){
            src = getDrawable(context,R.drawable.dusk_);
        } else {
            src = getDrawable(context, R.drawable.night_);
        }
        view.findViewById(R.id.bg).setBackground(src);
    }

}

