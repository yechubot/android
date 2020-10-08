package com.example.bmicheck;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    EditText heightInput, weightInput;
    Button obesityCheck;
    TextView showResult;
    ImageView showImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        heightInput = (EditText) findViewById(R.id.heightInput);
        weightInput = (EditText) findViewById(R.id.weightInput);
        obesityCheck = (Button) findViewById(R.id.obesityCheck);
        showResult = (TextView) findViewById(R.id.showResult);
        showImg = (ImageView) findViewById(R.id.showImg);

        obesityCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                String weightValue = weightInput.getText().toString();
                String heightValue = heightInput.getText().toString();
                double heightFinal = Double.parseDouble(heightValue);
                double weightFinal = Double.parseDouble(weightValue);
                double standardKg = (heightFinal - 100) * 0.9;

                    if (weightFinal > standardKg + 5) {
                        showResult.setText("비만입니다. 운동하세요 ");
                        showImg.setImageResource(R.drawable.ob);
                    } else if (weightFinal < standardKg - 5) {
                        showResult.setText("마른체형입니다.");
                        showImg.setImageResource(R.drawable.thin);
                    } else {
                        showResult.setText("정상입니다.");
                        showImg.setImageResource(R.drawable.good);
                    }
                } catch(java.lang.NumberFormatException e){
                    Toast.makeText(getApplicationContext(),"숫자를 입력하세요", Toast.LENGTH_SHORT).show();
                } catch(Exception e){
                    //모든 에러
                    Toast.makeText(getApplicationContext(),"개발자에게 문의하세요(010-1111-2222)"+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}