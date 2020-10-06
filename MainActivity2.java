package com.example.widgetex;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button button1, btn2, btn3; // name can be different
    ImageView img;
    TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = (Button) findViewById(R.id.btn1); //view -> whole widget
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        img = (ImageView) findViewById(R.id.img);
        tv1 = (TextView) findViewById(R.id.tv1);
        tv1.setTextColor(Color.RED); // textColor #ff0000
        button1.setText("클릭해주세요");
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn2.setVisibility(View.VISIBLE); // show btn2 when clicking btn1
            }
        });

        //click btn 2-> btn 3, btn 3 click -> show image

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn3.setEnabled(true);

            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img.setImageResource(R.drawable.mini);
            }
        });
    }
}
