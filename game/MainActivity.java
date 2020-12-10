package com.example.gameviewex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements AutoPermissionsListener {
    private static final String TAG = "game";
    FrameLayout framePreview;
    GameSurfaceView gameView;
    LinearLayout layout1;
    ImageView iv1;
    Button btnShow, btnHide;
    int imgs[] = {R.drawable.marvel4, R.drawable.marvel5, R.drawable.marvel6, R.drawable.marvel8};
    String coupon[] = {"라면 당첨!", "휴지 당첨", "10만원 당첨!", "10원 당첨!"};
    Display display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        framePreview = findViewById(R.id.framePreview);
        gameView = findViewById(R.id.gameView);
        display = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay();

        layout1 = findViewById(R.id.layout1);
        iv1 = findViewById(R.id.iv1);
        btnHide = findViewById(R.id.btnHide);
        btnShow = findViewById(R.id.btnShow);
        AutoPermissions.Companion.loadAllPermissions(this, 101);

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int max_value = 3;
                int min_value = 0;
                Random random = new Random();
                int randomNum = random.nextInt(max_value - min_value + 1) + min_value;
                // = (int) (Math.random()*img.length);
                iv1.setImageResource(imgs[randomNum]);
                iv1.setVisibility(View.VISIBLE);
                final int xOffset = (int) (Math.random() * (display.getWidth()-72));
                Log.d(TAG, "onClick: "+xOffset);
                final int yOffset = (int) (Math.random() * (display.getHeight()-500));
                //버튼이랑 텍스트뷰 높이 빼야함 150으로 부족해서 500빼니 계쏙 나옴
                Log.d(TAG, "onClick: "+yOffset);
                layout1.setPadding(xOffset, yOffset, 0, 0);

                iv1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int cou = (int) (Math.random() * coupon.length);
                        Toast.makeText(getApplicationContext(), coupon[cou],Toast.LENGTH_LONG ).show();
                    }
                });

            }
        });
        btnHide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv1.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        AutoPermissions.Companion.parsePermissions(this, requestCode, permissions, this);
    }

    @Override
    public void onDenied(int i, String[] strings) {

    }

    @Override
    public void onGranted(int i, String[] strings) {

    }
}
