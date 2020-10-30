package com.example.sdimage_ex2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class MyImageView extends View {
    String imagePath = null; // sd 카드에 있는 이미지
    int saturation =1;// 처음에 색상 있게 하려고 하니까
    public MyImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) { // 그림 그려주는 메소드
        super.onDraw(canvas);
        Paint paint = new Paint();

        if (imagePath != null) {
            Bitmap bm = BitmapFactory.decodeFile(imagePath);
            int picX = (this.getWidth() - bm.getWidth()) / 2;// this-> canvas
            int picY = (this.getHeight() - bm.getHeight()) / 2; // 이미지 가운데 오도록
            ColorMatrix cm = new ColorMatrix(); //캔버스에 그리기 전에
            cm.setSaturation(saturation);
            paint.setColorFilter(new ColorMatrixColorFilter(cm)); // paint에 채도 설정한 값으로 그림
            canvas.drawBitmap(bm, picX, picY, paint); // 이미지,left, right, 붓
            bm.recycle();//비트맵 리소스 해제
        }
    }
}
