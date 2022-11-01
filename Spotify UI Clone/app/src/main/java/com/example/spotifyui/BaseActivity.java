package com.example.spotifyui;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    /**
     * Initialize action attributes
     */
    protected abstract void init();

    /**
     * Contains display login of activity
     */
    protected abstract void display();


    /**
     * Contains submit logic of activity
     */
    protected abstract void submit();

    /**
     * returns vew of activity
     */
    protected abstract int getView();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getView());
        init();
        submit();
        display();
    }
}
