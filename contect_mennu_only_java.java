package com.example.javaonly_context_menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    LinearLayout layout1;
    TextView yum;
    ImageView donut;
    static final int RED = 1, GREEN = 2, YELLOW = 3;
    static final int ROTATE = 4, ENLARGE = 5, ORIGINAL = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout1 = findViewById(R.id.layout1);
        yum = findViewById(R.id.yum);
        donut = findViewById(R.id.donut);
        registerForContextMenu(yum);
        registerForContextMenu(donut);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);//if(v==yum) {}, if(v==donut){}~ 
        menu.add(0, RED, 0, "red");
        menu.add(0, GREEN, 0, "green");
        menu.add(0, YELLOW, 0, "yellow");
        menu.add(0, ROTATE, 0, "이미지 180도 회전");
        menu.add(0, ENLARGE, 0, "이미지 2배 확대");
        menu.add(0, ORIGINAL, 0, "이미지 원래대로");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case RED:
                layout1.setBackgroundColor(Color.RED);
                break;
            case GREEN:
                layout1.setBackgroundColor(Color.GREEN);
                break;
            case YELLOW:
                layout1.setBackgroundColor(Color.YELLOW);
                break;
            case ROTATE:
                donut.setRotation(180);
                break;
            case ENLARGE:
                donut.setScaleX(2);
                donut.setScaleY(2);
                break;
            case ORIGINAL:
                donut.setRotation(0);
                donut.setScaleX(1);
                donut.setScaleY(1);
                break;
        }
        return super.onContextItemSelected(item);
    }
}
