package com.example.myapplication.firebase;

import android.content.Intent;
import com.example.myapplication.dbmupdate;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

public class numbookmark {
    final FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference ref=database.getReference();

    String userid;

    public numbookmark(String userid){
        this.userid=userid;
        ref.child("NumBookmark").child(userid);
        //맨 처음 시작 때 사용자 database준비
    }

    public void savenbm(String snbm_info, String snbm){

        String key = ref.child("NumBookmark").child(userid).push().getKey();
        Data3 data3=new Data3(snbm, snbm_info);
        Map<String,Object> Value=data3.toMap();
        Map<String,Object> childUpdates=new HashMap<>();
        childUpdates.put("/NumBookMark/"+userid+'/'+key,Value);
        ref.updateChildren(childUpdates);
    }
};


@IgnoreExtraProperties
class Data3{
    public String snbm;
    public String snbm_info;

    public Data3(){}
    public Data3(String snbm, String snbm_info){
        this.snbm=snbm;
        this.snbm_info=snbm_info;
    }

    public Map<String, Object> toMap(){
        HashMap<String,Object> result=new HashMap<>();
        result.put(snbm_info,snbm);
        return result;
    }
}