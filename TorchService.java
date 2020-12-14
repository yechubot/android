package com.example.appwidget;

import android.app.Service;
import android.content.Intent;
import android.hardware.camera2.CameraAccessException;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class TorchService extends Service {
    Torch torch;
    Boolean isRunning = false;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            torch = new Torch(this);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    //서비스 수행시 제일 먼저 수행되는 메소드
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(intent.getAction()=="on"){
            try {
                torch.flashOn();
                isRunning = true;
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }else if(intent.getAction()=="off"){
            try {
                torch.flashOff();
                isRunning = false;
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }else {
            isRunning =!isRunning; //값 반전시킴
            if(isRunning){
                try {
                    torch.flashOn();
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }
            }else {
                try {
                    torch.flashOff();
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }
            }

        }
        return super.onStartCommand(intent, flags, startId);
    }
}
