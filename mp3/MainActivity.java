package com.example.mp3player;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    Button btnOpen, btnStop,  btnPause;
    boolean isPaused = false;
    TextView tvMusic;
    ProgressBar pbBar;
    ArrayList<String> mp3list;
    String selectedMp3;
    MediaPlayer mPlayer;
    String mp3Path;
    File mp3File[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listMp3);
        btnOpen = findViewById(R.id.btnOpen);
        btnStop = findViewById(R.id.btnStop);
        btnPause = findViewById(R.id.btnPause);
        tvMusic = findViewById(R.id.tvPlayMpe3);
        pbBar = findViewById(R.id.pbMp3);

        if (Build.VERSION.SDK_INT >= 23) { // 이전에 퍼미션 체크했는지 확인
            int permission_check = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (permission_check == PackageManager.PERMISSION_DENIED) {// 허락 안한 경우 요청함
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);
            } else { // 이전에 허락한 경우 sd 카드 읽어오는 메소드 호출
                sdcardRead();
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, mp3list);
        listView.setAdapter(adapter);
        listView.setChoiceMode(listView.CHOICE_MODE_SINGLE);//버튼 하나씩만 선택 가능

        listView.setItemChecked(0, true);//아무것도 체크 안된 상태로..position이 체크된 값
        selectedMp3 = mp3list.get(0);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //position이 찍은 값 받음
                selectedMp3 = mp3list.get(position);
            }
        });
        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mPlayer = new MediaPlayer();
                    mPlayer.setDataSource(mp3Path + selectedMp3);
                    mPlayer.prepare();//누를 때마다 음악 준비
                    mPlayer.start();
                    btnStop.setEnabled(true);
                    btnOpen.setEnabled(false);
                    btnPause.setEnabled(true);
                    tvMusic.setText("실행중인 음악 : "+selectedMp3);
                    pbBar.setVisibility(View.VISIBLE);
                } catch (IOException e) {
                    showToast("재생할 수 없습니다.");
                }
            }
        });
        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //button글씨로 물어볼 수도 있음
                if(isPaused==false){
                    mPlayer.pause();
                    btnPause.setText("이어듣기");
                    pbBar.setVisibility(View.INVISIBLE);
                    isPaused = false;
                }else {
                    mPlayer.start();
                    btnPause.setText("일시정지");
                    pbBar.setVisibility(View.VISIBLE);
                    isPaused = true;
                }
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPlayer.stop();
                mPlayer.reset();//다시 시작할 수 있게
                btnOpen.setEnabled(true);
                btnStop.setEnabled(false);
                btnPause.setEnabled(false);
                btnPause.setText("일시정지");
                tvMusic.setText("실행중인 음악 : ");
                pbBar.setVisibility(View.INVISIBLE);
            }
        });
    }

    void showToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    // 퍼미션 결과를 받는 메소드                                                            //퍼미션 결과 받는 변수
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
            finish();//거부했으니 꺼버리기
        } else {
            //허용한 경우
            sdcardRead();
        }
    }

    void sdcardRead() {
        mp3Path = Environment.getExternalStorageDirectory().getPath() + "/Music/";//sd카드 절대경로 안의 music 파일에 음악파일 있음
        mp3list = new ArrayList<>(); // 동적 배열 생성
        mp3File = new File(mp3Path).listFiles();//music안에있는 음악파일 목록 배열에 들어감
        //음악 파일만 들어가게
        String fileName, extName;
        for (File file : mp3File) {
            fileName = file.getName();
            extName = fileName.substring(fileName.length() - 3); //확장자만 가져옴
            if (extName.equals("mp3")) {
                mp3list.add(fileName);//음악 파일만 추가됨
            }
        }
    }
}
