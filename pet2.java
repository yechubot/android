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
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Switch check;
    RadioButton dog, cat, rabbit;
    Button end,goBack;
    ImageView img;
    LinearLayout innerLayout;
    RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        check = (Switch) findViewById(R.id.check);
        dog = (RadioButton) findViewById(R.id.dog);
        cat = (RadioButton) findViewById(R.id.cat);
        rabbit = (RadioButton) findViewById(R.id.rabbit);
        img = (ImageView) findViewById(R.id.img);
        end = (Button) findViewById(R.id.end);
        goBack = (Button)findViewById(R.id.goBack);
        innerLayout = (LinearLayout) findViewById(R.id.innerLayout);
        rg = (RadioGroup) findViewById(R.id.rg);

        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) { //if(isChecked==true), if(check.isChecked() ==true)
                    innerLayout.setVisibility(View.VISIBLE);

                } else {
                    innerLayout.setVisibility(View.INVISIBLE);

                }
            }
        });
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (dog.isChecked()) {// 실행은 되는데 parameter가 checkedId니까 checkedId==R.id.dog 이런식으로 하니까 됨. 이렇게 하는게 맞는 듯??
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

        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check.setChecked(false);
                dog.setChecked(false);
                cat.setChecked(false);
                rabbit.setChecked(false);
                img.setImageResource(0);
            }
        });

    }
}
