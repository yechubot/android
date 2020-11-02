package com.example.splash_activity_ex1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Handler hd = new Handler(); //인스턴스 생성
        hd.postDelayed(new Runnable() {// 있는 메소드를 생성하여
            @Override
            public void run() {
                finish();
            }
        },3000);// 3초 뒤에 실행해줌
    }
}