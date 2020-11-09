package com.example.gridviewex;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    GridView gridView1;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView1 = (GridView) findViewById(R.id.gridView1);
        adapter = new MyAdapter(this);//main activity
        gridView1.setAdapter(adapter);

    }

    public class MyAdapter extends BaseAdapter {
        Integer[] posterID = {R.drawable.mov01, R.drawable.mov02, R.drawable.mov03, R.drawable.mov04, R.drawable.mov05, R.drawable.mov06, R.drawable.mov07, R.drawable.mov08, R.drawable.mov09, R.drawable.mov10};
        String[] posterTitle = {"써니", "완득이", "괴물", "라디오스타", "비열한거리", "왕의남자", "아일랜드", "웰컴투동막골", "헬보이", "백투더퓨쳐"};
        Context context;

        public MyAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return posterID.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View view, ViewGroup viewGroup) {//격자 무늬에 들어갈 뷰를 정의하는 곳
            ImageView imgPoster = new ImageView(context);
            DisplayMetrics dm = getApplicationContext().getResources().getDisplayMetrics();
            int width = dm.widthPixels / 3;
            int height = dm.heightPixels / 3;
            imgPoster.setLayoutParams(new GridView.LayoutParams(width, height));//포스터가 들어갈 크기 설정
            imgPoster.setScaleType(ImageView.ScaleType.FIT_CENTER);// 이미지가 중간에 와서 여백이 생기게
            imgPoster.setPadding(5, 5, 5, 5);
            imgPoster.setImageResource(posterID[position]);
            imgPoster.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    View dialogView = (View) View.inflate(MainActivity.this, R.layout.dialog, null);
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    ImageView dlgPoster = dialogView.findViewById(R.id.dlgPoster);
                    dlgPoster.setImageResource(posterID[position]);
                    builder.setTitle(posterTitle[position]);
                    builder.setIcon(R.drawable.movie_icon);
                    builder.setView(dialogView);
                    builder.setPositiveButton("닫기",null);
                    builder.show();
                }
            });
            return imgPoster;
        }


    }
}
