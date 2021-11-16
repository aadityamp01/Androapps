package com.example.weatherapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;

interface IBindData{
    void bindData(WeatherData weatherData);
}

// gets API
public class AppController {
    private static String APIKEY_ = "c22541c8c24aef3809ab3e168f7b1d7e";
    private String BASE_URL = "https://api.openweathermap.org/data/2.5/weather?q=";
    private IBindData iBindData;

    public AppController(IBindData iBindData) {
        this.iBindData = iBindData;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void getData(Context context, String cityName) {
        String apiUrl = BASE_URL + cityName + "&appid=" + APIKEY_;

        // instantiates the cache
        Cache cache = new DiskBasedCache(context.getCacheDir(), 1024 * 1024);
        // setups the network to use the HTTPURLConnection client
        Network network = new BasicNetwork(new HurlStack());
        // instantiates the request queue
        RequestQueue mRequestQueue = new RequestQueue(cache, network);
        // starts the queue
        mRequestQueue.start();

        WeatherData weatherData = new WeatherData(cityName);

        @SuppressLint("SetTextI18n") JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, apiUrl, null, response -> {
                    weatherData.parseJson(response);
                    iBindData.bindData(weatherData);
                },
                        error -> {
                            Toast.makeText(context, "Invalid city!!!", Toast.LENGTH_SHORT).show();
                        });

        // Add the request to the RequestQueue
        mRequestQueue.add(jsObjRequest);
    }
}
