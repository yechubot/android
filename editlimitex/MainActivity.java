package com.example.editlimitex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText input;
    TextView counts, output;
    Button save;
    int length;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input = findViewById(R.id.edit_text_Input);
        output = findViewById(R.id.text_view_output);
        counts = findViewById(R.id.text_view_count);
        save = findViewById(R.id.btn_save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, input.getText().toString(), Toast.LENGTH_SHORT).show();
                output.setText(input.getText().toString());
            }
        });
        //입력될때마다 숫자가 줄어들려면 그 입력된 글자 수 호출해야함(클래스 존재), 2가지 방법
        //예전 방식
/*        input.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getAction()==KeyEvent.ACTION_UP){// 눌렀다 뗌
                    length = input.getText().toString().length();//input.length(); 랑 뭐가 다른지 확인해보기
                    counts.setText(length+" / 80");
                } //지워질 때 안바뀜 , 한글 인식..
                return false;
            }
        });*/
        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                length = input.getText().toString().length();
                counts.setText(length+" / 80");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
