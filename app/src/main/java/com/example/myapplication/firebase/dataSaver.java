package com.example.myapplication.firebase;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

public class dataSaver {
    final FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference ref=database.getReference();

    String userid;
    public dataSaver(String userid){
        this.userid=userid;
        ref.child("selected");
        //맨 처음 시작 때 사용자 database준비
    }


    public void saveaddress(String data){

        ref.child("Selected").child(userid).child("주소").setValue(data);
        //ref.child("Selected").child(s_num).setValue("true");

    }
    public void savenum(String data){
        ref.child("Selected").child(userid).child("번호").setValue(data);
    }
};

