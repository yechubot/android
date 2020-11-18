package com.example.pethospital;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Spinner spCity, spName;
    TextView tvResult;
    SQLiteDatabase sqlDB;
    List<String> siData, nameData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spCity = findViewById(R.id.spiCity);
        spName = findViewById(R.id.spName);
        tvResult = findViewById(R.id.tvResult);
        siData = new ArrayList<>();
        nameData = new ArrayList<>();
        //파일관련은 try catch로 물어보게 되어 있다.
        //데이터 존재해서 쓰는 경우 아래의 메소드로 변형해서 쓰면 된다.
        try {
            Boolean dbCheck = isCheckDB(this);
            if (!dbCheck) {
                copyDB(this);
            }
        } catch (Exception e) {
            showToast("can't save!");
        }
        //db 활용 ( file.exists() 에서 else에서 true에서 넘어오면 )
        sqlDB = SQLiteDatabase.openDatabase("/data/data/com.example.pethospital/databases/pethospitalDB.db", null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor1;
        nameData.clear();
        cursor1 = sqlDB.rawQuery("SELECT distinct(city) FROM petTBL;", null); // distinct를 사용하여 중복 배제
        while (cursor1.moveToNext()) {
            siData.add(cursor1.getString(0)); // 선택해서 가져올때는 선택한 것 부터 0
        }
        cursor1.close();
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item, siData);
        spCity.setAdapter(adapter);
        spCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor2;
                cursor2 = sqlDB.rawQuery("SELECT name FROM petTBL WHERE city='" + spCity.getSelectedItem().toString() + "';", null);
                while (cursor2.moveToNext()) {
                    nameData.add(cursor2.getString(0));
                }
                cursor2.close();
                ArrayAdapter<String> adapter1 = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item, nameData);
                spName.setAdapter(adapter1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    //DB 존재하는지, 크기가 다른지 확인(줄어들거나 커지면.. false를 돌려줌)
    public boolean isCheckDB(Context context) {
        String filePath ="/data/data/com.example.pethospital/databases/pethospitalDB.db";
        File file = new File(filePath);
        //나중에 커질 때 대비..?
        long newdb_size = 0;
        long olddb_size = file.length(); // 만들어지지 않았을 땐 0, copyDB후에는 해당 파일 크기
        AssetManager manager = context.getAssets();//assetManager: asset 폴더 관련일 처리 , 현재 액티비티에서 asset 정보 가져옴

        try {
            InputStream inputS = manager.open("pethospitalDB.db"); //raw 폴더..가져올 때?
            newdb_size = inputS.available();
        } catch (IOException e) {
            //폴더 or 파일 x
            showToast("no assets info!");
        }
        if (file.exists()) {
            if (newdb_size != olddb_size) {//존재 o , 크기 !=
                return false;
            } else { //크기 =
                return true;
            }
        }
        return false; //존재 x --> 처음일 때 (폴더도 없음)
    }

    //DB 복사
    public void copyDB(Context context) {
        AssetManager manager = context.getAssets();
        String folderPath = "/data/data/com.example.pethospital/databases/pethospitalDB.db";
        String filePath = "/data/data/com.example.pethospital/databases/pethospitalDB.db";
        File folder = new File(folderPath);
        File file = new File(filePath);
        FileOutputStream fileOS = null;
        BufferedOutputStream bufferOS = null;
        try {
            InputStream inputS = manager.open("pethospitalDB.db");
            BufferedInputStream bufferIS = new BufferedInputStream(inputS);
            if (!folder.exists()) {
                folder.mkdir(); // 폴더 존재 x -> 만들기
            }
            // 크기 !=
            if (file.exists()) {
                file.delete();
                file.createNewFile(); // 파일 만들지만 비어있다 <- 바이트로 넣어야함
            }
            fileOS = new FileOutputStream(file);
            bufferOS = new BufferedOutputStream(fileOS);
            int read = -1; //초기값 설정

            byte buffer[] = new byte[1024];//1024kb = 1mb  , inputS.available()..
            while ((read = bufferIS.read(buffer, 0, 1024)) != -1) {
                bufferOS.write(buffer, 0, read);
            }
            bufferOS.flush();
            bufferOS.close();


            fileOS.close();
            bufferIS.close();
            inputS.close();


        } catch (IOException e) {
            showToast("can't save!");
        }
    }

    public void showToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

}