package com.example.voteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

public class NextActivity extends AppCompatActivity {
    TextView tvs[] = new TextView[9];
    RatingBar rbar[] = new RatingBar[9];
    Button btn_return;
    int tvIDs[] = {R.id.tv1, R.id.tv2, R.id.tv3, R.id.tv4, R.id.tv5, R.id.tv6, R.id.tv7, R.id.tv8, R.id.tv9};
    int rbarIDs[] = {R.id.rBar1, R.id.rBar2, R.id.rBar3, R.id.rBar4, R.id.rBar5, R.id.rBar6, R.id.rBar7, R.id.rBar8, R.id.rBar9};
    int voteResult[];
    String imageName[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        btn_return = findViewById(R.id.btn_return);
        for (int i = 0; i < tvs.length; i++) {
            tvs[i] = findViewById(tvIDs[i]);
            rbar[i] = findViewById(rbarIDs[i]);
        }
        Intent gintent = getIntent();
        voteResult = gintent.getIntArrayExtra("VoteCount"); // 메인에서 변수 여기에 담았으니까
        imageName = gintent.getStringArrayExtra("TextId");
        for (int i = 0; i < tvIDs.length; i++) {
            tvs[i].setText(imageName[i] + "(총 " + voteResult[i] + "표)");
            rbar[i].setRating(voteResult[i]); //rating이 별 표시하는 거니깐
        }
        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); // 메인 돌아가고 싶으면 intent.get~ --> 메모리에 메인이 또 쌓이게 됨 -> 메모리에서 제거하는 명령어 사용 책 참고
            }
        });
    }
}