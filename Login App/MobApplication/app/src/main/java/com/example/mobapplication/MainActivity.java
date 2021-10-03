package com.example.mobapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

public class MainActivity extends AppCompatActivity {
    EditText email,password,name;
    CheckBox verify, rememberMe;
    //public static String EMAIL_VALUE = "";
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main); Can use to get hello world activity
        setContentView(R.layout.acitvity_mn);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        verify = findViewById(R.id.verify);
        rememberMe = findViewById(R.id.remember_me);

        preferences = getSharedPreferences("Auth",MODE_PRIVATE);

        if(preferences.getBoolean("remember",true)){
            Intent intent = new Intent(this,WelcomeActivity.class);
            String email = preferences.getString("email", "DEFAULT");
            intent.putExtra("email",email);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }
    public void onLOGIN(View view){
        String nameEntered = name.getText().toString();
        String emailEntered = email.getText().toString();
        String passwordEntered = password.getText().toString();
        //EMAIL_VALUE = emailEntered;

        if(TextUtils.isEmpty(nameEntered) || TextUtils.isEmpty(emailEntered) || TextUtils.isEmpty(passwordEntered)){
            Toasty.error(this,getString(R.string.complete_all_fields),Toast.LENGTH_SHORT).show();
            
        }else{
            if(verify.isChecked()) {
                Intent intent = new Intent(this, WelcomeActivity.class);
                intent.putExtra("email", emailEntered);



                Toasty.success(this,getString(R.string.entered_details, emailEntered, passwordEntered), Toast.LENGTH_SHORT).show();
                // Shared preferences
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("name",nameEntered);
                editor.putString("email",emailEntered);
                editor.putString("password",passwordEntered);


                editor.putBoolean("remember", rememberMe.isChecked());
                editor.apply();
                //intent.putExtra("age", 80);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
            else{
                Toasty.warning(this,getString(R.string.verify_checkbox), Toast.LENGTH_SHORT).show();
            }
        }
    }
}