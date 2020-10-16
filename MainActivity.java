package com.example.specialwidgetex;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Chronometer;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    Chronometer chrono;
    RadioButton rdoDate, rdoTime;
    //    CalendarView calendar;
    DatePicker datePick;
    TimePicker time;
    //    Button btnEnd, btnStart;
    TextView tvResult;
    int myYear, myMonth, myDate; // 캘린더 날짜 선택시 변수에 들어가게
//    GestureDetector detector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chrono = findViewById(R.id.chrono); // 캐스팅하는 순간 연결 -> 시간이 바로 가버림
        rdoDate = findViewById(R.id.rdoDate);
        rdoTime = findViewById(R.id.rdoTime);
        datePick = findViewById(R.id.datePick);
//        calendar = findViewById(R.id.calendar);
        time = findViewById(R.id.time);
/*        btnEnd = findViewById(R.id.btnEnd);
        btnStart = findViewById(R.id.btnStart);*/
        tvResult = findViewById(R.id.tvResult);
     /*   detector = new GestureDetector(this, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                return false;
            }

            @Override
            public void onShowPress(MotionEvent e) {

            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                chrono.stop();
                chrono.setTextColor(Color.RED);
                tvResult.setText(datePick.getYear() + "년" + datePick.getMonth() + "월" + datePick.getDayOfMonth() + "일" + time.getCurrentHour() + "시" + time.getCurrentMinute() + "분에 예약됨");
                rdoDate.setVisibility(View.INVISIBLE);
                rdoTime.setVisibility(View.INVISIBLE);
                datePick.setVisibility(View.INVISIBLE);
                time.setVisibility(View.INVISIBLE);

            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                return false;
            }
        });
*/
        chrono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chrono.setBase(SystemClock.elapsedRealtime());
                chrono.start();
                chrono.setTextColor(Color.BLUE);
                rdoDate.setVisibility(View.VISIBLE);
                rdoTime.setVisibility(View.VISIBLE);

            }
        });
/*        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chrono.setBase(SystemClock.elapsedRealtime()); // 예약시작 누를 때마다 chronometer 초기화 명령
                chrono.start();
                chrono.setTextColor(Color.BLUE);
            }
        });*/
        rdoDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePick.setVisibility(View.VISIBLE);
                time.setVisibility(View.INVISIBLE);
            }
        });
        rdoTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePick.setVisibility(View.INVISIBLE);
                time.setVisibility(View.VISIBLE);
            }
        });


//        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
//            @Override
//            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {// 찍은 날짜 여기 매개변수(변경 가능)에 들어감 -> 전역변수에 값 넘겨줘야 함
//                myYear = year;
//                myMonth = month + 1; // month 가 배열로 만들어짐, 1월이 0부터 시작
//
//
//                myDate = dayOfMonth;
//            }
//        });

/*        btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chrono.stop();
                chrono.setTextColor(Color.RED);
                tvResult.setText(myYear+"년"+myMonth+"월"+myDate+"일"+time.getCurrentHour()+"시"+time.getCurrentMinute()+"분에 예약됨");
            }
        });*/

/*        tvResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chrono.stop();
                chrono.setTextColor(Color.RED);
                tvResult.setText(datePick.getYear() + "년" + datePick.getMonth() + "월" + datePick.getDayOfMonth() + "일" + time.getCurrentHour() + "시" + time.getCurrentMinute() + "분에 예약됨");
            }
        });*/

        tvResult.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                chrono.stop();
                chrono.setTextColor(Color.RED);
                tvResult.setText(datePick.getYear() + "년" + datePick.getMonth() + "월" + datePick.getDayOfMonth() + "일" + time.getCurrentHour() + "시" + time.getCurrentMinute() + "분에 예약됨");
                rdoDate.setVisibility(View.INVISIBLE);
                rdoTime.setVisibility(View.INVISIBLE);
                datePick.setVisibility(View.INVISIBLE);
                time.setVisibility(View.INVISIBLE);
                return true;
            }
        });

    }
}
