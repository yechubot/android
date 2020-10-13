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
    RadioButton rdoPet[] = new RadioButton[3]; // rdoButton 객체 3개 만들겠다.
    int rdoPetID[] ={R.id.dog, R.id.cat, R.id.rabbit}; // ID 값 int 확인했으니까
    int imgID[] = {R.drawable.dog, R.drawable.cat, R.drawable.rabbit};
    Button end,goBack;
    ImageView img;
    LinearLayout innerLayout;
    RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        check = (Switch) findViewById(R.id.check);
        //객체 많을 때 유용
        for(int i =0; i<rdoPet.length; i++){
        rdoPet[i]=  (RadioButton) findViewById(rdoPetID[i]);
        }

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
        for(int i=0; i<rdoPet.length; i++){
            final int index =i;
            rdoPet[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    img.setImageResource(imgID[index]);
                }
            });
        }

        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check.setChecked(false);
                rg.clearCheck();
                img.setImageResource(0);
            }
        });

    }
}
