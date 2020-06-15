package com.example.myapplication.firebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

public class addressSaver {
    final FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference ref=database.getReference();

    String userid;
    public addressSaver(String userid){
        this.userid=userid;
        ref.child("selected");
        //맨 처음 시작 때 사용자 database준비
    }

    public void saveAddress(String des){

        String key = ref.child("Address").child(userid).push().getKey();
        Data data=new Data(des);
        Map<String,Object> gpsValue=data.toMap();
        Map<String,Object> childUpdates=new HashMap<>();
        childUpdates.put("/Address/"+userid+'/'+key,gpsValue);
        ref.updateChildren(childUpdates);
    }


};
@IgnoreExtraProperties
class Data{
    public String gps;

    public Data(){}
    public Data(String gps){

        this.gps=gps;

    }

    public Map<String, Object> toMap(){
        HashMap<String,Object> result=new HashMap<>();
        result.put(gps,true);
        return result;
    }
}
