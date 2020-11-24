package com.example.mediaex;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {
    Switch swPlay;
    MediaPlayer mPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        swPlay = findViewById(R.id.swPlay);
        mPlayer = MediaPlayer.create(this,R.raw.you);
        swPlay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(swPlay.isChecked()){
                    mPlayer.start();
                }else{
                    mPlayer.stop();//멈추고 다시 켜도 노래 안들림 다시 불러와야함
                }
            }
        });
    }
}