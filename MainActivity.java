package com.example.tablecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText etInput1, etInput2;
    Button[] numButtons = new Button[10];
    int numButtonsIDs[]={R.id.Num0, R.id.Num1, R.id.Num2, R.id.Num3, R.id.Num4,
            R.id.Num5, R.id.Num6, R.id.Num7, R.id.Num8, R.id.Num9};
    Button btnAdd, btnSub, btnMul, btnDiv;
    TextView textResult;
    String stringNum1, stringNum2;
    int result, i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etInput1=(EditText)findViewById(R.id.etInput1);
        etInput2=(EditText)findViewById(R.id.etInput2);
        for(int i =0; i<numButtons.length; i++){
            numButtons[i]=(Button)findViewById(numButtonsIDs[i]);
        }
        btnAdd=(Button)findViewById(R.id.btnAdd);
        btnSub=(Button)findViewById(R.id.btnSub);
        btnMul=(Button)findViewById(R.id.btnMul);
        btnDiv=(Button)findViewById(R.id.btnDiv);
        textResult=(TextView)findViewById(R.id.textResult);

        for(int i=0; i<numButtons.length; i++){
            final int index;
            index=i;
            numButtons[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(etInput1.isFocused()==true){
                        stringNum1 =etInput1.getText().toString()+numButtons[index].getText().toString();
                        //etInput1.getText().toString()으로 직접 입력한것도 함께 작성되도록함
                        etInput1.setText(stringNum1);
                    }else if(etInput2.isFocused()==true){
                        stringNum2 =etInput2.getText().toString()+numButtons[index].getText().toString();
                        etInput2.setText(stringNum2);
                    }else {
                        Toast.makeText(getApplicationContext(),"입력창을 선택한 다음 숫자를 입력하세요", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stringNum1 = etInput1.getText().toString();
                stringNum2 = etInput2.getText().toString();
                result=Integer.parseInt(stringNum1)+Integer.parseInt(stringNum1);

                textResult.setText("계산 결과: "+result);
            }
        });






    }
}
