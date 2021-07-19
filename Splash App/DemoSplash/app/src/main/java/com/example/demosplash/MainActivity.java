package com.example.demosplash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // To hide your AppBar
        Objects.requireNonNull(getSupportActionBar()).hide();


        new Handler(Looper.getMainLooper()).postDelayed(this::moveToWelcome,5000);

    }

    void moveToWelcome(){
        startActivity(new Intent( this,WelcomeActivity.class));
        finish();
    }
}