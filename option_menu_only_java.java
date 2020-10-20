package com.example.java_menu_ex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    LinearLayout layout1;
    ImageView iv1;
    // 코드 깔끔하게
    public static final int ITEM_RED = 1, ITEM_GREEN=2, ITEM_BLUE =3, ITEM_BASE = 4;
    public static final int ITEM_ROTATE = 5, ITEM_SIZE = 6, ITEM_BASEIMG = 7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout1 = findViewById(R.id.layout1);
        iv1 = findViewById(R.id.iv1);

    }

    //옵션 메뉴 생성
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        // java로 메뉴 생성해보기 inflater 없이
        // group id, id, order(안주면 만드는 순서로 만들어짐), title
        menu.add(0, ITEM_RED, 0, "배경색(빨강)");
        menu.add(0, ITEM_GREEN, 0, "배경색(초록)");
        menu.add(0, ITEM_BLUE, 0, "배경색(파랑)");
        menu.add(0, ITEM_BASE, 0, "배경색(기본)");

        SubMenu subMenu = menu.addSubMenu("이미지 변경 >> ");
        subMenu.add(0, ITEM_ROTATE, 0, "이미지 180도 회전");
        subMenu.add(0, ITEM_SIZE, 0, "이미지 2배 확대");
        subMenu.add(0, ITEM_BASEIMG, 0, "이미지 원래대로");
        return true;
    }

    // 옵션의 항목 클릭시 수행
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case ITEM_RED:
                layout1.setBackgroundColor(Color.RED);
                break;
            case ITEM_GREEN:
                layout1.setBackgroundColor(Color.GREEN);
                break;
            case ITEM_BLUE:
                layout1.setBackgroundColor(Color.BLUE);
                break;
            case ITEM_BASE:
                layout1.setBackgroundColor(Color.WHITE);
                break;
            case ITEM_ROTATE:
                iv1.setRotation(180);
                break;
            case ITEM_SIZE:
                iv1.setScaleX(2);
                iv1.setScaleY(2);
                break;
            case ITEM_BASEIMG:
                iv1.setRotation(0);
                iv1.setScaleX(1);
                iv1.setScaleY(1);
        }
        return super.onOptionsItemSelected(item);
    }
}

