package com.example.web_view_ex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText edtUrl, edtSearch;
    Button btnGo, btnPrev, btnSearchGo;
    WebView webView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("초간단 웹브라우저"); // 액션바 안만들고 제목만 바꾸려면 이렇게 할 수 있음, 제목만 가능
        edtUrl = findViewById(R.id.edtUrl);
        edtSearch = findViewById(R.id.edtSearch);
        btnGo = findViewById(R.id.btnGo);
        btnPrev = findViewById(R.id.btnPrev);
        btnSearchGo = findViewById(R.id.btnSearchGo);
        webView1 = findViewById(R.id.webView1);
        webView1.setWebViewClient(new MyWebViewClient()); //  익명으로 만듦
        WebSettings webSet = webView1.getSettings();
        webSet.setBuiltInZoomControls(true); //  on-screen zoom controls
        webView1.getSettings().setJavaScriptEnabled(true); // js 사용한 페이지는 js 수행되도록 설정
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            webView1.loadUrl(edtUrl.getText().toString());
            }
        });
        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webView1.goBack();
            }
        });
        btnSearchGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = edtSearch.getText().toString();
                webView1.loadUrl("https://terms.naver.com/search.nhn?query="+str+"&searchType=&dicType=&subject="); // 지식백과에서 검색한거로
            }
        });
    }

    class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return super.shouldOverrideUrlLoading(view, url);
        }
    }
}
