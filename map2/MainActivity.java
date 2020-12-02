package com.example.petmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    Spinner spCity, spName;
    TextView tvResult;
    boolean dbCheck;
    SQLiteDatabase sqlDB;
    List<String> city, name;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        spCity = (Spinner) findViewById(R.id.spCity);
        spName = (Spinner) findViewById(R.id.spName);
        tvResult = (TextView) findViewById(R.id.tvResult);
        city = new ArrayList<String>();
        name = new ArrayList<String>();
        try {
            dbCheck = isCheckDB(this);
            if (!dbCheck) {
                copyDB(this);
            }
        } catch (Exception e) {
            showToast(e.getMessage());
        }
        sqlDB = SQLiteDatabase.openDatabase("/data/data/com.example.petmanager/databases/petDB.db", null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor;
        cursor = sqlDB.rawQuery("SELECT DISTINCT city FROM petTBL", null);
        while (cursor.moveToNext()) {
            city.add(cursor.getString(0));
        }
        cursor.close();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, city);
        spCity.setAdapter(adapter);
        spCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                name.clear();
                Cursor cursor;
                cursor = sqlDB.rawQuery("SELECT name FROM petTBL WHERE city='" + spCity.getSelectedItem().toString() + "';", null);
                while (cursor.moveToNext()) {
                    name.add(cursor.getString(0));
                }
                cursor.close();
                ArrayAdapter<String> nameadapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, name);
                spName.setAdapter(nameadapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mMap.clear();
                Cursor cursor;
                cursor = sqlDB.rawQuery("SELECT name,data,status,closedata,tel,post,address,Y,X FROM petTBL WHERE name='" + spName.getSelectedItem().toString() + "' AND city='"+spCity.getSelectedItem().toString()+"';", null);
                cursor.moveToFirst();
                ArrayList<String> selectQ = new ArrayList<String>();
                LatLng tourPos = new LatLng(cursor.getDouble(7), cursor.getDouble(8));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tourPos, 15));
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(tourPos);
                markerOptions.title(cursor.getString(0));
                markerOptions.snippet(cursor.getString(4));
                mMap.addMarker(markerOptions).showInfoWindow();


                if (cursor.getString(2).equals("정상")) {
                    tvResult.setText(
                            "병원이름 :" + cursor.getString(0) + "\n" +
                                    "개업일 : " + cursor.getString(1) + "\n" +
                                    "전화번호 : " + cursor.getString(4) + "\n" +
                                    "우편번호 : " + cursor.getString(5) + "\n" +
                                    "주소 : " + cursor.getString(6));
                    mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                        @Override
                        public void onInfoWindowClick(Marker marker) {
                            Uri uri = Uri.parse("tel:" + cursor.getString(4));
                            Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                            startActivity(intent);
                        }
                    });

                    // 출력 병원이름 개업일 전화번호 우편번호 주소
                } else if (cursor.getString(2).equals("폐업")) {
                    tvResult.setText(
                            "병원이름 :" + cursor.getString(0) + "\n" +
                                    "개업일 : " + cursor.getString(1) + "\n" +
                                    "폐업일 : " + cursor.getString(3) + "\n");

                    // 출력 병원이름 개업일(페업일)
                }else{
                    tvResult.setText("병원이름 : "+cursor.getString(0));
                    // 출력 병원이름
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void showToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }

    //DB가 존재하는지, 크기가 다른지 검사
    public boolean isCheckDB(Context context) {
        String filePath = "/data/data/com.example.petmanager/databases/petDB.db";
        File file = new File(filePath);
        long newdb_size = 0;
        long olddb_size = file.length();
        AssetManager manager = context.getAssets(); //asset 폴더에 접근
        try {
            InputStream inputS = manager.open("petDB.db");
            newdb_size = inputS.available();
        } catch (IOException e) {
            //폴더x 파일x
            showToast(e.getMessage() + " isCheckDB");
        }
        if (file.exists()) {
            if (newdb_size != olddb_size) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    //DB 복사
    public void copyDB(Context context) {
        AssetManager manager = context.getAssets();
        String folderPath = "/data/data/com.example.petmanager/databases";
        String filePath = "/data/data/com.example.petmanager/databases/petDB.db";
        File folder = new File(folderPath);
        File file = new File(filePath);
        FileOutputStream fileOS = null;
        BufferedOutputStream bufferOS = null;
        try {
            InputStream inputS = manager.open("petDB.db");
            BufferedInputStream bufferIS = new BufferedInputStream(inputS);
            if (!folder.exists()) {
                folder.mkdir();
            }
            if (file.exists()) {
                file.delete();
                file.createNewFile();
            }
            fileOS = new FileOutputStream(file);
            bufferOS = new BufferedOutputStream(fileOS);
            int read = -1;
            byte buffer[] = new byte[1024];
            while ((read = bufferIS.read(buffer, 0, 1024)) != -1) {
                bufferOS.write(buffer, 0, read);
            }
            bufferOS.flush();
            bufferOS.close();
            fileOS.close();
            bufferIS.close();
            inputS.close();

        } catch (IOException e) {
            showToast(e.getMessage() + "  copy DB");

        }

    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }
}