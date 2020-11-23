package com.example.miniphotoshop;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.EmbossMaskFilter;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class MyGraphicView extends View {
    float scaleX = 1, scaleY = 1;
    float angle = 0;
    float color = 1;
    float sat = 1;
    boolean blur=false, embos = false;
    public MyGraphicView(Context context, @Nullable AttributeSet attrs) {//외부에서 만들 때 속성도 설정
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.rabbit);
        int picX = (this.getWidth() - bitmap.getWidth()) / 2;
        int picY = (this.getHeight() - bitmap.getHeight()) / 2;
        int centerX = this.getWidth() / 2;
        int centerY = this.getHeight() / 2;
        canvas.scale(scaleX, scaleY, centerX, centerY);//확대, 축소
        canvas.rotate(angle, centerX, centerY);//회전
/*        float array[]={
                color,0,0,0,0, //r
                0,color,0,0,0, //g
                0,0,color,0,0, //b
                0,0,0,1,0 //a, 1이 기본, 0이면 투명해짐
        };
        ColorMatrix cm = new ColorMatrix(array);*/
        ColorMatrix cm = new ColorMatrix();
        cm.setSaturation(sat);//0 흑백, 1 컬러
        paint.setColorFilter(new ColorMatrixColorFilter(cm));

        //blur
        if(blur==true){
            BlurMaskFilter blur;
            blur = new BlurMaskFilter(100, BlurMaskFilter.Blur.NORMAL);
            paint.setMaskFilter(blur);
        }

        //embos
        if(embos==true){
            EmbossMaskFilter embos;
            embos = new EmbossMaskFilter(new float[]{3, 3, 3}, 0.5f, 5, 20);
            paint.setMaskFilter(embos);
        }
        //draw  -- 그냥 그리면 이미지뷰랑 똑같음
        canvas.drawBitmap(bitmap, picX, picY, paint);
        bitmap.recycle();
    }
}
