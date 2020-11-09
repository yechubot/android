package com.example.listview2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText edtProduct;
    Button btnAdd;
    ListView listPd;
    ArrayList<String> productList;//동적 배열 선언
    int pos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtProduct = (EditText) findViewById(R.id.edtProduct);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        listPd = (ListView) findViewById(R.id.listPd);
        productList = new ArrayList<>(); // 동적 배열 생성
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, productList);
        listPd.setAdapter(adapter);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productList.add(edtProduct.getText().toString());// 항목 추가(추가할 때는 내용을 쓰고)
                adapter.notifyDataSetChanged();// 어댑터를 다시 세팅 (새로고침) -> 세팅된 자료가 나오게
                edtProduct.setText("");//입력하고나면 빈칸
            }
        });
/*        listPd.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {

                productList.remove(position);// 항목 삭제(위치로 표시)
                adapter.notifyDataSetChanged(); //새로고침해서 삭제된걸 반영함
                return false;
            }
        });*/
        //뗄 때 대화상자(항목삭제가 제목, 삭제 예, 아니오)
        listPd.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                pos = position;
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("항목 삭제");
                builder.setMessage("선택한 항목을 삭제하시겠습니까?");
                builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        productList.remove(pos);
                        adapter.notifyDataSetChanged();
                        Toast.makeText(getApplicationContext(),"삭제되었습니다.",Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("아니오", null);
                builder.show();
                return false;
            }
        });

    }
}
