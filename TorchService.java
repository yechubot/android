package com.example.appwidget;

import android.app.Service;
import android.content.Intent;
import android.hardware.camera2.CameraAccessException;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class TorchService extends Service {
    Torch torch;
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
        if(intent.getAction().equals("on")){
            try {
                torch.flashOn();
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }else if(intent.getAction().equals("off")){
            try {
                torch.flashOff();
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }else {

        }
        return super.onStartCommand(intent, flags, startId);
    }
}
