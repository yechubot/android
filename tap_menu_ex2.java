package com.example.tap_ex2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements ActionBar.TabListener {

    ActionBar.Tab myPetTab[] = new ActionBar.Tab[4]; // 4개의 탭을 배열로 생성
    MyTabFragment myTabFrag[] = new MyTabFragment[4];
    // 내부 클래스 , static 으로 선언하여 어디서든? 사용가능하게
    static Integer iconIds[] = {R.drawable.icon_dog, R.drawable.icon_cat, R.drawable.icon_horse, R.drawable.icon_rabbit};
    static Integer imgIds[] = {R.drawable.dog, R.drawable.cat, R.drawable.horse, R.drawable.rabbit};
    static int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar bar = getSupportActionBar(); // action bar title
        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS); // navi mode로 bar 생성
        for (int i = 0; i < myPetTab.length; i++) {
            myPetTab[i] = bar.newTab();
            myPetTab[i].setIcon(iconIds[i]);
            myPetTab[i].setTabListener(this);
            bar.addTab(myPetTab[i]);
        }
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        MyTabFragment myFrag = null;
        int index = tab.getPosition();
        position = index;
        if (myTabFrag[index] == null) {
            myFrag = new MyTabFragment();
            Bundle data = new Bundle();
            data.putInt("iconID", iconIds[index]);
            myFrag.setArguments(data);
            myTabFrag[index] = myFrag;
        } else {
            myFrag = myTabFrag[index];
        }
        ft.replace(android.R.id.content, myFrag);
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    public static class MyTabFragment extends Fragment {
        int iconID;

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Bundle data = getArguments();
            iconID = data.getInt("iconID");
        }

        @Nullable
        @Override                 //매개 변수로 inflater 제공해줌
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            // 탭 눌렀을 때 보여줄 내용
            View myFragView = inflater.inflate(R.layout.tap_layout, null);
            ImageView ivPet = (ImageView) myFragView.findViewById(R.id.ivTabPet);
            if(iconID == iconIds[position]){
                ivPet.setImageResource(imgIds[position]);

            }
/*            if (iconID == R.drawable.icon_dog) {
                ivPet.setImageResource(R.drawable.dog);
            }
            if (iconID == R.drawable.icon_cat) {
                ivPet.setImageResource(R.drawable.cat);
            }
            if (iconID == R.drawable.icon_horse) {
                ivPet.setImageResource(R.drawable.horse);
            }
            if (iconID == R.drawable.icon_rabbit) {
                ivPet.setImageResource(R.drawable.rabbit);
            }*/
            return myFragView;
        }
    }
}
