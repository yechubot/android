package com.example.twowaysactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class CalActivity extends AppCompatActivity {
    Button btn_sendCal,toast;
    int calResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cal);
        btn_sendCal = findViewById(R.id.btn_sendCal);
        toast = findViewById(R.id.toast);
        Intent gintent = getIntent();
        int num1 = gintent.getIntExtra("Num1", 0);
        int num2 = gintent.getIntExtra("Num2", 0);

        if (gintent.getBooleanExtra("C_plus", false)) {
            calResult = num1 + num2;
        } else if (gintent.getBooleanExtra("C_minus", false)) {
            calResult = num1 - num2;
        } else if (gintent.getBooleanExtra("C_mul", false)) {
            calResult = num1 * num2;
        } else if (gintent.getBooleanExtra("C_div", false)) {
            calResult = num1 / num2;
        }

        btn_sendCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sIntent = new Intent(getApplicationContext(), MainActivity.class);
                sIntent.putExtra("CalResult", calResult);
                setResult(RESULT_OK, sIntent);
                finish();
            }
        });
        toast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {           // string만 들어감
                Toast.makeText(getApplicationContext(),String.valueOf(calResult),Toast.LENGTH_SHORT).show();
            }
        });


    }
}