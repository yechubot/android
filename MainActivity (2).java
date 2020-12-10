package com.example.animation1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageView corona;
    Animation animation, animation2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        corona = findViewById(R.id.corona);
        animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.show);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Toast.makeText(getApplicationContext(),"hey",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                animation2 = AnimationUtils.loadAnimation(MainActivity.this,R.anim.move);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        corona.startAnimation(animation);
    }
}
