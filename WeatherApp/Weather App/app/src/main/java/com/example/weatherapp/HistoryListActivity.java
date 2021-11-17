package com.example.weatherapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class HistoryListActivity extends AppCompatActivity implements IBindData {

    ArrayList<String> cities;
    ArrayList<WeatherData> weatherDataList;
    CityAdapter adapter;
    ListView historyList;
    ImageView addBtn;

    IBindData iBindData;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);
        historyList = (ListView) findViewById(R.id.historyList);
        addBtn = (ImageView) findViewById(R.id.addButton);

        iBindData = this;
        Intent myIntent = getIntent();
        if(myIntent != null){

            AppController controller = new AppController(iBindData);
            cities = myIntent.getStringArrayListExtra("cities");
            weatherDataList = new ArrayList<>();
            adapter = new CityAdapter(this, weatherDataList);
            historyList.setAdapter(adapter);

            for (String city: cities){
                weatherDataList.add(new WeatherData(city));
                adapter.notifyDataSetChanged();
                controller.getData(this, city);
            }

            historyList.setOnItemClickListener((parent, view, i, id) -> {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("city",cities.get(i));
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            });
        }

        addBtn.setOnClickListener(view -> {
                setResult(Activity.RESULT_OK, null);
                finish();
        });
    }

    @Override
    public void bindData(WeatherData weatherData) {
        for (int i = 0; i< weatherDataList.size(); i++){
            if(weatherDataList.get(i).city.equals(weatherData.city)) {
                weatherDataList.set(i, weatherData);
            }
        }
        adapter.notifyDataSetChanged();
    }
}