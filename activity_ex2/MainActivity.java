package com.example.activity_ex2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {
    Button btnNewAct;
    RadioButton rdoSecond, rdoThird;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnNewAct = findViewById(R.id.btnNewAct);
        rdoSecond = findViewById(R.id.rdoSecond);
        rdoThird = findViewById(R.id.rdoThird);
/*        btnNewAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //activity 관련 일을 함
                Intent intent = new Intent(getApplicationContext(), SecondActivity.class); //띄울 activity 정보를 intent에 넣음
                startActivity(intent);// 띄울 activity를 start함
            }
        });*/

        //열기 클릭(라디오 버튼 찍힌 상태) -> 2번째 액티비티 열림, 3번째 찍고 버튼 누르면 3번째 액티비티 호출,
        btnNewAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                if(rdoSecond.isChecked()){
                     intent = new Intent(getApplicationContext(),SecondActivity.class);
                }else {
                     intent = new Intent(getApplicationContext(),ThirdActivity.class);
                }
                startActivity(intent);
            }
        });

    }
}
