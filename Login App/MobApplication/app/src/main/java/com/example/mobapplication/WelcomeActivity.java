package com.example.mobapplication;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    TextView welcomeLable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        welcomeLable = findViewById(R.id.welcomeLabel);

        String email = getIntent().getStringExtra("email");
        String result = getString(R.string.personalized_welcome, email);

        welcomeLable.setText(result);
    }
}
