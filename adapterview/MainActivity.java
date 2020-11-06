package com.example.adapterview;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    ListView list1;
    String pet[] = {"강아지", "고양이", "토끼", "말", "원숭이", "코끼리", "호랑이", "기린", "낙타", "표범", "돼지", "강아지", "고양이", "토끼", "말", "원숭이", "코끼리", "호랑이", "기린", "낙타", "표범", "돼지"};
    int petAge[] = {12, 7, 5, 10, 30, 40, 15, 35, 25, 10, 50, 12, 7, 5, 10, 30, 40, 15, 35, 25, 10, 50};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list1 = findViewById(R.id.list1);                       //context, 모양(직접 만드는 경우 레이아웃 폴더에 만들어서 inflate함
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, pet);//처음엔 simple_list_item_1
        list1.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);//checkbox보임 -> 제목밑에 체크항목들.. 만들기
        list1.setAdapter(adapter);
        list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), pet[position] + "의 최대 수명은 " + petAge[position] + " 입니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}