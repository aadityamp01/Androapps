package com.example.newsapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import com.example.newsapp.adaptor.ViewPagerAdapter;
import com.example.newsapp.fragment.IndiaFragment;
import com.example.newsapp.fragment.TechFragment;
import com.example.newsapp.fragment.USAFragment;
import com.example.newsapp.model.NewsData;
import com.example.newsapp.adaptor.RecyclerAdapter;
import com.example.newsapp.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    RecyclerAdapter adapter;
    ArrayList<NewsData> newsDataList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewPagerAdapter vpAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        vpAdapter.addFragment(new IndiaFragment(), "India");
        vpAdapter.addFragment(new USAFragment(), "USA");
        vpAdapter.addFragment(new TechFragment(),"Indonesia");

        binding.viewPager.setAdapter(vpAdapter);
        binding.tabLayout.setupWithViewPager(binding.viewPager);




    }
}