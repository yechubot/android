package com.example.appwidget;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.camera2.CameraAccessException;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

/*
 * 손전등 기능의 클래스
 * 앱 위젯 담당 서비스 클래스
 * 메인에서 연결하는 클래스
 * */
public class MainActivity extends AppCompatActivity {
    Switch swTorch;
   // Torch torch;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        swTorch = findViewById(R.id.swTorch);
        intent = new Intent(MainActivity.this,TorchService.class);
//        try {
//            torch = new Torch(this);
//        } catch (CameraAccessException e) {
//            showToast("플래시 기능 사용 불가");
//        }
        swTorch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
//                    try {
//                        torch.flashOn();
//                    } catch (CameraAccessException e) {
//                        e.printStackTrace();
//                    }
                    intent.setAction("on");

                } else {
//                    try {
//                        torch.flashOff();
//                    } catch (CameraAccessException e) {
//                        e.printStackTrace();
//                    }
                    intent.setAction("off");
                }
                startService(intent);
            }
        });
    }
    void showToast(String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }
}