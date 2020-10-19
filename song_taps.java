package com.example.tapex1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TabActivity;
import android.os.Bundle;
import android.widget.TabHost;

public class MainActivity extends TabActivity {
    TabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabHost = getTabHost(); // tab menu 정보 가져옴
        // tab1
        TabHost.TabSpec tabSpecSong = tabHost.newTabSpec("SONG").setIndicator("음악별"); // tab에 보이는 label 설정
        tabSpecSong.setContent(R.id.tabSong); //붙일 내용
        tabHost.addTab(tabSpecSong);
        // tab2
        TabHost.TabSpec tabSpecArtist = tabHost.newTabSpec("ARTIST").setIndicator("가수별"); // tab에 보이는 label 설정
        tabSpecArtist.setContent(R.id.tabArtist);
        tabHost.addTab(tabSpecArtist);
        //tab3
        TabHost.TabSpec tabSpecAlbum = tabHost.newTabSpec("ALBUM").setIndicator("앨범별"); // tab에 보이는 label 설정
        tabSpecAlbum.setContent(R.id.tabAlbum);
        tabHost.addTab(tabSpecAlbum);
       // tabHost.setCurrentTab(0);실행하자마자 첫번째가 가장 먼저 보임 ( 디폴트) 숫자를 바꿔넣으면 탭 디폴트 설정 가능
    }
}
