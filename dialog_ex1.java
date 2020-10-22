package com.example.dialog_ex;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnDialog = findViewById(R.id.btnDialog);
        btnDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // builder 객체 생성
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this
                );//어디에 보이게 할 건지
                // 용도 설정
                builder.setTitle("공지사항"); // 대화상자에 보이는 제목
                builder.setIcon(R.drawable.bob);
                builder.setMessage("마스크 착용");// 대화상자에 보이는 내용
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override                           // 버튼을 누르고 아무것도 수행 안하는 경우 null
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "cookie", Toast.LENGTH_SHORT).show();
                    }
                });
                //builder를 보여주기 위한 dialog 생성
                AlertDialog dialog = builder.create(); // 이거 없이 builder 객체 생성한 걸 builder.show()해도 에러 안남
                dialog.show();
            }
        });

    }
}
