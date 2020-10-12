package com.example.calculator;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView result;
    Button btnAdd, btnSub, btnMul, btnDiv, btnRem;
    EditText num1, num2;
    double FinalResult;
    double value1, value2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar bar = getSupportActionBar();
        bar.setTitle("초간단 계산기");
        bar.setDisplayShowHomeEnabled(true);
        bar.setIcon(R.drawable.android);
        result = (TextView) findViewById(R.id.TextResult);
        btnAdd = (Button) findViewById(R.id.BtnAdd);
        btnSub = (Button) findViewById(R.id.BtnSub);
        btnMul = (Button) findViewById(R.id.BtnMul);
        btnDiv = (Button) findViewById(R.id.BtnDiv);
        btnRem = (Button) findViewById(R.id.BtnRem);
        num1 = (EditText) findViewById(R.id.EditNum1);
        num2 = (EditText) findViewById(R.id.EditNum2);

        btnAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                     value1 = Double.parseDouble(num1.getText().toString());
                     value2 = Double.parseDouble(num2.getText().toString());
                    FinalResult = value1 + value2;
                    result.setText("실행결과: " + FinalResult);
                } catch (java.lang.NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "숫자를 입력하세요", Toast.LENGTH_SHORT).show();
                }
                ;
            }
        });
        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                     value1 = Double.parseDouble(num1.getText().toString());
                     value2 = Double.parseDouble(num2.getText().toString());
                    FinalResult = value1 - value2;
                    result.setText("실행결과: " + FinalResult);
                } catch (java.lang.NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "숫자를 입력하세요", Toast.LENGTH_SHORT).show();
                }
                ;
            }
        });
        btnMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                     value1 = Double.parseDouble(num1.getText().toString());
                     value2 = Double.parseDouble(num2.getText().toString());
                    FinalResult = value1 * value2;
                    result.setText("실행결과: " + FinalResult);
                } catch (java.lang.NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "숫자를 입력하세요", Toast.LENGTH_SHORT).show();
                }
                ;
            }
        });
        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                     value1 = Double.parseDouble(num1.getText().toString());
                     value2 = Double.parseDouble(num2.getText().toString());
                    FinalResult = value1/value2;
                    FinalResult=(int)(FinalResult*10)/10.0;
                    result.setText("실행결과: " + FinalResult);
                } catch (java.lang.NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "숫자를 입력하세요", Toast.LENGTH_SHORT).show();
                } catch (ArithmeticException e) {
                    result.setText("실행결과: " + 0);
                }
                ;
            }
        });

        btnRem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              if(num1.getText().toString().equals("")||num2.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "숫자를 입력하세요", Toast.LENGTH_SHORT).show();
                }
                else {
                     value1 = Double.parseDouble(num1.getText().toString()); // 밖으로 나가면 오류( 앱 강제 종료됨..why?!)
                     value2 = Double.parseDouble(num2.getText().toString());
                    FinalResult = value1%value2;
                    result.setText("실행결과: " + FinalResult);
                }

            }
        });
    }
}
