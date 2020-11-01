package com.example.sdcard_imgviewer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btn_prev, btn_next;
    ImageView iv_sdcardImg;
    String sd_path;
    File[] imgFile; // 여기서는 파일을 담는 용도로 사용
    String imgName;
    int position = 0; //기본값
    ArrayList<File> imgFileList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_next = findViewById(R.id.btn_next);
        btn_prev = findViewById(R.id.btn_prev);
        iv_sdcardImg = findViewById(R.id.iv_sdcardImg);

        imgFileList = new ArrayList<File>(); //동적 배열은 선언하고 인스턴스까지 생성해야 함

        sd_path = Environment.getExternalStorageDirectory().getAbsolutePath();
        if (Build.VERSION.SDK_INT >= 23) {
            int permission_check = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

            if (permission_check == PackageManager.PERMISSION_DENIED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);
            } else {
                sdcardImgFilter();
            }
        }

        btn_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position <= 0) {
                    showToast("첫번째 그림입니다.");
                } else {
                    position--;
                    imgName = imgFileList.get(position).toString();
                    Bitmap bm = BitmapFactory.decodeFile(imgName);
                    iv_sdcardImg.setImageBitmap(bm);
                }
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position >= imgFileList.size() - 1) {
                    showToast("마지막 그림입니다.");
                } else {
                    position++;
                    imgName = imgFileList.get(position).toString();
                    Bitmap bm = BitmapFactory.decodeFile(imgName);
                    iv_sdcardImg.setImageBitmap(bm);
                }
            }
        });
    }

    void showToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    void sdcardImgFilter() { // 순수하게 이미지만 담기.
        imgFile = new File(sd_path + "/Pictures").listFiles(); // 해당 폴더의 파일 목록을 넣음
        String fileName, extName;
        for (File file : imgFile) {
            fileName = file.getName();
            extName = fileName.substring(fileName.length() -4);
            if (extName.equals(".png") || extName.equals(".jpg") || extName.equals(".gif")) { // 이름이 겹치는 경우 대비 점까지 확장자 표시해야 해서
                imgFileList.add(file);
            }
        }
        imgName = imgFileList.get(position).toString(); // imgFile[0]
        Bitmap bm = BitmapFactory.decodeFile(imgName);
        iv_sdcardImg.setImageBitmap(bm);
    }

    @Override
    // 퍼미션 결과를 받는 메소드                                                            //퍼미션 결과 받는 변수
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
            finish();
        } else {
            //허용한 경우
            sdcardImgFilter();
        }
    }
}
