package com.example.toast_ex1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import javax.xml.transform.Templates;

public class MainActivity extends AppCompatActivity {
    Button btnMsg, btnSnack;
    Toast toast;
    View toastView;
    TextView toast_tvMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnMsg = findViewById(R.id.btnMsg);
        btnSnack = findViewById(R.id.btnSnack);
        //toast_tvMsg = findViewById(R.id.toast_tvMsg); --> 메인화면에서 해당 위젯 찾을 수 없기 때문에 여기서 하면 안됨!

        btnMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // application context는 하나뿐
                //Toast.makeText(getApplicationContext(),"cookie!",Toast.LENGTH_SHORT).show();
                // main activity 에 뜨게, 장소 명확히 ( activity  여러개니까)
                toast = Toast.makeText(MainActivity.this, "cookie", Toast.LENGTH_SHORT); // 만든 Toast를 toast에 넣음
             /* Display display = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay();// 핸드폰 화면 크기를 가져옴
                int x = (int) (Math.random()*display.getWidth());
                int y = (int)(Math.random()*display.getHeight());
                toast.setGravity(Gravity.TOP | Gravity.LEFT,x,y);// toast 가 보이는 위치 설정 */

                toast = new Toast(MainActivity.this); //새로운 toast 객체 생성
                toastView = (View) View.inflate(MainActivity.this, R.layout.toast, null); // toast.xml을 toastView에 넣음
                toast_tvMsg = (TextView)toastView.findViewById(R.id.toast_tvMsg); // toastView에서 찾아야함 ( 안그러면 메인에서 찾음)
                toast.setView(toastView); // 내가 만든 toastView 세팅
                toast.show(); // toast 보임
            }
        });
        btnSnack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"snack bar", BaseTransientBottomBar.LENGTH_LONG).show();
            }
        });
    }


}
