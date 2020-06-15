package com.example.myapplication.firebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class gpsSaver {
    final FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference ref=database.getReference();
    Date today=new Date();
    SimpleDateFormat format = new SimpleDateFormat(("yyyy-MM-dd"));
    String time=format.format(today);
    String userid;
    public gpsSaver(String userid){
        this.userid=userid;
        ref.child("GPS").child(userid).child(time);
        //맨 처음 시작 때 사용자 database준비
    }

    public void saveGPS(String gps){

        String key = ref.child("GPS").child(userid).push().getKey();
        Data5 data=new Data5(gps);
        Map<String,Object> gpsValue=data.toMap();
        Map<String,Object> childUpdates=new HashMap<>();
        childUpdates.put("/GPS/"+userid+'/'+time+'/'+key,gpsValue);
        ref.updateChildren(childUpdates);
    }
};
@IgnoreExtraProperties

class Data5{
    public String gps;
    public String time;
    public Data5(){}
    public Data5(String gps){
        Date today=new Date();
        SimpleDateFormat format = new SimpleDateFormat(("hh:ss-SSS"));
        String time=format.format(today);
        this.gps=gps;
        this.time=time;
    }

    public Map<String, Object> toMap(){
        HashMap<String,Object> result=new HashMap<>();
        result.put(time,gps);
        return result;
    }
}
