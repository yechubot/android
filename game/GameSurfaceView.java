package com.example.gameviewex;

import android.content.Context;
import android.hardware.Camera;
import android.os.Build;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.io.IOException;

public class GameSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    SurfaceHolder mHolder;
    Camera camera = null;
    int surfaceWidth;
    int surfaceHeight;

    public GameSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mHolder = getHolder();
        mHolder.addCallback(this);
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        camera = Camera.open();
        try {
            camera.setPreviewDisplay(mHolder);
        } catch (Exception e) {
            showToast("카메라 사용 불가");
        }
    }

    void showToast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
        surfaceHeight = height;
        surfaceWidth = width;
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {
                camera.setDisplayOrientation(90);
            } else {
                Camera.Parameters parameter = camera.getParameters();
                parameter.setRotation(90);
            }
            camera.setPreviewDisplay(holder);
        } catch (IOException e) {
            camera.release();
        }
        camera.startPreview();
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        camera.stopPreview();
        camera.setPreviewCallback(null);
        camera.release();
        camera = null;
    }
}
