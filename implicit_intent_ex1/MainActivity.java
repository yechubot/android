package com.example.implicit_intent_ex1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnDial, btnHp, btnMap, btnSearch, btnSound, btnPhoto, btnPhotoView, btnSms;
    Intent intent;
    ImageView ivPet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnDial = findViewById(R.id.btnDial);
        btnHp = findViewById(R.id.btnHp);
        btnMap = findViewById(R.id.btnMap);
        btnSearch = findViewById(R.id.btnSearch);
        btnSound = findViewById(R.id.btnSound);
        btnPhoto = findViewById(R.id.btnPhoto);
        btnPhotoView = findViewById(R.id.btnPhotoView);
        btnSms = findViewById(R.id.btnSms);
        ivPet = findViewById(R.id.ivPet);

        btnDial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("tel:01012341234");//전달할 값을 넣기
                //암시적 intent
                intent = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(intent);
            }
        });

        btnHp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://www.naver.com");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://maps.google.com/maps?q=" + 37.358765 + "," + 127.119790);// 위도 , 경도
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(Intent.ACTION_WEB_SEARCH);
                intent.putExtra(SearchManager.QUERY, "강아지");
                startActivity(intent);
            }
        });
        btnSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(Intent.ACTION_SENDTO);
                intent.putExtra("sms_body", "안녕 뭐해");
                intent.setData(Uri.parse("smsto:" + Uri.encode("010-1234-5678")));
                startActivity(intent);
            }
        });
        btnSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);//모든 언어 지원
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "좋아하는 애완동물을 말해보세요");
                startActivityForResult(intent, 50); // 강아지라고 말한 글자를 나중에 돌려주도록
            }
        });

        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(intent);
            }
        });
        btnPhotoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 30);// 찍으면 돌려받을 것
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 50) {
            ArrayList<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            String result = results.get(0); //동적배열 가져오기, 0번째 가져옴
            if (result.equals("강아지")) {//나중에 자료가 방대해지면 db와 연동해서 씀
                ivPet.setImageResource(R.drawable.dog);
            } else if (result.equals("고양이")) {
                ivPet.setImageResource(R.drawable.cat);
            } else if (result.equals("토끼")) {
                ivPet.setImageResource(R.drawable.rabbit);
            } else {
                ivPet.setImageResource(0);//이미지 사라짐
                Toast.makeText(getApplicationContext(), "이미지가 없어요...", Toast.LENGTH_SHORT).show();
            }
        } else if (resultCode == RESULT_OK && requestCode == 30) {
            Bitmap bm = (Bitmap) data.getExtras().get("data");
            ivPet.setImageBitmap(bm);//setimageresource는 drawble에서 가져올 때
        }
    }
}
