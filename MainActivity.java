package com.example.javacode;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    LinearLayout.LayoutParams params1, params2, params3;
    LinearLayout mainLayout, inLayout;
    TextView tv1;
    EditText edtName;
    Button btnHello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        params1 = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        );
        params2 = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        params3 = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        setContentView(mainLayout, params1);

        inLayout = new LinearLayout(this);
        inLayout.setOrientation(LinearLayout.HORIZONTAL);
        mainLayout.addView(inLayout);

        tv1 = new TextView(this);
        inLayout.addView(tv1, params2);
        tv1.setText("이름");

        edtName = new EditText(this);
        inLayout.addView(edtName,params3);

        btnHello = new Button(this);
        mainLayout.addView(btnHello);
        btnHello.setText("인사하기");

        btnHello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), edtName.getText().toString() + "님 안녕하세요", Toast.LENGTH_SHORT).show();
            }
        });
    }
}