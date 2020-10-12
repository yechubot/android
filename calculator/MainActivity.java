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
    Button btnAdd, btnSub, btnMul, btnDiv;
    EditText num1, num2;
    int FinalResult;

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
        num1 = (EditText) findViewById(R.id.EditNum1);
        num2 = (EditText) findViewById(R.id.EditNum2);

        btnAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    int value1 = Integer.parseInt(num1.getText().toString());
                    int value2 = Integer.parseInt(num2.getText().toString());
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
                    int value1 = Integer.parseInt(num1.getText().toString());
                    int value2 = Integer.parseInt(num2.getText().toString());
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
                    int value1 = Integer.parseInt(num1.getText().toString());
                    int value2 = Integer.parseInt(num2.getText().toString());
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
                    int value1 = Integer.parseInt(num1.getText().toString());
                    int value2 = Integer.parseInt(num2.getText().toString());
                    FinalResult = value1 / value2;
                    result.setText("실행결과: " + FinalResult);
                } catch (java.lang.NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "숫자를 입력하세요", Toast.LENGTH_SHORT).show();
                } catch (ArithmeticException e) {
                    result.setText("실행결과: " + 0);
                }
                ;
            }
        });
    }
}
