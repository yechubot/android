package com.example.voteapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn_end;
    ImageView[] img = new ImageView[9];
    int[] imgId = {R.id.pic1, R.id.pic2, R.id.pic3, R.id.pic4, R.id.pic5, R.id.pic6, R.id.pic7, R.id.pic8, R.id.pic9};
    String[] textId = {"독서하는 소녀", "꽃장식 모자 소녀","부채를 든 소녀","이레느깡 단 베르양", "잠자는 소녀","테라스의 두 자매", "피아노 레슨", "피아노 앞의 소녀들", "해변에서"};
    int [] counts = new int[9];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setIcon(R.drawable.pici_icon);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setTitle("영화 투표 앱");
        btn_end = findViewById(R.id.btn_end);

        for(int i = 0; i < img.length; i++) {
            img[i] =(ImageView) findViewById(imgId[i]);
        }

        for(int i =0; i<img.length; i++){
            final int index =i;
            img[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    counts[index]++;
                    showToast(textId[index]+":"+ "총 " +counts[index]+"표");
                }
            });
        }
        btn_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               //결과 액티비티 뜸
                Intent intent = new Intent(getApplicationContext(),NextActivity.class);
                intent.putExtra("VoteCount",counts); // 변수이름, 값 -- counts는 배열, --> 알아서 배열화된다.
                intent.putExtra("TextId",textId);
                startActivity(intent);
            }
        });
    }
     void showToast(String msg){
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
    }
}
