//package com.example.myapplication.firebase;
//
//
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.IgnoreExtraProperties;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class dataSaver {
//    final FirebaseDatabase database=FirebaseDatabase.getInstance();
//    DatabaseReference ref=database.getReference();
//
//
//    public dataSaver(){
//        ref.child("Selected");
//        //맨 처음 시작 때 사용자 database준비
//    }
//
//    public void savedata(String s_address, String s_num){
//
//        ref.child(s_address).setValue("");
//        ref.child(s_num).setValue("");
//
//    }
//};
//
