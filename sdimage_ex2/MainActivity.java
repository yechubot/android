package com.example.sdimage_ex2;

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
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btn_prev, btn_next, btn_grayscale;
    MyImageView iv_sdcardImg;
    TextView tv_imgOrder;
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
        btn_grayscale = findViewById(R.id.btn_grayscale);
        iv_sdcardImg = findViewById(R.id.iv_sdcardImg);
        tv_imgOrder = findViewById(R.id.tv_imgOrder);

        imgFileList = new ArrayList<File>(); //동적 배열은 선언하고 인스턴스까지 생성해야 함

        sd_path = Environment.getExternalStorageDirectory().getAbsolutePath();
        if (Build.VERSION.SDK_INT >= 23) {
            int permission_check = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

            if (permission_check == PackageManager.PERMISSION_DENIED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);
            } else { // permission 안된 경우
                sdcardImgFilter();
                tv_imgOrder.setText("이미지 " + (position + 1) + "/8"); // "1/"+imgFileList.size();
            }
        }

        btn_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position <= 0) {
                    //showToast("첫번째 그림입니다.");
                    position = imgFileList.size() - 1; //마지막 그림 보이게?
                } else {
                    position--;
                }
                imgName = imgFileList.get(position).toString();
                iv_sdcardImg.imagePath = imgName;
                iv_sdcardImg.invalidate();
                tv_imgOrder.setText("이미지 " + (position + 1) + "/8");

            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position >= imgFileList.size() - 1) {
                    //showToast("마지막 그림입니다.");
                    position = 0;
                } else {
                    position++;
                }
                imgName = imgFileList.get(position).toString();
                iv_sdcardImg.imagePath = imgName;
                iv_sdcardImg.invalidate();
                tv_imgOrder.setText("이미지 " + (position + 1) + "/8"); // position+1 +"/"+ imgFileList.size();
            }
        });

        btn_grayscale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt = btn_grayscale.getText().toString();
                if(txt.equals("흑백")){
                    iv_sdcardImg.saturation = 0;
                    iv_sdcardImg.invalidate(); //무효화 시킴 -> onDraw 메소드를 다시 호출하는 의미로 볼 수 있음
                    btn_grayscale.setText("컬러");
                }else {
                    iv_sdcardImg.saturation = 1;
                    iv_sdcardImg.invalidate();
                    btn_grayscale.setText("흑백");
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
            extName = fileName.substring(fileName.length() - 4);
            if (extName.equals(".png") || extName.equals(".jpg") || extName.equals(".gif")) { // 이름이 겹치는 경우 대비 점까지 확장자 표시해야 해서
                imgFileList.add(file);
            }
        }
        imgName = imgFileList.get(position).toString(); // imgFile[0]
        iv_sdcardImg.imagePath = imgName;
        iv_sdcardImg.invalidate(); //  ondraw를 호출한다? 즉 새 canvas를 받아서 그린다?
        tv_imgOrder.setText("이미지 " + (position + 1) + "/8");
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
