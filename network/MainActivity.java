package com.example.javatalk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    EditText edtMsg;
    Button btnSend;
    TextView tvRevMsg;
    InputStream is;
    OutputStream os;
    ObjectInputStream ois;
    ObjectOutputStream oos;
    Socket socket;
    String rMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);//통신 준비??
        setContentView(R.layout.activity_main);//xml 세팅 전에 통신 받아야함

        edtMsg = findViewById(R.id.edtMsg);
        btnSend = findViewById(R.id.btnSend);
        tvRevMsg = findViewById(R.id.tvRevMsg);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start();
                tvRevMsg.append(rMsg+"\n");
            }
        });
    }

    //통신 시작 메서드
    public void start() {
        try {
            socket = new Socket("192.168.2.38", 7070);//서버가서 문 열음(포트)
            sendMessage(socket);
            getMessage(socket);
        } catch (Exception e) {
            //통신 안됨
        } finally {
            {
                try {
                    socket.close();
                } catch (Exception e) {
                    //통신 안됨
                }
            }
        }
    }

    //서버로부터 자료를 받는 메서드
    public void getMessage(Socket socket) {
        try{
            is = socket.getInputStream();
            ois = new ObjectInputStream(is);
            rMsg =(String) ois.readObject(); // 객체 받음
        }catch (Exception e){
            //연결 귾김
        }
    }

    //자료를 서버로 보내는 메서드
    public void sendMessage(Socket socket) {
        try{
            os = socket.getOutputStream();
            oos = new ObjectOutputStream(os);
            String sMsg = "예은: "+edtMsg.getText().toString();
            oos.writeObject(sMsg); //객체 보냄
        }catch(Exception e){
            //연결끊김
        }
    }
}