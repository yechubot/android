package com.example.dialog_ex3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    Button btnInput;
    /*    TextView tvName, tvEmail;
        EditText dialog_edtName, dialog_edtEmail;*/
    View dialogView; // dialog 전용 뷰
    EditText edtName, edtEmail;
    TextView dialog_name, dialog_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnInput = findViewById(R.id.btnInput);
        edtName = findViewById(R.id.edtName);
        edtEmail = findViewById(R.id.edtEmail);

        btnInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogView = View.inflate(MainActivity.this, R.layout.dialog2, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("입력된 사용자 정보");
                builder.setIcon(R.drawable.friends);
                dialog_email = dialogView.findViewById(R.id.dialog_email);
                dialog_name = dialogView.findViewById(R.id.dialog_name);
                builder.setView(dialogView);
                dialog_name.setText(edtName.getText().toString());
                dialog_email.setText(edtEmail.getText().toString());
                builder.setPositiveButton("확인",null);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });


/* // dialog1
        tvName = findViewById(R.id.tvName);
        tvEmail = findViewById(R.id.tvEmail);

        btnInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogView = View.inflate(MainActivity.this, R.layout.dialog1, null);  // dialog xml을 변수에 담음
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
        });*/
    }
}
