package com.example.minipaint;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SubMenu;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    static final int LINE = 1, CIRCLE = 2, SQUARE = 3, BLUE = 4, RED = 5, GREEN = 6, BLACK = 7;
    static int curShape = LINE;
    static int lineColor = Color.BLACK;
    static List<MyShape> myShapes = new ArrayList<>();
    static boolean isFinish = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyGraphicView(this));
        setTitle("paintShop");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, LINE, 0, "draw a line");
        menu.add(0, CIRCLE, 0, "draw a circle");
        menu.add(0, SQUARE, 0, "draw a square");

        SubMenu subMenu = menu.addSubMenu("change a color");
        subMenu.add(0, BLUE, 0, "blue");
        subMenu.add(0, RED, 0, "red");
        subMenu.add(0, GREEN, 0, "green");
        subMenu.add(0, BLACK, 0, "black");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case LINE:
                curShape = LINE;
                break;
            case CIRCLE:
                curShape = CIRCLE;
                break;
            case SQUARE:
                curShape = SQUARE;
                break;
            case BLUE:
                lineColor = Color.BLUE;
                break;
            case RED:
                lineColor = Color.RED;
                break;
            case GREEN:
                lineColor = Color.GREEN;
                break;
            case BLACK:
                lineColor = Color.BLACK;
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private static class MyGraphicView extends View {
        int startX = -1, startY = -1, stopX = -1, stopY = -1;
        Paint paint;

        public MyGraphicView(Context context) {
            super(context);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    startX = (int) event.getX();
                    startY = (int) event.getY();
                    isFinish = false;
                    break;
                case MotionEvent.ACTION_MOVE:
                    stopX = (int) event.getX();
                    stopY = (int) event.getY();
                    this.invalidate();//ondraw 메서드 호출
                    break;
                case MotionEvent.ACTION_UP: //떼면 그린거 남음
                    isFinish = true;
                    MyShape shape = new MyShape();
                    shape.shapeType = curShape;
                    shape.startX = startX;
                    shape.startY = startY;
                    shape.stopX = stopX;
                    shape.stopY = stopY;
                    shape.color = lineColor;
                    myShapes.add(shape);//동적배열에 add
                    this.invalidate();//oncdraw 호출
                    break;
            }
            return true;
        }

        @SuppressLint("DrawAllocation")
        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            paint = new Paint();
            paint.setAntiAlias(true);
            paint.setStrokeWidth(5);
            paint.setStyle(Paint.Style.STROKE);
            //배열에 보관된 도형을 그리기
            for(int i =0; i<myShapes.size(); i++){
                MyShape shape = myShapes.get(i);
                paint.setColor(shape.color);
                switch (shape.shapeType) {
                    case LINE:
                        canvas.drawLine(shape.startX, shape.startY, shape.stopX, shape.stopY, paint);
                        break;
                    case CIRCLE:
                        int radius = (int) Math.sqrt(Math.pow(shape.stopX - shape.startX, 2) + Math.pow(shape.stopY - shape.startY, 2));
                        canvas.drawCircle(shape.startX, shape.stopY, radius, paint);
                        break;
                    case SQUARE:
                        canvas.drawRect(shape.startX, shape.startY, shape.stopX, shape.stopY, paint);
                        break;
                }

            }

            if (isFinish == false) {//마우스 안뗌  ; 현재 진행중인 도형을 그린다.
                paint.setColor(lineColor);
                switch (curShape) {
                    case LINE:
                        canvas.drawLine(startX, startY, stopX, stopY, paint);
                        break;
                    case CIRCLE:
                        int radius = (int) Math.sqrt(Math.pow(stopX - startX, 2) + Math.pow(stopY - startY, 2));
                        canvas.drawCircle(startX, stopY, radius, paint);
                        break;
                    case SQUARE:
                        canvas.drawRect(startX, startY, stopX, stopY, paint);
                        break;
                }
            }

        }
    }

    //도형 클래스 --> 만들어서 동적배열에 넣어
    private static class MyShape {
        int shapeType; //어떤 도형으로 그렸냐
        int startX, startY, stopX, stopY;//그려진 도형의 좌표
        int color;//도형 색상
    }
}
