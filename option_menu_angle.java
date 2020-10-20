package com.example.option_menu_ex2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText inputAngle;
    ImageView img;
    int angle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img = findViewById(R.id.img);
        inputAngle = findViewById(R.id.inputAngle);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       super.onCreateOptionsMenu(menu);
        MenuInflater inflater =  getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.rotate:
               angle = Integer.parseInt(inputAngle.getText().toString());
               img.setRotation(angle);
               break;
            case R.id.first:
                img.setImageResource(R.drawable.jeju9);
                if(item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                break;
            case R.id.second:
                img.setImageResource(R.drawable.chuja);
                if(item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                break;
            case R.id.third:
                img.setImageResource(R.drawable.bam);
                if(item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
