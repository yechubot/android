package com.example.tap_ex3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    TabLayout tabs;
    ViewPager viewPager1;
    MyPageAdapter adapter;
    //이미지 넣고 싶을 때 배열로 id 넣고, int position  선언

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabs = (TabLayout)findViewById(R.id.tabs);
        viewPager1 = (ViewPager)findViewById(R.id.viewPager1);
        tabs.addTab(tabs.newTab().setText("동물선택"));
        tabs.addTab(tabs.newTab().setText("인사"));
        tabs.addTab(tabs.newTab().setText("비만도 체크"));
        tabs.setTabGravity(tabs.GRAVITY_FILL);// 새 탭 채움
        //어댑터 설정
        adapter = new MyPageAdapter(getSupportFragmentManager(), 3);//어댑터 몇 개 생성
        //어댑터 장착
        viewPager1.setAdapter(adapter);
        //탭 메뉴를 클릭하면 뷰 페이저에 해당 프레그먼트 변경(어댑터를 가지고 장착)
        tabs.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager1));

       /* + 탭 선택시 이미지 넣고 싶을 때
       tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                position = tab.getPosition();
                백그라운드 아이디.setBackgroundResource(배열[position]);..
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });*/
        //뷰페이저를 바꾸면 해당 탬 메뉴 변경 (밀때 탭 메뉴가 옮겨지도록)
        viewPager1.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));
    }
}
