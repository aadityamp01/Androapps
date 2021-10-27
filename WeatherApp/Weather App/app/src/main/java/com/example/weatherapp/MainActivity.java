package com.example.weatherapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {
    String APIKEY_ = "c22541c8c24aef3809ab3e168f7b1d7e";

    ImageButton search;
    EditText city;
    TextView location;
    TextView temperature;
    TextView displayWeather;
    TextView currentDate;
    TextView currentTime;
    TextView feels;
    ImageView background;
    TextView humid;
    TextView wind;
    TextView sunRise;
    TextView sunSet;

    // uses this value to set back ground based on the current time
    LocalTime myTime;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // sets views
        city = (EditText) findViewById(R.id.searchLocation);
        search = (ImageButton) findViewById(R.id.searchButton);
        location = (TextView) findViewById(R.id.location);
        temperature = (TextView) findViewById(R.id.temperature);
        displayWeather = (TextView) findViewById(R.id.weather);
        feels = (TextView) findViewById(R.id.feel);
        background = (ImageView) findViewById(R.id.bg);
        currentDate = (TextView) findViewById(R.id.currentDate);
        currentTime = (TextView) findViewById(R.id.currentTime);
        humid = (TextView) findViewById(R.id.humid);
        wind = (TextView) findViewById(R.id.wind);
        sunRise = (TextView) findViewById(R.id.dawn);
        sunSet = (TextView) findViewById(R.id.dusk);

        // gets current time
        myTime =  LocalTime.now();
        setBackground(myTime.getHour(), background);

        search.setOnClickListener(view -> {
            // hides soft keyboard
            hideSoftKeyboard(MainActivity.this);

            // if user did not provide a city name, show toast to announce
            if(city.getText().toString().equals("")){
                Toast.makeText(getApplicationContext(), "Please provide a city name!!!", Toast.LENGTH_SHORT).show();
            } else {
                // calls function requesting API
                getData(city.getText().toString());
                city.setText(""); // resets value of edit text
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void getData(String city) {
        String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + APIKEY_;
        // creates request queue
        RequestQueue mRequestQueue;
        // instantiates the cache
        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024);
        // setups the network to use the HTTPURLConnection client
        Network network = new BasicNetwork(new HurlStack());
        // instantiates the request queue
        mRequestQueue = new RequestQueue(cache, network);
        // starts the queue
        mRequestQueue.start();

        @SuppressLint("SetTextI18n") JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, apiUrl, null, response -> {
                    try {
                        // gets JSON objects
                        JSONObject mainWeather = response.getJSONObject("main");
                        JSONObject weather = response.getJSONArray("weather").getJSONObject(0);
                        JSONObject windInfo = response.getJSONObject("wind");
                        JSONObject sunInfo = response.getJSONObject("sys");

                        // converts unix time to date
                        Date dateInfo = new Date(response.getLong("dt")*1000);
                        Date sunRiseD = new Date(sunInfo.getLong("sunrise") * 1000);
                        Date sunSetD = new Date(sunInfo.getLong("sunset") * 1000);
                        int timezone = response.getInt("timezone");

                        // formats output
                        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, dd MMM yyyy");
                        dateFormat.setTimeZone(TimeZone.getTimeZone(ZoneOffset.ofTotalSeconds(timezone)));
                        @SuppressLint("SimpleDateFormat") SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm a");
                        timeFormat.setTimeZone(TimeZone.getTimeZone(ZoneOffset.ofTotalSeconds(timezone)));

                        String dateStr = dateFormat.format(dateInfo);
                        String timeStr = timeFormat.format(dateInfo);
                        String sunRiseStr = timeFormat.format(sunRiseD);
                        String sunSetStr = timeFormat.format(sunSetD);

                        // converts °K to °C
                        int tempC = (int) (Math.round(mainWeather.getDouble("temp") - 273.15));
                        int feelTemp = (int)(Math.round(mainWeather.getDouble("feels_like") - 273.15));

                        // gets weather information
                        int humidity = mainWeather.getInt("humidity");
                        double windSpeed = windInfo.getDouble("speed");

                        // sets content for xml views
                        setBackground(Integer.parseInt(timeStr.substring(0,2)), background);
                        currentDate.setText(dateStr);
                        currentTime.setText(timeStr);
                        temperature.setText(tempC + "°");
                        location.setText(city.toUpperCase());
                        displayWeather.setText(weather.getString("main"));
                        feels.setText("Feels like: " + feelTemp + "°C");
                        humid.setText(humidity + "%");
                        wind.setText(windSpeed + "m/s");
                        sunRise.setText(sunRiseStr);
                        sunSet.setText(sunSetStr);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                        error -> {
                            error.printStackTrace();
                            // displays error
                            Toast.makeText(getApplicationContext(), "Invalid city!!!", Toast.LENGTH_SHORT).show();
                            VolleyLog.e("Error: ", error.toString());
                            VolleyLog.e("Error: ", error.getLocalizedMessage());
                        });

        // Add the request to the RequestQueue
        mRequestQueue.add(jsObjRequest);
    }

    // gets time and changes img src based on time
    @SuppressLint("UseCompatLoadingForDrawables")
    void setBackground(int hour, ImageView imgV){
        Drawable src = getDrawable(R.drawable.night_);
        displayWeather.setTextColor(Color.parseColor("#ffffff"));

        if(hour >= 4 && hour < 7 ) {
            src = getDrawable(R.drawable.dawn_);
            displayWeather.setTextColor(Color.parseColor("#000000"));
        } else if(hour >=7 && hour < 16){
            src = getDrawable(R.drawable.day_);
        } else if(hour >= 16 && hour < 19 ){
            src = getDrawable(R.drawable.dusk_);
            displayWeather.setTextColor(Color.parseColor("#000000"));
        }
        imgV.setBackground(src);
    }

    // hides soft keyboard when search btn is clicked
    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        if(inputMethodManager.isAcceptingText()){
            inputMethodManager.hideSoftInputFromWindow(
                    activity.getCurrentFocus().getWindowToken(),
                    0
            );
        }
    }
}






