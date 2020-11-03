package com.example.sort;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText edtNum;
    Button btnInput, btnResult;
    TextView tvResult;
    int num[] = new int[5];
    int count = 0;
    String result;
    int temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtNum = findViewById(R.id.edtNum);
        btnInput = findViewById(R.id.btnInput);
        btnResult = findViewById(R.id.btnResult);
        tvResult = findViewById(R.id.tvResult);
        btnInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num[count] = Integer.parseInt(edtNum.getText().toString());
                edtNum.setText(""); //전의 값 지움
                count++;
            }
        });
        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // before sort
                result = "[sort 전]\n";
                for (int i = 0; i < num.length; i++) {
                    result += num[i] + "   " ;
                }
                result += "\n[sort 후]\n";
                //selection?? sort algorithm
                for (int a = 0; a < num.length - 1; a++) {//비교하는 개수는 항상 자료보다 하나 작음
                    for (int b = a + 1; b < num.length; b++) {
                        if (num[a] > num[b]) { // ascending
                            temp = num[a];
                            num[a] = num[b];
                            num[b] = temp;
                        }
                    }
                }
                //sort result
                for (int i = 0; i < num.length; i++) {
                    result += num[i] + "   " ;
                }
                tvResult.setText(result);
            }
        });
    }
}
