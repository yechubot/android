package com.example.sharedpreex;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText edtInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtInput = findViewById(R.id.edtInput);
    }
    //작성한 내용이 남게 만들 때 쓰거나..처음이후 실행에 계쏙 데이터가 저장되어 있게 만들때..?
    //복원하려고 할 때 쓰거나..
    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences preferences = getSharedPreferences("pref", Activity.MODE_PRIVATE);// APPEND는 추가, PRIVATE은 계쏙 새롭게 만듦
        SharedPreferences.Editor editor = preferences.edit();//저장할 변수가 editor
        editor.putString("mykey", edtInput.getText().toString());//담을 키 이름
        editor.commit(); //호출해야 실제로 저장됨
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences preferences = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        if(preferences != null && preferences.contains("mykey")){// null일 때는 가져올 필요 없음
            edtInput.setText(preferences.getString("mykey",""));
        }
    }
    //clear 하고 싶을 때??
    protected void clearState() {
        SharedPreferences preferences = getSharedPreferences("pref", Activity.MODE_PRIVATE);// APPEND는 추가, PRIVATE은 계쏙 새롭게 만듦
        SharedPreferences.Editor editor = preferences.edit();//저장할 변수가 editor
        editor.clear();//
        editor.commit();//해야 저장됨
    }
}
