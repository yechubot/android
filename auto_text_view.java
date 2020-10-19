package com.example.autotextview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;

public class MainActivity extends AppCompatActivity {
    AutoCompleteTextView autoEdtView1;
    MultiAutoCompleteTextView multiAuto1;
    String words [] = {"able", "apple", "application", "bear", "bit", "byte", "car", "cable"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        autoEdtView1 = (AutoCompleteTextView) findViewById(R.id.autoEditView1);
        multiAuto1 = (MultiAutoCompleteTextView) findViewById(R.id.multiAuto1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line, words); // context-this-> 현재 액티비티 , layout, 담을 자료
        //simple_dropdown_item_1line -> 입력했을때 나오는 모양
        autoEdtView1.setAdapter(adapter); // 위에 만든 어댑터 장착
        MultiAutoCompleteTextView.CommaTokenizer tokenizer = new MultiAutoCompleteTextView.CommaTokenizer(); 
        multiAuto1.setTokenizer(tokenizer);
        multiAuto1.setAdapter(adapter);
    }
}
