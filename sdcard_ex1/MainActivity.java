package com.example.sdcard_ex1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    Button btn_sdCardRead, btn_mkdir, btn_delete;
    TextView tvContent;
    String sd_path;
    File folder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_sdCardRead = findViewById(R.id.btn_sdCardRead);
        btn_delete = findViewById(R.id.btn_delete);
        btn_mkdir = findViewById(R.id.btn_mkdir);
        tvContent = findViewById(R.id.tvContent);
        //폰 환경에서 절대주소 가져오기
        sd_path = Environment.getExternalStorageDirectory().getAbsolutePath();
        // 만들 폴더 객체 생성
        folder = new File(sd_path + "/new_one");
        // 앱 실행시권한 묻기
        if (Build.VERSION.SDK_INT >= 23) {
            int permission_check = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            //if(permission_check!== PackageManager.PERMISSION_GRANTED){
            if (permission_check == PackageManager.PERMISSION_DENIED) {
                ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);
            }
        }
        btn_mkdir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //생성한 객체의 폴더 만들기
                folder.mkdir();
            }
        });
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                folder.delete();
            }
        });
        btn_sdCardRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    //FileInputStream fileInputStream = new FileInputStream("sdcard/sdtest.txt");
                    //"storage/emulated/0/sdtest.txt"
                    FileInputStream fileInputStream = new FileInputStream(sd_path + "/sdtest.txt");
                    byte txt[] = new byte[fileInputStream.available()];
                    fileInputStream.read(txt);
                    tvContent.setText(new String(txt));
                    fileInputStream.close();
                } catch (IOException e) {
                    //읽어올 수 없음
                }

            }
        });
    }
}
