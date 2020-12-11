package com.example.appwidget;

import android.content.Context;
import android.content.Intent;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.widget.Toast;
/*
 * main activity -> torch class 호출
 * main activity 에 app widegt 생성 -> service에 넘김
 * service -> torch class 호출
 * */

public class Torch {
    Context context;
    String cameraID;
    CameraManager cameraManager;

    public Torch(Context context) throws CameraAccessException { //카메라 접근 못할 때
        this.context = context;
        cameraManager = (CameraManager) context.getSystemService(Context.CAMERA_SERVICE);
       cameraID = getCameraID(); // null값 리턴 받으면 카메라 못씀

    }
    public String getCameraID() throws  CameraAccessException{
        String cameraIDs[] = cameraManager.getCameraIdList();
        for(String id: cameraIDs){
            CameraCharacteristics info = cameraManager.getCameraCharacteristics(id);
            Boolean flashAvailable = info.get(CameraCharacteristics.FLASH_INFO_AVAILABLE);
            Integer lensFacing = info.get(CameraCharacteristics.LENS_FACING);

            if(
                    flashAvailable != null && flashAvailable && lensFacing !=null &&
                    lensFacing == CameraCharacteristics.LENS_FACING_BACK)
            {
                return id;
            }
        }
        return  null;
    }

    public void flashOn() throws CameraAccessException{
        cameraManager.setTorchMode(cameraID,true);
    }
    public void flashOff() throws CameraAccessException{
        cameraManager.setTorchMode(cameraID,false);
    }

}
