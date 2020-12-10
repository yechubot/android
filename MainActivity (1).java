package com.example.broadex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ImageView iv_battery;
    TextView tv_battery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv_battery = findViewById(R.id.iv_battery);
        tv_battery = findViewById(R.id.tv_battery);

    }
    BroadcastReceiver br = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();// intent에서 상태 정보 받음
            if(action.equals(Intent.ACTION_BATTERY_CHANGED)){
                int remain = intent.getIntExtra(BatteryManager.EXTRA_LEVEL,0);
                tv_battery.setText("현재 충전량: "+remain + "%\n");

                if(remain>=98){
                    iv_battery.setImageResource(R.drawable.betteryfull);
                }else if(remain >=90){
                    iv_battery.setImageResource(R.drawable.bettery90);
                }else if(remain >=70){
                    iv_battery.setImageResource(R.drawable.bettery70);
                }else if(remain >=40){
                    iv_battery.setImageResource(R.drawable.bettery40);
                }else if(remain >=10){
                    iv_battery.setImageResource(R.drawable.bettery10);
                }else{
                    iv_battery.setImageResource(R.drawable.bettery0);
                }
                int plug = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED,0);
                switch (plug){
                    case 0:
                        tv_battery.append("전원 연결 안됨");
                        break;
                    case BatteryManager.BATTERY_PLUGGED_AC:
                        tv_battery.append("충전기 연결됨");
                        break;
                    case BatteryManager.BATTERY_PLUGGED_USB:
                        tv_battery.append("usb 연결됨");
                        break;
                }
            }

        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(br,filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(br);

    }
}