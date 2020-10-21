package com.example.context_menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    LinearLayout layout1;
    Button btnBack;
    ImageView ivCookie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout1 = findViewById(R.id.layout1);
        btnBack = findViewById(R.id.btnBack);
        ivCookie = findViewById(R.id.ivCookie);
        registerForContextMenu(btnBack);
        registerForContextMenu(ivCookie);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater minflater = getMenuInflater();
        if(v==btnBack){
            menu.setHeaderTitle("배경색 변경");
            minflater.inflate(R.menu.menu1,menu);
        }
        if(v==ivCookie){
            minflater.inflate(R.menu.menu2,menu);
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
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
            case R.id.subRotate:
                ivCookie.setRotation(180);
                break;
            case R.id.subSize:
                ivCookie.setScaleX(2);
                ivCookie.setScaleY(2);
                break;
            case R.id.subOriginal:
                ivCookie.setRotation(0);
                ivCookie.setScaleX(1);
                ivCookie.setScaleY(1);
                break;

        }
        return super.onContextItemSelected(item);
    }
}
