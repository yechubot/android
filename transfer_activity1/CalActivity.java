package com.example.transfer_activity1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CalActivity extends AppCompatActivity {
    Button btnReturn;
    int plusValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cal);
        btnReturn = findViewById(R.id.btnReturn);
        final Intent gintent = getIntent();
        plusValue = gintent.getIntExtra("Num1",0)+gintent.getIntExtra("Num2",0);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent outIntent = new Intent(getApplicationContext(),MainActivity.class);//보낼 때는 항상 인스턴스 생성
                outIntent.putExtra("Plus",plusValue);
                setResult(RESULT_OK,outIntent);
                finish();
            }
        });
    }
}