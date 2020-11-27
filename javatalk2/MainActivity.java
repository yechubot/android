package com.example.javatalk2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listChat;
    EditText edtSendMsg;
    ImageView ivSendBtn;
    ArrayList<ChatMessage> arrayList;
    MyAdapter adapter;
    boolean flagConnection=true;
    boolean isConnection=false;
    boolean flagRead=true;
    Handler writeHandler;
    Socket socket;
    BufferedInputStream bis;
    BufferedOutputStream bos;
    SocketThread socketThread;
    ReadThread readThread;
    WriteThread writeThread;
    String serverIp="192.168.2.38";
    int serverPort=7070;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listChat=(ListView)findViewById(R.id.listChat);
        edtSendMsg=(EditText)findViewById(R.id.edtSendMsg);
        ivSendBtn=(ImageView)findViewById(R.id.ivSendBtn);
        arrayList=new ArrayList<ChatMessage>();
        adapter=new MyAdapter(this, R.layout.chat_item,arrayList);
        listChat.setAdapter(adapter);
        ivSendBtn.setEnabled(false);
        edtSendMsg.setEnabled(false);
        ivSendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message msg=new Message();
                msg.obj=edtSendMsg.getText().toString();
                writeHandler.sendMessage(msg);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        socketThread=new SocketThread();
        socketThread.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        flagConnection=false;
        isConnection=false;
        if(socket != null){
            flagRead=false;
            writeHandler.getLooper().quit();
            try {
                bis.close();
                bos.close();
                socket.close();
            }catch (IOException e) {
                showToast("오류가 발생");
            }
        }
    }
    Handler mainHandler=new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if(msg.what==10) {
                showToast("서버와 연결되었음");
                edtSendMsg.setEnabled(true);
                ivSendBtn.setEnabled(true);
            }else if(msg.what==20){
                showToast("서버와 연결실패!!");
                edtSendMsg.setEnabled(false);
                ivSendBtn.setEnabled(false);
            }else if(msg.what==100){
                //메시지를 읽는 중...
                addMessage("you",(String)msg.obj);
            }else if(msg.what==200) {
                addMessage("me",(String)msg.obj);
            }
        }
    };
    private void addMessage(String friendMsg, String msg) {
        ChatMessage cm=new ChatMessage();
        cm.friendMsg=friendMsg;
        cm.msg=msg;
        arrayList.add(cm);
        adapter.notifyDataSetChanged();
        listChat.setSelection(arrayList.size()-1);
    }
    void showToast(String msg) {
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
    }
    class ChatMessage {
        String friendMsg;
        String msg;
    }
    class MyAdapter extends ArrayAdapter<ChatMessage> {
        ArrayList<ChatMessage> arrayList;
        int resource;
        Context context;
        public MyAdapter(@NonNull Context context, int resource,
                         ArrayList<ChatMessage> arrayList) {
            super(context, resource,arrayList);
            this.context=context;
            this.resource=resource;
            this.arrayList=arrayList;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater inflater=(LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(resource,null);
            TextView msgView=(TextView)convertView.findViewById(R.id.tvItemMsg);
            RelativeLayout.LayoutParams params=
                    (RelativeLayout.LayoutParams)msgView.getLayoutParams();
            ChatMessage msg=arrayList.get(position);
            if(msg.friendMsg.equals("me")){
                params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,RelativeLayout.TRUE);
                msgView.setTextColor(Color.WHITE);
                msgView.setBackgroundResource(R.drawable.chat_right);
            }else if(msg.friendMsg.equals("you")){
                params.addRule(RelativeLayout.ALIGN_PARENT_LEFT,RelativeLayout.TRUE);
                msgView.setBackgroundResource(R.drawable.chat_left);
            }
            msgView.setText(msg.msg);
            return convertView;
        }
    }
    class SocketThread extends Thread {
        @Override
        public void run() {
            while (flagConnection){
                try {
                    if(!isConnection) {
                        socket=new Socket();
                        SocketAddress remoteAddr=new InetSocketAddress(serverIp,serverPort);
                        socket.connect(remoteAddr,10000);
                        bis=new BufferedInputStream(socket.getInputStream());
                        bos=new BufferedOutputStream(socket.getOutputStream());
                        if(readThread != null){
                            flagRead=false;
                        }
                        if(writeThread != null){
                            writeHandler.getLooper().quit();
                        }
                        writeThread=new WriteThread();
                        writeThread.start();
                        readThread=new ReadThread();
                        readThread.start();
                        isConnection=true;
                        Message msg=new Message();
                        msg.what=10;
                        mainHandler.sendMessage(msg);
                    }else {
                        SystemClock.sleep(10000);
                    }
                }catch (Exception e) {
                    showToast("서버와 연결중...");
                    SystemClock.sleep(10000);
                }
            }
        }
    }
    class WriteThread extends Thread {
        @Override
        public void run() {
            Looper.prepare();
            writeHandler=new Handler() {
                @Override
                public void handleMessage(@NonNull Message msg) {
                    try {
                        bos.write(((String)msg.obj).getBytes());
                        bos.flush();
                        Message message=new Message();
                        message.what=200;
                        message.obj=msg.obj;
                        mainHandler.sendMessage(message);
                    }catch (Exception e) {
                        isConnection=false;
                        writeHandler.getLooper().quit();
                        try {
                            flagRead=false;
                        }catch (Exception e1) {
                            showToast("전송 중 에러 발생!!");
                        }
                    }
                }
            };
            Looper.loop();
        }
    }
    class ReadThread extends Thread {
        @Override
        public void run() {
            byte[] buffer=null;
            while (flagRead){
                buffer=new byte[1024];
                try {
                    String message=null;
                    int size=bis.read(buffer);
                    if(size>0){
                        message=new String(buffer,0,size,"utf-8");
                        if(message !=null &&  !message.equals("")) {
                            Message msg=new Message();
                            msg.what=100;
                            msg.obj=message;
                            mainHandler.sendMessage(msg);
                        }else {
                            flagRead=false;
                            isConnection=false;
                        }
                    }
                }catch (Exception e) {
                    flagRead=false;
                    isConnection=false;
                    showToast("서버에 보내는 중 에러!!");
                }
            }
            Message msg=new Message();
            msg.what=20;
            mainHandler.sendMessage(msg);
        }
    }
}
