package com.example.animationex;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class GameView extends SurfaceView {
    ImgThread thread;

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {

        thread.setX((int)event.getX());
        thread.setY((int)event.getY());
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==event.KEYCODE_DPAD_LEFT){
            thread.setXPos(-10);
        }else if(keyCode==event.KEYCODE_DPAD_RIGHT){
            thread.setXPos(10);
        }else if(keyCode==event.KEYCODE_DPAD_UP){
            thread.setYPos(-10);
        }else if(keyCode==event.KEYCODE_DPAD_DOWN){
            thread.setYPos(10);
        }
        return super.onKeyDown(keyCode, event);
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        SurfaceHolder holder = getHolder();
        thread = new ImgThread(holder,context);
        setFocusable(true);
        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(@NonNull SurfaceHolder holder) {
                thread.start();
            }

            @Override
            public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
                boolean retry = true;
                while(retry){
                    try {
                        thread.join();
                        retry = false;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
    }

    class ImgThread extends Thread{
        Bitmap bitmap;
        SurfaceHolder surfaceHolder;
        Drawable manImg[] = new Drawable[2]; // img 2개
        int cnt =0;
        int xPos =0, yPos =0;

        public ImgThread(SurfaceHolder surfaceHolder,Context context){
            this.surfaceHolder = surfaceHolder;
            Resources res = context.getResources();
            bitmap = BitmapFactory.decodeResource(res,R.drawable.oreo);
            manImg[0] = res.getDrawable(R.drawable.man1);
            manImg[1] = res.getDrawable(R.drawable.man2);
        }

        @Override
        public void run() {
            while(true){
                Canvas canvas = null;
                try{
                    canvas = surfaceHolder.lockCanvas(null);
                    synchronized (surfaceHolder){ //draw
                        canvas.drawBitmap(bitmap,0,0,null);
                        cnt++;
                        manImg[cnt%2].setBounds(120+xPos,300+yPos,220+xPos,500+yPos);
                        manImg[cnt%2].draw(canvas);
                        sleep(100);
                    }
                }catch(InterruptedException e){

                }finally {//show
                        if(canvas!=null){
                            surfaceHolder.unlockCanvasAndPost(canvas);
                        }
                    }
                }
            }
            void setXPos(int val){
            xPos+=val;
        }
        void setYPos(int val){
            yPos+=val;
        }
        void setX(int val){
            xPos = val-100;
        }
        void setY(int val){
            yPos = val-200;
        }

    }
}
