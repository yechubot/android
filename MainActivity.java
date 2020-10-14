package com.example.table;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edt1, edt2;
    Button btn[] = new Button[10];
    int btnId[] = {R.id.num0, R.id.num1, R.id.num2, R.id.num3, R.id.num4, R.id.num5, R.id.num6, R.id.num7, R.id.num8, R.id.num9};
    Button plus, minus, mul, divide;
    TextView result;
    int finalResult;
   String stNum1, stNum2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt1 = (EditText) findViewById(R.id.edt1);
        edt2 = (EditText) findViewById(R.id.edt2);
        plus = (Button) findViewById(R.id.plus);
        minus = (Button) findViewById(R.id.minus);
        mul = (Button) findViewById(R.id.mul);
        divide = (Button) findViewById(R.id.divide);
        for (int i = 0; i < btn.length; i++) {
            btn[i] = (Button) findViewById(btnId[i]);
        }

        for(int i=0; i<btn.length; i++){
            final int index;
            index = i;
            btn[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(edt1.isFocused()){
                        stNum1 = edt1.getText().toString()+btn[index].getText().toString();
                        edt1.setText(stNum1);
                    }else if(edt2.isFocused()){
                        stNum2 = edt2.getText().toString()+btn[index].getText().toString();
                        edt2.setText(stNum2);
                    }else {
                        Toast.makeText(getApplicationContext(), "click input field to enter your numbers", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stNum1 = edt1.getText().toString();
                stNum2 = edt2.getText().toString();
                finalResult = Integer.parseInt(stNum1)+Integer.parseInt(stNum2);

                result.setText(result.getText().toString() +finalResult); //"result" + finalResult
            }
        });


    }
}
