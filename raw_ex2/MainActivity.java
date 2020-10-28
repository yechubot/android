package com.example.raw_ex2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    TextView title, explain;
    RadioButton rdobtn[] = new RadioButton[4];
    int rdobtnID[] = {R.id.head, R.id.face, R.id.body, R.id.teeth};
    int rdoimgID[] = {R.drawable.head, R.drawable.wash, R.drawable.body, R.drawable.tooth};
    Button btn_result;
    ImageView img;
    String titles[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        title = findViewById(R.id.title);
        explain = findViewById(R.id.explain);
        btn_result = findViewById(R.id.btn_result);
        img = findViewById(R.id.img);
        for (int i = 0; i < rdobtn.length; i++) {
            rdobtn[i] = (RadioButton) findViewById(rdobtnID[i]);
        }
        //title
        InputStream inputS = getResources().openRawResource(R.raw.ptest);
        try {
            byte txt[] = new byte[inputS.available()];
            inputS.read(txt);
            String str = new String(txt);
            titles = str.split("#");
            title.setText(titles[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //click rdobtn
        for (int i = 0; i < rdobtn.length; i++) {
            final int index = i;
            rdobtn[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    img.setImageResource(rdoimgID[index]);
                }
            });
        }
      /*  head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img.setImageResource(R.drawable.head);
                explain.setText(titles[1]);
            }
        });
        body.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img.setImageResource(R.drawable.body);
                explain.setText(titles[3]);
            }
        });
        teeth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img.setImageResource(R.drawable.tooth);
                explain.setText(titles[4]);
            }
        });
        face.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img.setImageResource(R.drawable.wash);
                explain.setText(titles[2]);
            }
        });
        btn_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch()
            }
        });*/
        //click result_btn
        btn_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
        for(int i =0; i< rdobtn.length; i++){
            final int index = i;
            while(rdobtn[index].isChecked()){
                explain.setText(titles[i+1]);
                break;
            }
        }
            }
        });

    }
}
