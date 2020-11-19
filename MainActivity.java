package com.example.pethospital;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Spinner spCity, spName;
    TextView tvResult;
    SQLiteDatabase sqlDB;
    ArrayList<String> siData, nameData;
    String result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spCity=(Spinner)findViewById(R.id.spiCity);
        spName=(Spinner)findViewById(R.id.spName);
        siData=new ArrayList<String>();
        nameData=new ArrayList<String>();
        tvResult=(TextView)findViewById(R.id.tvResult);
        try {
            Boolean dbCheck=isCheckDB(this);
            if(!dbCheck){
                copyDB(this);
            }
        }catch (Exception e) {
            //파일 저장할 수 없습니다.
        }
        //DB활용
        sqlDB=SQLiteDatabase.openDatabase("/data/data/com.example.pethospital/databases/petHDB.db",
                null,SQLiteDatabase.OPEN_READONLY);
        Cursor cursor;
        cursor=sqlDB.rawQuery("SELECT distinct(city) FROM petHTBL;",null);
        while (cursor.moveToNext()) {
            siData.add(cursor.getString(0));
        }
        cursor.close();
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_spinner_dropdown_item,siData);
        spCity.setAdapter(adapter);
        spCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor1;
                nameData.clear();

                cursor1=sqlDB.rawQuery("SELECT name FROM petHTBL WHERE city='" +
                        spCity.getSelectedItem().toString()+"';",null);
                while (cursor1.moveToNext()) {
                    nameData.add(cursor1.getString(0));
                }
                cursor1.close();
                ArrayAdapter<String> adapter1=new ArrayAdapter<String>(MainActivity.this,
                        android.R.layout.simple_spinner_dropdown_item,nameData);
                spName.setAdapter(adapter1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor1;
                cursor1 = sqlDB.rawQuery("select * from petHTBL where name ='"+spName.getSelectedItem().toString()+"' and city='"+spCity.getSelectedItem().toString()+"';",null);
                cursor1.moveToFirst();
                String status;
                result="hospital name: "+cursor1.getString(1)+"\n";
                result+="open date:"+cursor1.getString(2);

                status = cursor1.getString(3);
                if(status.equals("폐업")||status.equals("말소")){
                    result +="("+status+":"+cursor1.getString(4)+")";
                }
                result+="\n";
                result+="tel: "+cursor1.getString(5)+"\n";
                result+="post-code: "+cursor1.getString(6)+"\n";
                result+="address: "+cursor1.getString(7);

                tvResult.setText(result);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    //DB가 존재하는지, 크기가 다른지
    public boolean isCheckDB(Context context) {
        String filePath="/data/data/com.example.pethospital/databases/petHDB.db";
        File file=new File(filePath);
        long newdb_size=0;
        long olddb_size=file.length();
        AssetManager manager=context.getAssets(); //assets 폴더에 접근
        try {
            InputStream inputS=manager.open("petHDB.db");
            newdb_size=inputS.available();
        }catch (IOException e) {
            //폴더x 파일x
        }
        if(file.exists()) {
            if(newdb_size!=olddb_size){
                return false;
            }else {
                return true;
            }
        }
        return false;
    }
    //DB복사
    public  void copyDB(Context context) {
        AssetManager manager=context.getAssets();
        String folderPath="/data/data/com.example.pethospital/databases";
        String filePath="/data/data/com.example.pethospital/databases/petHDB.db";
        File folder=new File(folderPath);
        File file=new File(filePath);
        FileOutputStream fileOS=null;
        BufferedOutputStream bufferOS=null;
        try {
            InputStream inputS=manager.open("petHDB.db");
            BufferedInputStream bufferIS=new BufferedInputStream(inputS);
            if(!folder.exists()) {
                folder.mkdir();
            }
            if(file.exists()) {
                file.delete();
                file.createNewFile();
            }
            fileOS=new FileOutputStream(file);
            bufferOS=new BufferedOutputStream(fileOS);
            int read=-1;
            byte buffer[]=new byte[1024];
            while ((read=bufferIS.read(buffer,0,1024)) != -1) {
                bufferOS.write(buffer,0,read);
            }
            bufferOS.flush();
            bufferOS.close();
            fileOS.close();
            bufferIS.close();
            inputS.close();
        }catch (IOException e) {
            //저장X
        }
    }
}
