package com.example.lifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnNewAct, btnEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //로그에서 수명주기 기록을 보기
        android.util.Log.i("테스트", "onCreate 메서드 수행");// i는 정보 남길때 씀
        btnEnd = findViewById(R.id.btnEnd);
        btnNewAct = findViewById(R.id.btnNewAct);

        btnNewAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NewAct.class);
                startActivity(intent);
            }
        });
        btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        android.util.Log.i("테스트", "onStart 메서드 수행");
    }

    @Override
    protected void onResume() {//액티비티 뜨기 바로 전에 수행
        super.onResume();
        android.util.Log.i("테스트", "onResume 메서드 수행");
    }

    @Override
    protected void onPause() {
        super.onPause();
        android.util.Log.i("테스트", "onPause 메서드 수행");
    }

    @Override
    protected void onStop() {
        super.onStop();
        android.util.Log.i("테스트", "onStop 메서드 수행");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        android.util.Log.i("테스트", "onRestart 메서드 수행");
    }

    @Override
    protected void onDestroy() {// 스레드 이용시 interrupt해서 사용하기도 한다.
        super.onDestroy();
        android.util.Log.i("테스트", "onDestroy 메서드 수행");
    }
}