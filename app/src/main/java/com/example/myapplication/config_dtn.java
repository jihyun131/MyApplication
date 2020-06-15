package com.example.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.myapplication.firebase.dataSaver;
import com.google.firebase.auth.FirebaseAuth;

public class config_dtn extends AppCompatActivity {
    String data;

    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    final String uid = mAuth.getCurrentUser().getUid();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_dtn);
        ActionBar ab = getSupportActionBar() ;
        ab.setIcon(R.drawable.pocketpolice_icon) ;
        ab.setDisplayUseLogoEnabled(true) ;
        ab.setDisplayShowHomeEnabled(true) ;

        //Intent intent_ad = getIntent();

        Button button1=(Button)findViewById(R.id.btn_complete);
        EditText button2 = (EditText) findViewById(R.id.editTextTextPostalAddress);
        Button button3=(Button)findViewById(R.id.bml);


        data = getIntent().getStringExtra("input_address");
        button2.setText(data);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save2();
                Intent intent1=new Intent(getApplicationContext(), home.class);
                //intent1.putExtra("input_address",data);
                startActivity(intent1);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2=new Intent(getApplicationContext(), search_address.class);
                startActivity(intent2);
            }
        });


        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3=new Intent(getApplicationContext(), bookmark_dtn.class);
                startActivity(intent3);
            }
        });



    }

    public void save2 (){
        dataSaver sss = new dataSaver(uid);
        sss.saveaddress(data);
    }
}