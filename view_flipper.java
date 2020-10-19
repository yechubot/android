package com.example.viewflipperexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {
    Button btnPrev, btnNext;
    ViewFlipper vFlipper1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //시작누르면 자동으로 사진 넘어감, 중지 누르면 멈춤
        //이미지 각 설명 달기
        setContentView(R.layout.activity_main);
        btnPrev = findViewById(R.id.btnPrev);
        btnNext = findViewById(R.id.btnNext);
        vFlipper1 = findViewById(R.id.vFlipper1);
        vFlipper1.setFlipInterval(2000);

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                vFlipper1.showPrevious(); // 이전것 보여줘 -> 이전 것 없으면 마지막게 이전 것됨
                vFlipper1.startFlipping();

            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                vFlipper1.showNext();
                vFlipper1.stopFlipping();
            }
        });
    }
}
