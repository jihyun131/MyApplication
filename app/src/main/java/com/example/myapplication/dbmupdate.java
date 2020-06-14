package com.example.myapplication;

import com.example.myapplication.firebase.desbookmark;
import com.example.myapplication.firebase.numbookmark;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.logging.Handler;

public class dbmupdate extends AppCompatActivity {
    String uid;
    private FirebaseAuth firebaseAuth;
    String sdbm_info;
    String sdbm;
    private WebView webView;
    private TextView txt_address;
    private Handler handler;
    Button find_address;
    Button desdata_up;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String data1;

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        uid = mAuth.getCurrentUser().getUid();

        super.onCreate(savedInstanceState);
        Intent ad = getIntent();
        final String desti = ad.getStringExtra("input_address");

        setContentView(R.layout.dbmlist);
        ActionBar ab = getSupportActionBar();
        ab.setIcon(R.drawable.pocketpolice_icon);
        ab.setDisplayUseLogoEnabled(true);
        ab.setDisplayShowHomeEnabled(true);

        EditText find_address = (EditText) findViewById(R.id.find_address);
        find_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pg4 = new Intent(getApplicationContext(), DaumWebViewActivity.class);
                startActivity(pg4);
            }
        });
        final EditText des_info = (EditText) findViewById(R.id.newdesname);
        data1 = getIntent().getStringExtra("input_address");
        find_address.setText(data1);

        desdata_up = (Button) findViewById(R.id.desdata_up);
        desdata_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sdbm_info = des_info.getText().toString();
                sdbm = desti;
                svdbm();
                Intent pg1 = new Intent(getApplicationContext(), bookmark_dtn.class);
                startActivity(pg1);

            }
        });
    }
    public void svdbm (){
        desbookmark sss = new desbookmark(uid);
        sss.savedbm(sdbm, sdbm_info);
    }
}
