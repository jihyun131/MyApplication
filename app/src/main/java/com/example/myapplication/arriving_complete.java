package com.example.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class arriving_complete extends AppCompatActivity {
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    final String nowuser = mAuth.getCurrentUser().getUid();

    String username="dohee";
    String PhoneNo[]={"01095972366","01064222366"};
    String message=username+"님이 안전하게 귀가하셨습니다.";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arriving_complete);
        ActionBar ab = getSupportActionBar() ;
        ab.setIcon(R.drawable.pocketpolice_icon) ;
        ab.setDisplayUseLogoEnabled(true) ;
        ab.setDisplayShowHomeEnabled(true) ;

        Button sendbutton=(Button)findViewById(R.id.btn_sendmsg);

        sendbutton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UnlocalizedSms")
            @Override
            public void onClick(View view) {

                try{
                    SmsManager smsManager = SmsManager.getDefault();
                    for(String number :PhoneNo) {
                        smsManager.sendTextMessage(number, null, message, null, null);
                        Toast.makeText(getApplicationContext(),"전송완료!",Toast.LENGTH_LONG).show();
                    }
                }catch(Exception e){
                    Toast.makeText(getApplicationContext(),"SMS faild, please try again later!",Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });

    }

}

