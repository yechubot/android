package com.example.myimageviexex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.EmbossMaskFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyImageView(this));
    }

    private static class MyImageView extends View {

        public MyImageView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Paint paint = new Paint();
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.cat);
            //가운데 오게 하기
            int picX=(this.getWidth()-bitmap.getWidth())/2;
            int picY =(this.getHeight()-bitmap.getHeight())/2;
            int centX = this.getWidth()/2;
            int centY = this.getHeight()/2;

            //회전
            // canvas.rotate(45,centX,centY);//중심정 잡고 회전 ! 0 인 경우 0.0 기준으로 회전해서 벗어난다.
            //확대
            //canvas.scale(2,2,centX,centY);//scale 1이 기본
            //이동
           // canvas.translate(-150,200);
            //비틀기
           // canvas.skew(0.3f,0.3f);

            //그리기
            //블러는 버전에 따라 되기도, 안되기도 함->하드웨어 가속기 꺼야 되는 경우 있음
           /* BlurMaskFilter bMask;
            bMask = new BlurMaskFilter(100, BlurMaskFilter.Blur.NORMAL);//(블러링 크기, 스타일)
            paint.setMaskFilter(bMask);*/
           //엠보싱
            /*EmbossMaskFilter eMask;
            eMask = new EmbossMaskFilter(new float[]{3,3,3},0.5f,5,20 );//빛 방향(배열), 빛 밝기(0-1), 반사계수,(5-8) 엠보싱 크기
            paint.setMaskFilter(eMask);*/
            canvas.drawBitmap(bitmap,picX,picY,paint);// paint가 null이면 이미지 그대로 가져옴(원래 이미지 뷰 처럼)
            bitmap.recycle();//draw후 비트맵 리소스 해제
        }
    }
}
