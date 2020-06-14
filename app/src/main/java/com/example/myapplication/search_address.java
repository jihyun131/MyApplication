package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.TextView;

public class search_address extends AppCompatActivity {
    private WebView daum_webView;
    private TextView daum_result;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_address);
        daum_result=(TextView) findViewById(R.id.daum_result);


        //WebView 초기화
        init_webView();

        handler=new Handler();
    }
    public void init_webView(){
        WebView daum_webView = (WebView) findViewById(R.id.daum_webview);
        daum_webView.getSettings().setJavaScriptEnabled(true);
        daum_webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        daum_webView.addJavascriptInterface(new AndroidBridge(), "TestApp");
        daum_webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onCloseWindow(WebView window){

            }
        });
        daum_webView.loadUrl("http://220.125.195.158:8080/indexx.php");
    }
    private class AndroidBridge{
        @JavascriptInterface
        public void setAddress(final String arg1, final String arg2, final String arg3){
            handler.post(new Runnable(){
                @Override
                public void run(){
                    String input_Aaa;
                    input_Aaa= String.format("%s %s", arg2, arg3);
                    daum_result.setText(String.format("%s %s", arg2, arg3));
                    Intent intent_ad = new Intent(getApplicationContext(), config_dtn.class);
                    intent_ad.putExtra("input_address", input_Aaa);
                    startActivity(intent_ad);

                    init_webView();
                }
            });
        }
    }
}