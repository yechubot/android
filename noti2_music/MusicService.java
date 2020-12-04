package com.example.serviceex;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class MusicService extends Service {
    MediaPlayer mp;
    private static final String TAG = "service";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        startForegroundService();
        Log.i(TAG, "onCreate:");
    }

    @Override
    public void onDestroy() {
        mp.stop();
        super.onDestroy();
        Log.i(TAG, "onDestroy:");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand: 서비스 수행");
        mp = MediaPlayer.create(this,R.raw.like);
        mp.setLooping(true);
        mp.start();
        return super.onStartCommand(intent, flags, startId);
    }

    void startForegroundService(){
        Intent notiIntent = new Intent(this,MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,notiIntent,0);
        RemoteViews remoteViews = new RemoteViews(getPackageName(),R.layout.noti);
        NotificationCompat.Builder builder;
        if(Build.VERSION.SDK_INT>=26){
            String chID = "musicChannel";
            NotificationChannel channel = new NotificationChannel(chID,"음악채널",
                    NotificationManager.IMPORTANCE_DEFAULT);
            ((NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE)).createNotificationChannel(channel);
        builder = new NotificationCompat.Builder(this,chID);
        }else {
            builder = new NotificationCompat.Builder(this);
        }
        builder.setSmallIcon(R.drawable.ic_launcher_background)
                .setContent(remoteViews)
                .setContentIntent(pendingIntent);
        startForeground(1,builder.build());
    }
}
