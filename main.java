package com.example.widgettest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity { // activity 에 관련된 기능 상속받음
    EditText edtInput; // 선언만 함
    Button btnTextShow, btnHomeOpen;
    RadioButton rdoDog, rdoCat;
    ImageView ivPet;
    String url; // 밖에 선언-> 전역변수 -> 모든 메서드에서 호출 가능

    @Override
    protected void onCreate(Bundle savedInstanceState) { // activity 생성 메서드
        super.onCreate(savedInstanceState); // 부모onCreate 기능 받음
        setContentView(R.layout.activity_main); // activity 화면 setting , view => 위젯 총칭
        // 위젯 위치가 Resources-> layout -> activiy_main 이라는 파일에 있다

        // 위젯과 연결
        //(EditText)안써도 됨 but 쓰면 명시
        edtInput = (EditText)findViewById(R.id.edtInput);
        btnTextShow = (Button)findViewById(R.id.btnTextShow);
        btnHomeOpen = (Button)findViewById(R.id.btnHomeOpen);
        rdoDog = (RadioButton)findViewById(R.id.rdoDog);
        rdoCat = (RadioButton)findViewById(R.id.rdoCat);
        ivPet = (ImageView)findViewById(R.id.ivPet);

        //editText 글자 입력 후, 버튼 클릭하면 토스트로 보이기
        btnTextShow.setOnClickListener(new View.OnClickListener() { // Interface OnClickListener 익명 클래스에서 구현
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),edtInput.getText().toString(),Toast.LENGTH_SHORT).show();// toast에 보일 글자를 만드는 메서드
                //숫자 넣으면 앱 종료됨 string이 아니라서
            }
        });
        // editText에 홈페이지 주소를 입력 후 , 버튼 클릭하면 브라우저 통해 홈페이지 보이기
        btnHomeOpen.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //http 입력 없이도 url 열리게 만들기
                url = edtInput.getText().toString();
                if(!url.substring(0,7).equals("http://")){
                    url= "http://"+url;
                }
                Intent mintent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(mintent);

            }
        });
        // 강아지/고양이 라디오 버튼을 클릭하면 imageView에 강아지/고양이 보이기
        rdoDog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivPet.setImageResource(R.drawable.dog);
            }
        });

        rdoCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivPet.setImageResource(R.drawable.cat);
            }
        });
    }
}
