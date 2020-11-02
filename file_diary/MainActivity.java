package com.example.file_diary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
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
    String sdPath;
    File myDir; //File은 파일과 폴더에 관련된 두 가지일을 다 한다.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("초간단 일기장");
        dPicker1 = findViewById(R.id.dPicker1);
        edtDiary = findViewById(R.id.edtDiary);
        btnSave = findViewById(R.id.btnSave);

        //오늘 날짜 가져오기
        Calendar cal = Calendar.getInstance();
        cYear = cal.get(Calendar.YEAR);
        cMonth = cal.get(Calendar.MONTH);
        cDay = cal.get(Calendar.DAY_OF_MONTH);

        if (Build.VERSION.SDK_INT >= 23) { // 이전에 퍼미션 체크했는지 확인
            int permission_check = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (permission_check == PackageManager.PERMISSION_DENIED) {// 허락 안한 경우 요청함
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);
            } else { // 이전에 허락한 경우 sd 카드 읽어오는 메소드 호출
                sdcardRead();
            }
        }
        fileName = cYear + "_" + (cMonth + 1) + "_" + cDay + ".txt"; // 오늘날짜일때, 앱 실행시 (날짜 바꿀 때 말고) 일기 읽어와야 함
//        edtDiary.setText(readDiary(fileName)); sd 카드에 접근도 안했는데 읽어올 수 없으니 sd읽어오고(메소드 호출뒤) 난 뒤에 써야함

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
//                  FileOutputStream fileOs = openFileOutput(fileName, MODE_PRIVATE); 내장 메모리 읽어오는 명령
                    FileOutputStream fileOs = new FileOutputStream(sdPath+fileName);
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
            FileInputStream fileIs = new FileInputStream(fileName);
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
    @Override
    // 퍼미션 결과를 받는 메소드                                                            //퍼미션 결과 받는 변수
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
            finish();
        } else {
            //허용한 경우
            sdcardRead();
        }
    }
    void sdcardRead() {
        sdPath = Environment.getExternalStorageDirectory().getAbsolutePath();// 허락해야 절대 경로 얻어옴
        sdPath += "/myDiary/"; // myDiary 폴더 안에 저장하려고 함 , 마지막 슬래시 까지 해야 안에 저장됨 (안하면 밖에 저장됨)
        myDir = new File(sdPath);// 저장 경로를 확보하기 위해 File만듦
        //폴더가 없으면 만들기
        if (!myDir.isDirectory()) { // 폴더 존재하지 않는지 ?
            myDir.mkdir();
        }
        edtDiary.setText(readDiary(fileName));
    }
}


