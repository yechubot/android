package com.example.mypet;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    CheckBox check;
    RadioButton dog, cat, rabbit;
    RadioGroup group;
    Button checkDone;
    ImageView img;
    LinearLayout innerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        check = (CheckBox) findViewById(R.id.check);
        dog = (RadioButton) findViewById(R.id.dog);
        cat = (RadioButton) findViewById(R.id.cat);
        rabbit = (RadioButton) findViewById(R.id.rabbit);
        img = (ImageView) findViewById(R.id.img);
        checkDone = (Button) findViewById(R.id.checkDone);
        innerLayout = (LinearLayout) findViewById(R.id.innerLayout);

        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    innerLayout.setVisibility(View.VISIBLE);
                } else {
                    innerLayout.setVisibility(View.INVISIBLE);
                }
            }
        });
        checkDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    if (dog.isChecked()) {
                        img.setImageResource(R.drawable.dog);
                    }
                    if (cat.isChecked()) {
                        img.setImageResource(R.drawable.cat);
                    }
                    if (rabbit.isChecked()) {
                        img.setImageResource(R.drawable.rabbit);
                    }


            }
        });

    }
}