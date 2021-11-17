package com.example.weatherapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class WeatherData {
    String city;
    String weather;
    String dateStr;
    String timeStr;
    int hour;
    String sunRiseStr;
    String sunSetStr;
    int tempC;
    int tempF;
    int feelTemp;
    int humidity;
    double windSpeed;

    public WeatherData(String city) {
        this.city = city.toUpperCase();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void parseJson(JSONObject response) {
        try{
            JSONObject mainWeather = response.getJSONObject("main");
            JSONObject windInfo = response.getJSONObject("wind");
            JSONObject sunInfo = response.getJSONObject("sys");

            // converts unix time to date
            Date dateInfo = new Date(response.getLong("dt") * 1000);
            Date sunRiseD = new Date(sunInfo.getLong("sunrise") * 1000);
            Date sunSetD = new Date(sunInfo.getLong("sunset") * 1000);
            int timezone = response.getInt("timezone");

            // formats output
            @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, dd MMM yyyy");
            dateFormat.setTimeZone(TimeZone.getTimeZone(ZoneOffset.ofTotalSeconds(timezone)));
            @SuppressLint("SimpleDateFormat") SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm a");
            timeFormat.setTimeZone(TimeZone.getTimeZone(ZoneOffset.ofTotalSeconds(timezone)));

            // gets time & date data
            dateStr = dateFormat.format(dateInfo);
            timeStr = timeFormat.format(dateInfo);
            hour = Integer.parseInt(timeStr.substring(0,2)) ;
            sunRiseStr = timeFormat.format(sunRiseD);
            sunSetStr = timeFormat.format(sunSetD);

            // converts °K to °C
            tempC = (int) (Math.round(mainWeather.getDouble("temp") - 273.15));
            feelTemp = (int) (Math.round(mainWeather.getDouble("feels_like") - 273.15));

            // converts °K to °F
            tempF = (int) (Math.round(mainWeather.getDouble("temp") * 9 / 5 - 459.67));

            // gets weather information
            weather = response.getJSONArray("weather").getJSONObject(0).getString("main");
            humidity = mainWeather.getInt("humidity");
            windSpeed = windInfo.getDouble("speed");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
