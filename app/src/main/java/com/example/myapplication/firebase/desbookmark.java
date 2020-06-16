package com.example.myapplication.firebase;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

public class desbookmark {
    String user;
    final FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference ref=database.getReference();

    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    final String nowuser = mAuth.getCurrentUser().getUid();

    public desbookmark(String uid){
        this.user=nowuser;
        ref.child("DesBookmark").child(uid);
    }


    public void savedbm(String sdbm_info, String sdbm){

        String key = ref.child("NumBookmark").child(user).push().getKey();
        Data4 data=new Data4(sdbm, sdbm_info);
        Map<String,Object> gpsValue=data.toMap();
        Map<String,Object> childUpdates=new HashMap<>();
        childUpdates.put("/DesBookmark/"+user+'/'+key,gpsValue);
        ref.updateChildren(childUpdates);
    }
};


@IgnoreExtraProperties
class Data4{
    public String sdbm;
    public String sdbm_info;

    public Data4(){}
    public Data4(String sdbm, String sdbm_info){
        this.sdbm=sdbm;
        this.sdbm_info=sdbm_info;
    }

    public Map<String, Object> toMap(){
        HashMap<String,Object> result=new HashMap<>();
        result.put(sdbm,sdbm_info);
        return result;
    }
}