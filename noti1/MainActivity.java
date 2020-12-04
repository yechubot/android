package com.example.noti1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btn_noti;
    String chID = "channel";
    NotificationCompat.Builder mBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_noti = findViewById(R.id.btn_noti);
        final Bitmap mIcon = BitmapFactory.decodeResource(getResources(), R.drawable.zoroicon);
        final PendingIntent pendingIntent = PendingIntent.getActivity(
                MainActivity.this,0,new Intent(getApplicationContext(),MainActivity.class),
                PendingIntent.FLAG_CANCEL_CURRENT); //noti활용 인텐트
        btn_noti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= 26) {
                    NotificationChannel channel = new NotificationChannel(chID,"공지",NotificationManager.IMPORTANCE_DEFAULT);
                    ((NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE)).createNotificationChannel(channel);
                    mBuilder = new NotificationCompat.Builder(MainActivity.this, chID);
                } else {
                    mBuilder = new NotificationCompat.Builder(MainActivity.this);
                }
                mBuilder.setSmallIcon(R.drawable.zoroicon)
                        .setContentTitle("noti")
                        .setContentText("i am a noti, i want to go home")
                        .setLargeIcon(mIcon).setAutoCancel(true)//사라지게 만들기
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setDefaults(Notification.DEFAULT_SOUND)
                        .setContentIntent(pendingIntent);
                NotificationManager notiMgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                notiMgr.notify(0,mBuilder.build());
            }
        });

    }
}
