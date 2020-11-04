package com.example.transfer_activity1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText edtNum1, edtNum2;
    Button btnCalcAct;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtNum1 = findViewById(R.id.edtNum1);
        edtNum2 = findViewById(R.id.edtNum2);
        btnCalcAct = findViewById(R.id.btn_cal);
        tvResult = findViewById(R.id.tvResult);
        btnCalcAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mintent = new Intent(getApplicationContext(), CalActivity.class);
                mintent.putExtra("Num1", Integer.parseInt(edtNum1.getText().toString()));//int로 미리 바꿔서 보내기
                mintent.putExtra("Num2", Integer.parseInt(edtNum2.getText().toString()));
                //startActivity(mintent) --> 일방향 액티비티처럼 이렇게 하면 안됨
                startActivityForResult(mintent, 0); //양방향일때는 이걸 사용

            }
        });
    }
//CalActivity에서 setResult 한 결과를 받아 메인에서 처리
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {//intent 변수 - data
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            tvResult.setText("두 수의 합"+data.getIntExtra("Plus", 0));//잘못된 값이 들어왔을 땐 디폴트 0

        }
    }
}
