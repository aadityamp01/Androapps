package com.example.pedometer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.pedometer.data.MyDatabase;
import com.example.pedometer.service.StepListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {
    private static final int SENSOR_CODE=567;
    public static float STEPS=0;
    private int[] upperBounds={50,100,200,250,500,1000,2000,2500,3000,5000,10000};

    ListView listView;
    TextView stepsView;
    TextView distanceInMetres;
    TextView caloriesBurnt;
    ProgressBar pBar;

    MyDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Check whether permission is already granted if not ask for it
        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACTIVITY_RECOGNITION) == PackageManager.PERMISSION_DENIED){
            requestPermissions(new String[]{Manifest.permission.ACTIVITY_RECOGNITION}, SENSOR_CODE);
        }

        listView = findViewById(R.id.listView);
        stepsView=findViewById(R.id.stepsView);
        distanceInMetres=findViewById(R.id.inM);
        caloriesBurnt=findViewById(R.id.calories);
        pBar=findViewById(R.id.progress);

        db=new MyDatabase(this);

        listView.setAdapter(db.getMyListAdapter());

        Intent StepsIntent = new Intent(getApplicationContext(), StepListener.class);
        startService(StepsIntent);

        update();

        write();

    }

    public void update(){
        final Handler handler=new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                updateValues();

                //set steps to 0 at start of the day
                SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss");
                sdf.setTimeZone(TimeZone.getTimeZone("IST"));
                String timeNow=sdf.format(new Date());
                if(timeNow.equals("00:00:00")||timeNow.equals("00:00:01")||timeNow.equals("00:00:02")){
                    STEPS=0;
                }

                handler.postDelayed(this,1000);
            }
        });

    }
    public void write(){
        final Handler writeHandler=new Handler();
        writeHandler.post(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat sdf=new SimpleDateFormat("E,d MMM YYYY");
                sdf.setTimeZone(TimeZone.getTimeZone("IST"));
                String dateNow=sdf.format(new Date());
                //String datenow=new SimpleDateFormat("E,d MM YYYY").format(Calendar.getInstance().getTime());
                db.writeTo(dateNow,(int)(STEPS*0.762),(float)((int)(STEPS*0.762)*0.76));

                writeHandler.postDelayed(this,3600000);
            }
        });
    }
    public void updateValues(){
        stepsView.setText(STEPS+"\n STEPS");

        int m= (int) (STEPS*0.762);
        distanceInMetres.setText(m+"\n Metres");

        float caloriesc= (float) (m*0.76);
        caloriesBurnt.setText(caloriesc+"\n Calories Burnt");

        //Set it from array
        for(int i:upperBounds){
            if(STEPS<i){
                pBar.setMax(i);
                break;
            }
        }
        pBar.setProgress((int) STEPS);

    }

    @Override
    protected void onPause() {
        super.onPause();
        write();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        STEPS=0;
    }
}
