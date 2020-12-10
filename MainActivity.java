package com.example.cameraexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;

import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

public class MainActivity extends AppCompatActivity implements AutoPermissionsListener {
    Button btnPhoto;
    FrameLayout frameLayout;
    CamView camView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnPhoto = findViewById(R.id.btnPhoto);
        frameLayout = findViewById(R.id.framePreview);
        AutoPermissions.Companion.loadAllPermissions(this,101);
        camView = new CamView(this);
        frameLayout.addView(camView);
        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePic();
            }
        });
    }
    public void takePic(){
        camView.capture(new Camera.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] data, Camera camera) {
                try {
                    Bitmap bitmap = BitmapFactory.decodeByteArray(data,0,data.length);
                    String outUriString = MediaStore.Images.Media.insertImage(getContentResolver(),
                            bitmap,"captured img","captured img by camera");
                    if(outUriString==null){
                        //not saved
                        return;
                    }else {
                        Uri outUri = Uri.parse(outUriString);
                        sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,outUri));
                        camera.startPreview();
                    }
                }catch(Exception E){
                    //no preview
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        AutoPermissions.Companion.parsePermissions(this, requestCode,permissions,this);
    }

    @Override
    public void onDenied(int i, String[] strings) {

    }

    @Override
    public void onGranted(int i, String[] strings) {

    }

    class CamView extends SurfaceView implements SurfaceHolder.Callback {
        SurfaceHolder mHolder;
        Camera camera = null;

        public CamView(Context context) {
            super(context);
            mHolder = getHolder();
            mHolder.addCallback(this);
        }

        @Override
        public void surfaceCreated(@NonNull SurfaceHolder holder) {
            setCameraOrientation();
            try {
                camera = Camera.open();
                camera.setPreviewDisplay(mHolder);
            } catch (Exception e) {
                //error
            }
        }

        public void setCameraOrientation(){
            if(camera==null){
                return;
            }
            Camera.CameraInfo info = new Camera.CameraInfo();
            Camera.getCameraInfo(0,info);
            WindowManager manager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
            int rotation = manager.getDefaultDisplay().getRotation();
            int degrees = 0;
            switch (rotation){
                case Surface.ROTATION_0: degrees = 0;
                    break;
                case Surface.ROTATION_90: degrees = 90;
                    break;
                case Surface.ROTATION_180: degrees = 180;
                    break;
                case Surface.ROTATION_270: degrees = 270;
                    break;
            }
            int result;
            if(info.facing==Camera.CameraInfo.CAMERA_FACING_FRONT){
                result=(info.orientation+degrees)%360;
                result=(360-result)%360;
            }else {
                result = (info.orientation-degrees+360)%360;
            }
            camera.setDisplayOrientation(result);
        }
        @Override
        public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
            camera.startPreview();
        }

        @Override
        public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
            camera.stopPreview();
            camera.release();
            camera = null;

        }
        public boolean capture(Camera.PictureCallback callback) {
            if (camera != null) {
                camera.takePicture(null, null, callback);
                return true;
            } else {
                return false;
            }
        }
    }
}