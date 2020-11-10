package com.example.spinnerex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.opengl.EGLExt;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Spinner spinner1;
    ImageView image_view_movie;
    String[] movie = {"써니", "완득이", "괴물", "라디오스타", "비열한거리", "왕의남자", "아일랜드", "웰컴투동막골", "헬보이", "백투더퓨쳐"};
    Integer[] posterID = {R.drawable.mov01, R.drawable.mov02, R.drawable.mov03, R.drawable.mov04, R.drawable.mov05, R.drawable.mov06, R.drawable.mov07, R.drawable.mov08, R.drawable.mov09, R.drawable.mov10};
    static final int RIGHT = 1, LEFT = 2;
    int angle =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner1 = findViewById(R.id.spinner1);
        image_view_movie = findViewById(R.id.image_view_movie);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, movie);
        spinner1.setAdapter(adapter);
        //context menu 꾹 눌렀을 때 나오는 메뉴
        registerForContextMenu(image_view_movie);//꾹 누르면 포스터가 context menu에 나오게 등록해야 함
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                image_view_movie.setImageResource(posterID[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    //context menu create
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v == image_view_movie) {
            menu.add(0, RIGHT, 0, "오른쪽으로 회전");
            menu.add(0, LEFT, 0, "왼쪽으로 회전");
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case RIGHT:
                angle+=10;
                image_view_movie.setRotation(angle);
                break;
            case LEFT:
                angle-=10;
                image_view_movie.setRotation(angle);
        }
        return super.onContextItemSelected(item);
    }
}
