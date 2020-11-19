package com.example.minipaint;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class MainActivity extends AppCompatActivity {
    static final int LINE = 1, CIRCLE = 2, SQUARE = 3, BLUE = 4, RED = 5, GREEN = 6, BLACK = 7;
    static int curShape = LINE;
    static int lineColor =BLACK;

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
        subMenu.add(0,BLACK,0,"black");
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
                lineColor = BLUE;
                break;
            case RED:
                lineColor = RED;
                break;
            case GREEN:
                lineColor = GREEN;
                break;
            case BLACK:
                lineColor = BLACK;
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
                    break;
                case MotionEvent.ACTION_MOVE:
                case MotionEvent.ACTION_UP:
                    stopX = (int) event.getX();
                    stopY = (int) event.getY();
                    this.invalidate();
                    break;
            }
            return true;
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            paint = new Paint();
            paint.setAntiAlias(true);
            paint.setStrokeWidth(5);
            paint.setStyle(Paint.Style.STROKE);

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
            switch (lineColor) {
                case BLUE:
                    paint.setColor(Color.BLUE);
                    break;
                case RED:
                    paint.setColor(Color.RED);
                    break;
                case GREEN:
                    paint.setColor(Color.GREEN);
                    break;
                case BLACK:
                    paint.setColor(Color.BLACK);
                    break;
            }
        }
    }
}
