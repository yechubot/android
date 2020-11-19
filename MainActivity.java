package com.example.graphicsex;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyGraphicView(this)); //set my class to this activity
       // setContentView(R.layout.activity_main); without xml

    }
    private static class MyGraphicView extends View {

        public MyGraphicView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas); // get a blank canvas
            Paint paint = new Paint();// get a brush ..?
            paint.setAntiAlias(true);
            paint.setColor(Color.GREEN);//pick a paint
            //paint.setColor(Color.rgb());

            canvas.drawLine(10,30,300,30,paint); // draw a line 290, 30 length line
            paint.setColor(Color.BLUE);
            paint.setStrokeWidth(5);//start from 0
            canvas.drawLine(10,60,300,60,paint);
            paint.setStrokeWidth(0);
            paint.setColor(Color.BLACK);
            paint.setStyle(Paint.Style.FILL);//not necessary
            canvas.drawRect(10,100,110,200,paint);//draw a square
            paint.setStyle(Paint.Style.STROKE);//not necessary
            canvas.drawRect(110,100,210,200,paint);
            Rect rect = new Rect(320,100,420,200);
            canvas.drawRect(rect,paint);
            canvas.drawCircle(60,400,50,paint);

            paint.setStrokeWidth(5);
            Path path = new Path();// connect the path
            path.moveTo(10,600);//start
            path.lineTo(10+50,600+50);
            path.lineTo(10+100,600);
            path.lineTo(10+150,600+50);
            path.lineTo(10+200,600);
            canvas.drawPath(path,paint);

            paint.setStrokeWidth(0);
            paint.setTextSize(50);
            canvas.drawText("HEY",10,800,paint);
        }
    }
}