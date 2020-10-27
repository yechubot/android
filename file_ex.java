package com.example.file_ex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    Button btnSave, btnRead;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSave = findViewById(R.id.btnSave);
        btnRead = findViewById(R.id.btnRead);
        tvResult = findViewById(R.id.tvResult);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FileOutputStream fileOs = openFileOutput("test.txt", MODE_PRIVATE);//저장시 반드시 확장자 있어야함
                    String str = "hello \n 내용이 저장됩니다."; // \n: 줄바꿈
                    fileOs.write(str.getBytes());//저장명령 write()
                    fileOs.close(); // file open후 반드시 close 해야 한다.
                    showToast("text.txt가 저장되었습니다.");
                } catch (IOException e) { // file은 의무적으로 try, catch 요구
                    showToast("파일을 저장할 수 없습니다.");
                }
            }
        });
        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FileInputStream fileIs = openFileInput("test.txt");
                    byte txt[] = new byte[fileIs.available()];//읽어올 때도 바이트로, 크기 따로 주면 그 크기만큼만 잘려서 읽어옴
                    fileIs.read(txt);
                    String str = new String(txt); // 바이트 읽어들인 값을 스트링으로 str에 담음
                    tvResult.setText(str);
                    fileIs.close();// 꼭 닫기. 닫지 않으면 다음에 쓸 수 없음
                } catch (IOException e) {
                    showToast("파일을 찾을 수 없습니다.");
                }
            }
        });
    }

    //toast 메소드 만듦
    void showToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

}
