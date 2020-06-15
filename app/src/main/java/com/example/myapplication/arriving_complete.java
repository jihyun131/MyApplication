package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;

public class arriving_complete extends AppCompatActivity {
    String namename;
    //String input_phonenum = getIntent().getStringExtra("input_phonenum");
    String input_phonenum;   //="01095972366";
    String message="님이 안전하게 귀가하셨습니다.";
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    final String uid = mAuth.getCurrentUser().getUid();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arriving_complete);
        ActionBar ab = getSupportActionBar() ;
        ab.setIcon(R.drawable.pocketpolice_icon) ;
        ab.setDisplayUseLogoEnabled(true) ;
        ab.setDisplayShowHomeEnabled(true) ;


        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        final String testUser = mAuth.getCurrentUser().getUid();
        DatabaseReference mDatabase;
        mDatabase= FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Selected").child(uid).child("번호").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue()!=null) {
                    input_phonenum = dataSnapshot.getValue().toString();
                    Log.i("TESTTEST", input_phonenum);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mDatabase.child("Users").child(testUser).child("name").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                namename=dataSnapshot.getValue().toString();
                Log.i("TEST",namename);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });




        Button sendbutton=(Button)findViewById(R.id.btn_sendmsg);

        sendbutton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UnlocalizedSms")
            @Override
            public void onClick(View view) {

                try{
                    SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage(input_phonenum, null, namename+message, null, null);
                        Toast.makeText(getApplicationContext(),namename+"전송완료!",Toast.LENGTH_LONG).show();
                }catch(Exception e){
                    Toast.makeText(getApplicationContext(),"SMS faild, please try again later!",Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });

    }

}

