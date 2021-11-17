package com.example.weatherapp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
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

import java.time.LocalTime;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements IBindData {
    int LAUNCH_SECOND_ACTIVITY = 1;
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
    ImageButton list;
    ArrayList<String> cityList = new ArrayList<>();
    IBindData iBindData;
    // uses this value to set back ground based on the current time
    LocalTime myTime;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iBindData = this;
        // sets views
        city = (EditText) findViewById(R.id.searchLocation);
        search = (ImageButton) findViewById(R.id.searchButton);
        list = (ImageButton) findViewById(R.id.listButton);
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
        myTime = LocalTime.now();
        setBackground(myTime.getHour(), background);

        search.setOnClickListener(view -> {
            // hides soft keyboard
            hideSoftKeyboard(MainActivity.this);

            // if user did not provide a city name, show toast to announce
            if (city.getText().toString().equals("")) {
                Toast.makeText(getApplicationContext(), "Please provide a city name!!!", Toast.LENGTH_SHORT).show();
            } else {
                // calls function requesting API
                AppController controller = new AppController(iBindData);
                controller.getData(this, city.getText().toString());
                city.setText(""); // resets value of edit text
            }

        });

        // starts second act ans passes cityList to it when list btn is clicked
        list.setOnClickListener(view -> {
            Intent myIntent = new Intent(this, HistoryListActivity.class);
            myIntent.putStringArrayListExtra("cities", this.cityList);
            if(myIntent.resolveActivity(getPackageManager()) != null){
                actLauncher.launch(myIntent);
            }
        });

    }

    // sets content for xml views
    @SuppressLint("SetTextI18n")
    public void setData(WeatherData cityData) {
            setBackground(cityData.hour, background);
            currentDate.setText(cityData.dateStr);
            currentTime.setText(cityData.timeStr);
            temperature.setText(cityData.tempC + "°");
            location.setText(cityData.city.toUpperCase());
            displayWeather.setText(cityData.weather);
            feels.setText("Feels like: " + cityData.feelTemp + "°C");
            humid.setText(cityData.humidity + "%");
            wind.setText(cityData.windSpeed + "m/s");
            sunRise.setText(cityData.sunRiseStr);
            sunSet.setText(cityData.sunSetStr);
            // add city to CityList
            if (!this.cityList.contains(cityData.city.toLowerCase())) {
                this.cityList.add(cityData.city.toLowerCase());
            }
    }

    // gets time and changes img src based on time
    @SuppressLint("UseCompatLoadingForDrawables")
    void setBackground(int hour, ImageView imgV) {
        displayWeather.setTextColor(Color.parseColor("#ffffff"));
        Drawable src;
        if (hour >= 4 && hour < 7) {
            src = getDrawable(R.drawable.dawn_);
            displayWeather.setTextColor(Color.parseColor("#000000"));
        } else if (hour >= 7 && hour < 16) {
            src = getDrawable(R.drawable.day_);
        } else if (hour >= 16 && hour < 19) {
            src = getDrawable(R.drawable.dusk_);
            displayWeather.setTextColor(Color.parseColor("#000000"));
        }else {
            src = getDrawable(R.drawable.night_);
        }
        imgV.setBackground(src);
    }

    // hides soft keyboard when search btn is clicked
    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        if (inputMethodManager.isAcceptingText()) {
            inputMethodManager.hideSoftInputFromWindow(
                    activity.getCurrentFocus().getWindowToken(),
                    0
            );
        }
    }

    @Override
    public void bindData(WeatherData weatherData) {
        setData(weatherData);
    }

    private ActivityResultLauncher<Intent> actLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @SuppressLint("SetTextI18n")
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == Activity.RESULT_OK){
                        // item of listview is clicked
                        if(result.getData() != null){
                            Intent myIntent = result.getData();
                            AppController controller = new AppController(iBindData);
                            controller.getData(getApplicationContext(), myIntent.getStringExtra("city"));
                        } else {
                            // add btn is clicked
                            myTime = LocalTime.now();
                            setBackground(myTime.getHour(), background);
                            feels.setText("Enter your city to check the weather forecast!");
                            currentDate.setText("Welcome to the weather app!!");
                            currentTime.setText("");
                            temperature.setText("");
                            location.setText("");
                            displayWeather.setText("");
                            humid.setText("");
                            wind.setText("");
                            sunRise.setText("");
                            sunSet.setText("");
                        }
                    }
                }
            });
}






