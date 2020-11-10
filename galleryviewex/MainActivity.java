package com.example.galleryviewex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    Gallery gallery1;
    ImageView image_view_big;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gallery1 = findViewById(R.id.gallery1);
        image_view_big = findViewById(R.id.image_view_big);
        GalleryAdapter adapter = new GalleryAdapter(this);
        gallery1.setAdapter(adapter);
    }

    public class GalleryAdapter extends BaseAdapter {
        Context context;
        int posterId[] = {R.drawable.mov01, R.drawable.mov02, R.drawable.mov03, R.drawable.mov04, R.drawable.mov05, R.drawable.mov06, R.drawable.mov07, R.drawable.mov08, R.drawable.mov09, R.drawable.mov10};
        public GalleryAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return posterId.length;
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
        public View getView(final int position, View view, ViewGroup viewGroup) {
            ImageView imageView_poster = new ImageView(context);//얘를 클릭하는 거
            imageView_poster.setLayoutParams(new Gallery.LayoutParams(400,500));
            imageView_poster.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView_poster.setPadding(5,5,5,5);
            imageView_poster.setImageResource(posterId[position]);
/*            imageView_poster.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    image_view_big.setImageResource(posterId[position]);
                }
            });*/ // 사진은 보이지만 갤러리뷰가 넘어가지 않음
            imageView_poster.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    image_view_big.setImageResource(posterId[position]);
                    return false;// true하면 안움직임
                }
            });
            return imageView_poster;
        }
    }
}
