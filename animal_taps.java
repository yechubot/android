package com.example.animaltapexample;

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
        tabHost = getTabHost();

        TabHost.TabSpec dog = tabHost.newTabSpec("dog").setIndicator("강아지");
        dog.setContent(R.id.dog);
        tabHost.addTab(dog);

        TabHost.TabSpec cat = tabHost.newTabSpec("cat").setIndicator("고양이");
        cat.setContent(R.id.cat);
        tabHost.addTab(cat);

        TabHost.TabSpec rabbit = tabHost.newTabSpec("rabbit").setIndicator("토끼");
        rabbit.setContent(R.id.rabbit);
        tabHost.addTab(rabbit);

        TabHost.TabSpec horse = tabHost.newTabSpec("horse").setIndicator("말");
        horse.setContent(R.id.horse);
        tabHost.addTab(horse);

    }
}
