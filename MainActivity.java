package com.example.file_diary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    DatePicker dPicker1;
    EditText edtDiary;
    Button btnSave;
    String fileName;
    int cYear, cMonth, cDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("초간단 일기장");
        dPicker1 = findViewById(R.id.dPicker1);
        edtDiary = findViewById(R.id.edtDiary);
        btnSave = findViewById(R.id.btnSave);
        Calendar cal = Calendar.getInstance(); // 현재 날짜 가져와서 cal에 넣음
        cYear = cal.get(Calendar.YEAR);
        cMonth = cal.get(Calendar.MONTH);
        cDay = cal.get(Calendar.DAY_OF_MONTH);

        fileName = cYear + "_" + (cMonth + 1) + "_" + cDay+".txt"; // 오늘날짜일때, 앱 실행시 (날짜 바꿀 때 말고) 일기 읽어와야 함
        edtDiary.setText(readDiary(fileName));

        // Dpicker 초기화 메소드 , 현재 날짜를 가져와서 그걸로 초기화
        dPicker1.init(cYear, cMonth, cDay, new DatePicker.OnDateChangedListener() { // 날짜 바꿀 때
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                fileName = i + "_" + (i1 + 1) + "_" + i2 + ".txt"; //저장되는 파일 확장자 꼭 줘야함
                edtDiary.setText(readDiary(fileName));
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FileOutputStream fileOs = openFileOutput(fileName, MODE_PRIVATE);
                    String str = edtDiary.getText().toString();
                    fileOs.write(str.getBytes());
                    fileOs.close();
                    showToast(fileName + " 파일이 저장되었습니다.");
                    btnSave.setText("수정하기");
                } catch (IOException e) { // 입출력 관련 예외
                    showToast("파일을 저장할 수 없습니다.");
                }

            }
        });
    }

    //Toast method
    void showToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    //일기 읽어오는 메소드 ( 날짜 바뀔때, 앱 시작되자마자도 일기를 읽어와야함 --> 중복 --> 메소드를 만들어씀 )
    String readDiary(String fileName) {
        String diaryStr = null; //일기 내용 담을 변수

        //저장된 날짜에 일기가 있는지 확인하기
        try {
            FileInputStream fileIs = openFileInput(fileName);
            //byte txt[] = new byte[100]; // 100바이트까지만 불러옴 ( 메모리 한정되어 있을 때 큰 바이트는 에러 날 수 있는 경우 ..)
            byte txt[] = new byte[fileIs.available()];
            fileIs.read(txt);
            diaryStr = (new String(txt).trim()); // 익명으로 넣음
            fileIs.close();
            btnSave.setText("수정하기");
        } catch (IOException e) {
            edtDiary.setHint("일기 없음");
            btnSave.setText("새로 저장하기");
        }
        return diaryStr;

    }
}
