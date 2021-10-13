package com.example.musicplayerapp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    String[] items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

     //   listView = (ListView)findViewById(R.id.listView);
        runtimePermission();
    }

    public void runtimePermission(){

        Dexter.withContext(this).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {

                        displaySong();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {

                        permissionToken.continuePermissionRequest();
                    }
                }).check();
    }

    public ArrayList<File> findSong(File file){

        ArrayList<File> arrayList= new ArrayList<>();
        File[] files = file.listFiles();
        for(File f: Objects.requireNonNull(files)){
            if(f.isDirectory() && !f.isHidden()){
                arrayList.addAll(findSong(f));
            }
            else{
                if(f.getName().endsWith(".mp3") || f.getName().endsWith(".wav")){
                    arrayList.add(f);
                }
            }
        }
        return arrayList;
    }

    public void  displaySong(){

        final ArrayList<File> mySong= findSong(Environment.getExternalStorageDirectory());
        items= new String[mySong.size()];
        for(int i=0;i<mySong.size();i++){

            items[i]= mySong.get(i).getName().replace(".mp3", "").replace(".wav", "");

        }
        customAdapter custmAdapter = new customAdapter();
        listView.setAdapter(custmAdapter);

        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            String songName= (String) listView.getItemAtPosition(i);
            startActivity(new Intent(getApplicationContext(), PlayActivtiy.class)
                    .putExtra("songs",mySong)
                    .putExtra("songname",songName)
                    .putExtra("pos",i)
                    );
        });
    }

    class customAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return items.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            @SuppressLint({"ViewHolder", "InflateParams"}) View view1 = getLayoutInflater().inflate(R.layout.items_list,null);
            TextView textSong = view.findViewById(R.id.textView);
            textSong.setSelected(true);
            textSong.setText(items[i]);
            return view1;
        }
    }
}