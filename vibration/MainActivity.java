package com.example.noti;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button btn1,btn2,btn3,btn4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = findViewById(R.id.btnVib1);
        btn2 = findViewById(R.id.btnVib2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnVib1:
                Vibrator vib1 = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                vib1.vibrate(1000);
                break;
            case R.id.btnVib2:
                Vibrator vib2 = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                vib2.vibrate(new long[] {500,1000,300,1000},-1);
                                //대기,진동, 대기, 진동.. -1:반복안함
                //vib2.cancel
                break;
            case R.id.btn3:

                break;
            case R.id.btn4:
                Uri noti = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                Ringtone ringtone = RingtoneManager.getRingtone(getApplicationContext(),noti);
                ringtone.play();
                break;

        }
    }
}