package com.example.myapplication.firebase;

import android.content.Intent;
import com.example.myapplication.dbmupdate;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

public class numbookmark {
    String user;
    private FirebaseAuth firebaseAuth;
    final FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference ref=database.getReference();

    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    final String nowuser = mAuth.getCurrentUser().getUid();


    public numbookmark(String uid){
        this.user=nowuser;
        ref.child("NumBookmark").child(uid);
    }

    public void savenbm(String snbm_info, String snbm){
        String key = ref.child("NumBookmark").child(user).push().getKey();
        Data3 data3=new Data3(snbm, snbm_info);
        Map<String,Object> Value=data3.toMap();
        Map<String,Object> childUpdates=new HashMap<>();
        childUpdates.put("/NumBookMark/"+user+'/'+key,Value);
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