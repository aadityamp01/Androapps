package com.example.mobapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    TextView welcomeLable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        welcomeLable = findViewById(R.id.welcomeLabel);
        Button signOut = findViewById(R.id.sign_out);

        String email = getIntent().getStringExtra("email");
        String result = getString(R.string.personalized_welcome, email);

        welcomeLable.setText(result);


        final SharedPreferences preferences = getSharedPreferences("Auth", MODE_PRIVATE);
        final Intent mainIntent = new Intent(this,MainActivity.class);

        View.OnClickListener signOutListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("remember", false);
                editor.apply();

                mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(mainIntent);
            }
        };
        signOut.setOnClickListener(signOutListener);
    }
}
