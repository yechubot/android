package com.example.raw_ex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    Button btnCorona;
    TextView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCorona = findViewById(R.id.btnCorona);
        tvContent = findViewById(R.id.tvContent);
        btnCorona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //파일관련 try, catch 모두 요구
                try{
                    InputStream inputS = getResources().openRawResource(R.raw.corona);
                    byte txt[] = new byte[inputS.available()];
                    inputS.read(txt);
                    tvContent.setText(new String(txt));
                    inputS.close();
                }catch(IOException e){
                    Toast.makeText(getApplicationContext(),"해당 파일을 읽을 수 없습니다.",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
