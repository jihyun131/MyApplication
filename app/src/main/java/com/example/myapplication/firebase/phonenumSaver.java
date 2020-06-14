package com.example.myapplication.firebase;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

public class phonenumSaver {
    final FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference ref=database.getReference();

    String userid;
    public phonenumSaver(String userid){
        this.userid=userid;
        ref.child("PhoneNum").child(userid);
        //맨 처음 시작 때 사용자 database준비
    }

    public void savePhonenum(String sname, String snum){

        String key = ref.child("PhoneNum").child(userid).push().getKey();
        Data2 data2=new Data2(sname, snum);
        Map<String,Object> gpsValue=data2.toMap();
        Map<String,Object> childUpdates=new HashMap<>();
        childUpdates.put("/PhoneNum/"+userid+'/'+key,gpsValue);
        ref.updateChildren(childUpdates);
    }
};


@IgnoreExtraProperties
class Data2{
    public String sname;
    public String snum;

    public Data2(){}
    public Data2(String sname, String snum){

        this.sname=sname;
        this.snum=snum;
    }

    public Map<String, Object> toMap(){
        HashMap<String,Object> result=new HashMap<>();
        result.put(sname,snum);
        return result;
    }
}
