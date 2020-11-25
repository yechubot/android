package com.example.threadex1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SeekBar sb1,sb2;
    Button btnStart;
    TextView tv1,tv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sb2 = findViewById(R.id.sb2);
        sb1 = findViewById(R.id.sb1);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        btnStart = findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //스레드 없을 때
                //버튼이 눌린 상태에서 한번에 와버림 ,동시에 수행 못해서 멈춰있음
                //포문 돌다가 100이 되면 위치에 와버림

                new Thread(){
                    @Override
                    public void run() {
                        for(int i=0; i<100; i++){
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    sb1.setProgress(sb1.getProgress()+2);
                                    tv1.setText("1번 진행률 : "+sb1.getProgress()+"%");
                                }
                            });

                            SystemClock.sleep(100);
                        }
                    }
                }.start();

                new Thread(){
                    @Override
                    public void run() {
                        for(int i=0; i<100; i++){
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    sb2.setProgress(sb2.getProgress()+1);
                                    tv2.setText("2번 진행률: "+sb2.getProgress()+"%");
                                }
                            });

                            SystemClock.sleep(100);
                        }
                    }
                }.start();

            }
        });
    }
}
