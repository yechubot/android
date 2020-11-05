package com.example.twowaysactivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText edt1, edt2;
    RadioButton plus, minus, mul, div;
    Button btn_cal;
    TextView tv_result;
    RadioGroup rGroup1; //추가

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt1 = findViewById(R.id.edt1);
        edt2 = findViewById(R.id.edt2);
        plus = findViewById(R.id.plus);
        minus = findViewById(R.id.minus);
        mul = findViewById(R.id.mul);
        div = findViewById(R.id.div);
        btn_cal = findViewById(R.id.btn_cal);
        tv_result = findViewById(R.id.tv_result);
        rGroup1 = findViewById(R.id.rGroup1);

        btn_cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //연산 액티비티 intent 생성
                Intent mintent = new Intent(getApplicationContext(), CalActivity.class);
                //입력값 보내기
                mintent.putExtra("Num1", Integer.parseInt(edt1.getText().toString()));
                mintent.putExtra("Num2", Integer.parseInt(edt2.getText().toString()));
                //라디오 버튼 값 보내기
                mintent.putExtra("C_plus", plus.isChecked());
                mintent.putExtra("C_minus", minus.isChecked());
                mintent.putExtra("C_mul", mul.isChecked());
                mintent.putExtra("C_div", div.isChecked());
                startActivityForResult(mintent, 0);
            }
        });
    }

    //받은 연산결과 set하기
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            tv_result.setText("결과"+data.getIntExtra("CalResult", 0));
            // setText에는 String만 들어가니까 처음에 "CalResult"만 넣었을 때는 int가 됨
            //앞의 글자와 같이 쓰면 string으로 변환되서 들어가고, 아니면 String.valueOf에 넣어서 출력하여 String으로 출력해야 함
        }
    }
}