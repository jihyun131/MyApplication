package com.example.myapplication.firebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

public class desbookmark {
    final FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference ref=database.getReference();

    String userid;
    public desbookmark(String userid){
        this.userid=userid;
        ref.child("DesBookmark").child(userid);
        //맨 처음 시작 때 사용자 database준비
    }

    public void savedbm(String sdbm_info, String sdbm){

        String key = ref.child("NumBookmark").child(userid).push().getKey();
        Data4 data=new Data4(sdbm, sdbm_info);
        Map<String,Object> gpsValue=data.toMap();
        Map<String,Object> childUpdates=new HashMap<>();
        childUpdates.put("/DesBookmark/"+userid+'/'+key,gpsValue);
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