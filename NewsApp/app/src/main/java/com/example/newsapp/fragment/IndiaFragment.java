package com.example.newsapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.newsapp.R;
import com.example.newsapp.adaptor.RecyclerAdapter;
import com.example.newsapp.databinding.FragmentIndiaBinding;
import com.example.newsapp.model.NewsData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class IndiaFragment extends Fragment {
    FragmentIndiaBinding binding;
    ArrayList<NewsData> newsDataList;
    RecyclerAdapter adapter;



    public IndiaFragment() {

    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = com.example.newsapp.databinding.FragmentIndiaBinding.inflate(inflater, container, false);
        newsDataList = new ArrayList<>();

        adapter = new RecyclerAdapter(newsDataList, getActivity());

        binding.recyclerView.setAdapter(adapter);
        fetchData();
        return binding.getRoot();
    }


    private void fetchData() {
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        String url = "https://newsapi.org/v2/top-headlines?country=in&apiKey=f5407908af22427ab06abb0f50678d17";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String status = response.getString("status");
                    Log.d("MainActivity", "onResponse: " + status);
                    if(status.equals("ok")) {
                        JSONArray jsonArray = response.getJSONArray("articles");
                        for(int i=0;i<jsonArray.length();i++) {
                            JSONObject newsObject = jsonArray.getJSONObject(i);
                            String title = newsObject.getString("title");
                            String content = newsObject.getString("description");
                            String url = newsObject.getString("url");
                            String imgUrl = newsObject.getString("urlToImage");
                            NewsData nd = new NewsData(title, content, imgUrl, url);

                            newsDataList.add(nd);
                        }
                        adapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("User-Agent", "PostmanRuntime/7.26.8");
                return headers;
            }
        };
        queue.add(jsonObjectRequest);
    }
}