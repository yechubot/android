package com.example.tapex;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements ActionBar.TabListener {
    ActionBar.Tab tabSong, tabArtist, tabAlbum;
    MyTapFragment myFrags[] = new MyTapFragment[3]; // 탭 3개, 탭 별로 fragment 만듦

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        ActionBar bar = getSupportActionBar(); // action bar title
        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS); // navi mode로 bar 생성
        tabSong = bar.newTab(); // bar 새 탭 생성
        tabSong.setText("음악별"); // tab 이름
        tabSong.setTabListener(this);// 해당 activity 에서 반응하도록
        bar.addTab(tabSong);

        tabArtist = bar.newTab();
        tabArtist.setText("가수별");
        tabArtist.setTabListener(this);
        bar.addTab(tabArtist);

        tabAlbum = bar.newTab();
        tabAlbum.setText("앨범별");
        tabAlbum.setTabListener(this);
        bar.addTab(tabAlbum);

    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        // 탭 셀렉시 발생
        MyTapFragment myTapFragment = null;
        if(myFrags[tab.getPosition()]==null){
            // 객체의 초기값은 null 이니까
            myTapFragment = new MyTapFragment();
            Bundle data = new Bundle();
            data.putString("tabName",tab.getText().toString());// key, value
            myTapFragment.setArguments(data);// 매개변수 setting
            myFrags[tab.getPosition()] = myTapFragment;
            //한번씩 누르고 나면 null 값이 아니니까 else로 이동
        }else {
            myTapFragment = myFrags[tab.getPosition()];
        }
        ft.replace(android.R.id.content, myTapFragment);
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    public static class MyTapFragment extends Fragment {
        String tabName;

        //미니 화면을 생성할 때 기본적으로 정의할 것
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Bundle data = getArguments();
            tabName = data.getString("tabName"); // 해당 키의 값을 받아옴
        }

        // 탭을 눌렀을 때 보여주고 싶은 위젯 정의.. 작은 화면 생성?
        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            LinearLayout layout = new LinearLayout(super.getActivity()); // 부모에서 얻은 액티비티 위에 생성
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.setLayoutParams(params); // params 크기로 레이아웃 설정
            if (tabName.equals("음악별")) { // or switch문으로
                layout.setBackgroundColor(Color.YELLOW);
            }
            if (tabName.equals("가수별")) {
                layout.setBackgroundColor(Color.BLUE);
            }
            if (tabName.equals("앨범별")) {
                layout.setBackgroundColor(Color.GREEN);
            }
            return layout; // 내가 만든 레이아웃 리턴
           // return super.onCreateView(inflater, container, savedInstanceState);
        }
    }
}
