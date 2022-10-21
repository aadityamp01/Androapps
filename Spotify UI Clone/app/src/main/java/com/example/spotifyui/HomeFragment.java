package com.example.spotifyui;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.Toast;


public class HomeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ScrollView scrollView_vertical;
        HorizontalScrollView horizontalScrollView;
        HorizontalScrollView horizontalScrollView1;
        ImageButton img_btn_settings;
        CardView card_small_1, card_small_2, card_small_3, card_small_4, card_small_5, card_small_6;


        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        scrollView_vertical = v.findViewById(R.id.scroll_view_vertical);
        horizontalScrollView = v.findViewById(R.id.horizontal_scroll);
        horizontalScrollView1 = v.findViewById(R.id.horizontal_scroll_1);
        img_btn_settings = v.findViewById(R.id.img_btn_settings);
        card_small_1 = v.findViewById(R.id.card_small_1);
        card_small_2 = v.findViewById(R.id.card_small_2);
        card_small_3 = v.findViewById(R.id.card_small_3);
        card_small_4 = v.findViewById(R.id.card_small_4);
        card_small_5 = v.findViewById(R.id.card_small_5);
        card_small_6 = v.findViewById(R.id.card_small_6);

        horizontalScrollView.setHorizontalScrollBarEnabled(false);
        horizontalScrollView1.setHorizontalScrollBarEnabled(false);
        scrollView_vertical.setVerticalScrollBarEnabled(false);


        img_btn_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Settings", Toast.LENGTH_SHORT).show();
            }
        });

        card_small_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new PlaylistFragment())
                        .commit();
            }
        });


        card_small_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new PlaylistFragment())
                        .commit();
            }
        });
        card_small_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new PlaylistFragment())
                        .commit();
            }
        });
        card_small_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new PlaylistFragment())
                        .commit();
            }
        });
        card_small_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new PlaylistFragment())
                        .commit();
            }
        });
        card_small_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new PlaylistFragment())
                        .commit();
            }
        });

        return v;
    }




}