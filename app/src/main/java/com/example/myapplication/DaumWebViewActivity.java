package com.example.myapplication;


import android.content.Intent;

import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.webkit.JavascriptInterface;

import android.webkit.WebChromeClient;

import android.webkit.WebView;

import android.widget.TextView;




public class DaumWebViewActivity extends AppCompatActivity {
    private WebView daum_webView;

    private TextView daum_result;

    private Handler handler;



    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_daum_web_view);

        daum_result = (TextView) findViewById(R.id.daum_result);


        // WebView 초기화

        init_webView();


        // 핸들러를 통한 JavaScript 이벤트 반응

        handler = new Handler();

    }


    public void init_webView() {

        // WebView 설정

        daum_webView = (WebView) findViewById(R.id.daum_webview);
        // JavaScript 허용
        daum_webView.getSettings().setJavaScriptEnabled(true);


        // JavaScript의 window.open 허용

        daum_webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        daum_webView.addJavascriptInterface(new AndroidBridge(), "TestApp");
        daum_webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onCloseWindow(WebView window){

            }
        });


        // webview url load. php 파일 주소

        daum_webView.loadUrl("http://220.125.195.158:8080/indexx.php");

    }


    private class AndroidBridge {

        @JavascriptInterface

        public void setAddress(final String arg1, final String arg2, final String arg3) {

            handler.post(new Runnable() {

                @Override

                public void run() {
                    String input_add;
                    input_add= String.format(" %s %s", arg2, arg3);
                    daum_result.setText(String.format("%s %s", arg2, arg3));
                    Intent intent_ad = new Intent(getApplicationContext(), dbmupdate.class);
                    intent_ad.putExtra("input_address", input_add);
                    startActivity(intent_ad);
                    // WebView를 초기화 하지않으면 재사용할 수 없음

                    init_webView();

                }

            });

        }

    }
}
