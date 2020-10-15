package com.example.activityex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnNewAct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnNewAct = findViewById(R.id.btnNewAct);
        btnNewAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // 여는 장소, 열고 싶은 activity ( 실행 파일은 class 니까 컴파일 된... 파일.. class?)
                Intent mintent = new Intent(getApplicationContext(),ResultActivity.class); // 호출만 된 상태
                startActivity(mintent); // 호출할 액티비티 start
            }
        });
    }
}