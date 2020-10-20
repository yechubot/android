package com.example.menu_example;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    LinearLayout layout1;
    ImageView iv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout1 = findViewById(R.id.layout1);
        iv1 = findViewById(R.id.iv1);

    }
    // 옵션 메뉴를 메인 액티비티에 추가함
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu); // 부모에 있는 옵션 메뉴, 부모로부터 메뉴를 생성한다는 기본 정보를 받음
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu1, menu); // 소스, 종류
        return true;// 등록하고 false하면 액티비티가 안 돌아감
    }
    // item 선택시 바뀌게 
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemRed:
                layout1.setBackgroundColor(Color.RED);
                break;
            case R.id.itemBlue:
                layout1.setBackgroundColor(Color.BLUE);
                break;
            case R.id.itemGreen:
                layout1.setBackgroundColor(Color.GREEN);
                break;
            case R.id.itemBase:
                layout1.setBackgroundColor(Color.WHITE);
                break;
            case R.id.subRotate:
                iv1.setRotation(180);
                break;
            case R.id.subSize:
                iv1.setScaleX(2);
                iv1.setScaleY(2);
                break;
            case R.id.changeImg:
                iv1.setImageResource(R.drawable.cat);
        }
        return super.onOptionsItemSelected(item);
    }
}
