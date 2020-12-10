package com.example.providerex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements AutoPermissionsListener {
    Button btn_call;
    TextView tv_call;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_call = findViewById(R.id.btn_call);
        tv_call = findViewById(R.id.tv_call);
        AutoPermissions.Companion.loadAllPermissions(this, 101);
        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_call.setText(getCallHistory());
            }
        });
    }
    //통화기록 content provider
    public String getCallHistory(){
        String[] callSet = new String[] {
                CallLog.Calls.DATE,
                CallLog.Calls.TYPE,
                CallLog.Calls.NUMBER,
                CallLog.Calls.DURATION
        };
        Cursor cursor = getContentResolver().query(
                CallLog.Calls.CONTENT_URI,
                callSet,
                null,
                null,
                null);
        Log.d("phone", "getCallHistory: "+cursor);
        if(cursor== null || cursor.equals("")){
            //새 폰인 경우
            return "통화 기록 없음";

        }else {
            //문자 담을 스트링버퍼 필요함
            StringBuffer callBuffer = new StringBuffer();
            callBuffer.append("날짜      구분       전화번호      통화시간\n");
            cursor.moveToFirst();
            do {
                long callDate = cursor.getLong(0);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String date_str = dateFormat.format(new Date(callDate));
                callBuffer.append(date_str + "     ");
                if (cursor.getInt(1) == CallLog.Calls.INCOMING_TYPE) {
                    callBuffer.append("수신  ");
                } else {
                    callBuffer.append("발신  ");
                }
                callBuffer.append(cursor.getString(2));
                callBuffer.append(cursor.getString(3)+"초");
            }while(cursor.moveToNext());
            cursor.close();
            return callBuffer.toString();
            }
        }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        AutoPermissions.Companion.parsePermissions(this,requestCode,permissions,this);
    }

    @Override
    public void onDenied(int i, String[] strings) {

    }

    @Override
    public void onGranted(int i, String[] strings) {

    }
}
