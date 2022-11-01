package com.example.customlistview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.os.Bundle;

import java.util.List;
import java.util.Vector;
public class MainActivity extends AppCompatActivity {

    List<String> text = new Vector<>();
    ListView listview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview = findViewById(R.id.listview);
        text.add("First");
        text.add("Second");
        text.add("Third");
        text.add("Fourth");
        text.add("Fifth");
        text.add("Sixth");
        text.add("Seventh");
        text.add("Eighth");
        text.add("Ninth");
        text.add("Tenth");
        MineAdapter ad = new MineAdapter(this,R.layout.ilst_view_item,text);
        listview.setAdapter(ad);
        listview.setOnItemClickListener (new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent n = new Intent(MainActivity.this,clicked.class);
                startActivity(n);
            }
        });
    }
}