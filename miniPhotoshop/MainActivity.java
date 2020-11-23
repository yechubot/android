package com.example.miniphotoshop;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    ImageButton zoomi, zoomt, rotate, bright, dark, gray, blur, embos;
    MyGraphicView myGraphicView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        zoomi = findViewById(R.id.idZoomIn);
        zoomt = findViewById(R.id.idZoomOut);
        rotate = findViewById(R.id.rotate);
        bright = findViewById(R.id.bright);
        dark = findViewById(R.id.dark);
        embos = findViewById(R.id.embos);
        blur = findViewById(R.id.blur);
        // gray = findViewById(R.id.gray);
        myGraphicView = findViewById(R.id.myGV);

        zoomi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myGraphicView.scaleX += 0.1f;
                myGraphicView.scaleY += 0.1f;
                myGraphicView.invalidate();//다시 새 캔버스 준비 -> 계속 다시 그려주는 것
            }
        });

        zoomt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myGraphicView.scaleX -= 0.1f;
                myGraphicView.scaleY -= 0.1f;
                myGraphicView.invalidate();

                if (myGraphicView.scaleX < 0 && myGraphicView.scaleY < 0) {
                    myGraphicView.scaleX = 0.0f;
                    myGraphicView.scaleY = 0.0f;
                }

            }
        });
        rotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myGraphicView.angle += 10;
                myGraphicView.invalidate();
            }
        });

        bright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myGraphicView.sat += 0.1f;
                myGraphicView.invalidate();
            }
        });
        dark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myGraphicView.sat -= 0.1f;
                myGraphicView.invalidate();
            }
        });

/*        gray.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myGraphicView.sat==1){
                    myGraphicView.sat=0;
                }else {
                    myGraphicView.sat=1;
                }
                myGraphicView.invalidate();
            }
        });*/
        blur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myGraphicView.blur == false) {
                    myGraphicView.blur = true;
                } else {
                    myGraphicView.blur = false;
                }
                myGraphicView.invalidate();
            }
        });

        embos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myGraphicView.embos==false){
                    myGraphicView.embos = true;
                }else {
                    myGraphicView.embos = false;
                }
                myGraphicView.invalidate();
            }
        });


    }
}
