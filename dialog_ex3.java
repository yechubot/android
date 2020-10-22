package com.example.dialog_ex3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button btnInput;
    TextView tvName, tvEmail;
    EditText dialog_edtName, dialog_edtEmail;
    View dialogView; // dialog 전용 뷰
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnInput = findViewById(R.id.btnInput);
        tvName = findViewById(R.id.tvName);
        tvEmail = findViewById(R.id.tvEmail);

        btnInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogView = View.inflate(MainActivity.this,R.layout.dialog1,null);  // dialog xml을 변수에 담음
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("사용자 정보 입력");
                builder.setIcon(R.drawable.friends);
                dialog_edtName = dialogView.findViewById(R.id.dialog_edtName);
                dialog_edtEmail = dialogView.findViewById(R.id.dialog_edtEmail);
                builder.setView(dialogView);
                builder.setPositiveButton("취소", null);
                builder.setNegativeButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        tvName.setText(dialog_edtName.getText().toString());
                        tvEmail.setText(dialog_edtEmail.getText().toString());
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });
    }
}
