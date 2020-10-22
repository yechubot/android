package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    EditText et_num;
    Button btn_start, btn_result;
    TextView tv_resultText;
    ImageView iv_img;
    int comNum, myNum, count;
    Random random = new Random(); // Random instance 생성

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_num = (EditText) findViewById(R.id.et_num);
        btn_start = (Button) findViewById(R.id.btn_start);
        btn_result = (Button) findViewById(R.id.btn_result);
        tv_resultText = (TextView) findViewById(R.id.tv_resultText);
        iv_img = (ImageView) findViewById(R.id.iv_img);

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_result.setEnabled(true);
                btn_start.setEnabled(false);
                tv_resultText.setText("게임을 시작합니다");
                count = 0; // 처음에 초기화 안했는데 어떻게 실행됐지?
                comNum = random.nextInt(100) + 1; // 난수 정수로 받겠다
                //comNum = (int) (Math.random() * 100) + 1;

                iv_img.setImageResource(R.drawable.number);
            }
        });
        btn_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myNum = Integer.parseInt(et_num.getText().toString());
                count++; // 처음에 틀린 if에만 넣었었음 --> 맞혔을 때 count 집계가 안되는 문제?
                if (comNum > myNum) {
                    tv_resultText.setText("숫자가 작습니다. 큰 수를 넣으세요(시도횟수="+count+"회)");
                    iv_img.setImageResource(R.drawable.wrong);
                } else if (comNum == myNum) {
                    tv_resultText.setText("축하합니다! " + count + "번 만에 맞추셨습니다!");
                    iv_img.setImageResource(R.drawable.good);
                    btn_start.setEnabled(true);
                    btn_result.setEnabled(false);
                } else if (comNum < myNum) {
                    tv_resultText.setText("숫자가 큽니다. 작은 수를 넣으세요(시도횟수="+count+"회)");
                    iv_img.setImageResource(R.drawable.wrong);
                } // maybe just else
                et_num.setText("");

            }
        });
            end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("프로그램 종료");
                builder.setMessage("정말로 종료하시겠습니까?");
                builder.setPositiveButton("네", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                builder.setNegativeButton("취소", null);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
}
