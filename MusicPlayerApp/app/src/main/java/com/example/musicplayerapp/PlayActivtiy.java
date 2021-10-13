package com.example.musicplayerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

public class PlayActivtiy extends AppCompatActivity {

    Button playBtn,nextBtn, backBtn, forwardBtn,rewindBtn;
    SeekBar seekMusicBar;
    TextView txtSongName, txtSongStart, txtSongEnd;
    ImageView imgView;
    String songName;
    static MediaPlayer mediaPlayer;
    int pos;
    ArrayList<File> mySongs;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()== android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    Thread updateSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_activtiy);

        getSupportActionBar().setTitle("Music Player");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        playBtn= findViewById(R.id.play);
        nextBtn= findViewById(R.id.next);
        backBtn= findViewById(R.id.back);
        forwardBtn=findViewById(R.id.fastForward);
        rewindBtn=findViewById(R.id.rewind);

        txtSongName=findViewById(R.id.txtSong);
        txtSongStart=findViewById(R.id.txtStart);
        txtSongEnd=findViewById(R.id.txtEnd);
        seekMusicBar=findViewById(R.id.seekBar);
        imgView=findViewById(R.id.musicIcon);

        if(mediaPlayer != null){
            mediaPlayer.start();
            mediaPlayer.release();
        }
        Intent intent= getIntent();
        Bundle bundle= intent.getExtras();

        mySongs=(ArrayList)bundle.getParcelableArrayList("songs");
        pos= bundle.getInt("position",0);
        txtSongName.setSelected(true);
        Uri uri = Uri.parse(mySongs.get(pos).toString());
        songName= mySongs.get(pos).toString();
        txtSongName.setText(songName);

        mediaPlayer = MediaPlayer.create(getApplicationContext(),uri);
        mediaPlayer.start();

        updateSeekBar= new Thread(() -> {
            int totalDuration = mediaPlayer.getDuration();
            int currPos= 0;
            while (currPos<totalDuration){
                try {
                    Thread.sleep(500);
                    currPos= mediaPlayer.getCurrentPosition();
                    seekMusicBar.setProgress(currPos);

                }
                catch (InterruptedException | IllegalStateException e){
                    e.printStackTrace();
                }
            }

        });
        seekMusicBar.setMax(mediaPlayer.getDuration());
        updateSeekBar.start();
        seekMusicBar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.White), PorterDuff.Mode.MULTIPLY);
        seekMusicBar.getThumb().setColorFilter(getResources().getColor(R.color.White),PorterDuff.Mode.SRC_IN);

        seekMusicBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });

        String endTime = createTime(mediaPlayer.getDuration());
        txtSongEnd.setText(endTime);

        final Handler handler= new Handler();
        final int delay= 1000;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                String currTime = createTime(mediaPlayer.getCurrentPosition());
                txtSongStart.setText(currTime);
                handler.postDelayed(this, delay);

            }
        },delay);

        playBtn.setOnClickListener(view -> {

            if (mediaPlayer.isPlaying()){
                playBtn.setBackgroundResource(R.drawable.ic_play_circle);
                mediaPlayer.pause();
            }
            else{
                playBtn.setBackgroundResource(R.drawable.ic_pause_circle_24);
                mediaPlayer.start();
            }
        });

        nextBtn.setOnClickListener(view -> {

            mediaPlayer.stop();
            mediaPlayer.release();
            pos= ((pos+1)%mySongs.size());
            Uri uri1 = Uri.parse(mySongs.get(pos).toString());
            mediaPlayer = MediaPlayer.create(getApplicationContext(),uri1);
            songName= mySongs.get(pos).getName();
            txtSongName.setText(songName);
            mediaPlayer.start();
            startAnimation(360f);
        });

        mediaPlayer.setOnCompletionListener(mediaPlayer -> nextBtn.performClick());

        backBtn.setOnClickListener(view -> {

            mediaPlayer.stop();
            mediaPlayer.release();
            pos= ((pos-1)<0)?(mySongs.size()-1):pos-1;
            Uri uri1 = Uri.parse(mySongs.get(pos).toString());
            mediaPlayer = MediaPlayer.create(getApplicationContext(),uri1);
            songName= mySongs.get(pos).getName();
            txtSongName.setText(songName);
            mediaPlayer.start();
            startAnimation(-360f);
        });

        forwardBtn.setOnClickListener(view -> {

            if (mediaPlayer.isPlaying()){
                 mediaPlayer.seekTo(mediaPlayer.getCurrentPosition()+10000);
            }
        });

        rewindBtn.setOnClickListener(view -> {
            if (mediaPlayer.isPlaying()){
                mediaPlayer.seekTo(mediaPlayer.getCurrentPosition()-10000);
            }
        });
    }

    public void startAnimation(float degree){

        ObjectAnimator objectAnimator= ObjectAnimator.ofFloat(imgView, "rotation",0f,degree);
        AnimatorSet animatorSet= new AnimatorSet();
        animatorSet.playTogether(objectAnimator);
        animatorSet.start();
    }
    public String createTime(int duration){
        String time="";
        int min=duration/1000/60;
        int sec=duration/1000/60;
        time= time+min+":";
        if(sec<10){
            time+="0";
        }
        time+=sec;
        return time;

    }
}