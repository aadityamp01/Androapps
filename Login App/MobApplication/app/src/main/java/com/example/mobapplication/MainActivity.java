package com.example.mobapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText email,password,name;
    CheckBox verify;
    //public static String EMAIL_VALUE = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main); Can use to get hello world activity
        setContentView(R.layout.acitvity_mn);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        verify = findViewById(R.id.verify);

    }
    public void onLOGIN(View view){
        String nameEntered = name.getText().toString();
        String emailEntered = email.getText().toString();
        String passwordEntered = password.getText().toString();
        //EMAIL_VALUE = emailEntered;

        if(TextUtils.isEmpty(nameEntered) || TextUtils.isEmpty(emailEntered) || TextUtils.isEmpty(passwordEntered)){
            Toast.makeText(this,"Complete all fields",Toast.LENGTH_SHORT).show();
        }else{
            if(verify.isChecked()) {
                Intent intent = new Intent(this, WelcomeActivity.class);
                intent.putExtra("email", emailEntered);

                Toast.makeText(this, "Email Entered: " + emailEntered + "\nPassword Entered: " + passwordEntered, Toast.LENGTH_SHORT).show();


                //intent.putExtra("age", 80);
                startActivity(intent);
            }
            else{
                Toast.makeText(this,"Verify checkbox",Toast.LENGTH_SHORT).show();
            }
        }
    }
}