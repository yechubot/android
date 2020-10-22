package com.example.dialog_ex2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    Button btnChoice;
    ImageView ivPet;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnChoice = findViewById(R.id.btnChoice);
        ivPet = findViewById(R.id.ivPet);
        //item 목록
        final String pet[] = {"강아지", "고양이", "토끼"};
        final int petImg[] = {R.drawable.dog, R.drawable.cat, R.drawable.rabbit};

        btnChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("좋아하는 동물은?");
                builder.setIcon(R.drawable.shark);
                /*  builder.setItems(pet, new DialogInterface.OnClickListener() {
                    @Override //item 보여줄 배열, 선택시 수행할 내용
                    public void onClick(DialogInterface dialogInterface, int which) {
                        ivPet.setImageResource(petImg[which]);
                    }
                });*/

                // radio button 같이 보이게 만들기
                builder.setSingleChoiceItems(pet, 0, new DialogInterface.OnClickListener() {
                    @Override                      //0번째 체크된 상태로 시작 (-1 로 설정-> 체크 안된 상태에서 시작)
                    public void onClick(DialogInterface dialogInterface, int which) {
                        // ivPet.setImageResource(petImg[which]); --> 누를 때 창은 안닫히고 뒤에 이미지가 뜸
                        position = which;
                    }
                });
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ivPet.setImageResource(petImg[position]);
                    }
                });//닫기만 할 경우 listener는 null
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

    }
}
