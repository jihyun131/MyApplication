package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.myapplication.firebase.dataSaver;
import com.example.myapplication.firebase.desbookmark;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class bookmark_dtn extends AppCompatActivity {
    String uid;
    String dbmdata2;
    String selected_item;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;
    private DatabaseReference mRef;

    private ChildEventListener mChild;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    List<Object> Array = new ArrayList<Object>();

    Button dbm6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark_dtn);

        listView =(ListView) findViewById(R.id.listdbm);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        uid = mAuth.getCurrentUser().getUid();
        initDatabase();

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,new ArrayList<String>());
        listView.setAdapter(adapter);

        mReference = mDatabase.getReference("DesBookmark").child(uid);
        mRef = mDatabase.getReference();
        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot){
                adapter.clear();

                for (DataSnapshot dbmData : dataSnapshot.getChildren()){
                    for (DataSnapshot dbmdata2 : dbmData.getChildren()) {
                        String dbmdata3 = dbmdata2.getValue().toString();
                        Array.add(dbmdata3);
                        adapter.add(dbmdata3);
                    }
                }
                adapter.notifyDataSetChanged();
                listView.setSelection(adapter.getCount()-1);

            }

            @Override
            public void onCancelled(DatabaseError databaseError){

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView,
                                    View view, int position, long id) {

                //클릭한 아이템의 문자열을 가져옴
                selected_item = (String)adapterView.getItemAtPosition(position);
                bm_save2();
                Intent go2 = new Intent(getApplicationContext(), home.class);
                startActivity(go2);
            }
        });


        ActionBar ab = getSupportActionBar() ;
        ab.setIcon(R.drawable.pocketpolice_icon) ;
        ab.setDisplayUseLogoEnabled(true) ;
        ab.setDisplayShowHomeEnabled(true) ;

        Button button1=(Button)findViewById(R.id.btn_complete);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(getApplicationContext(), setting.class);
                startActivity(intent1);
            }
        });

        dbm6 = (Button) findViewById(R.id.dbm6);
        dbm6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pg1 = new Intent(bookmark_dtn.this, dbmupdate.class);
                startActivity(pg1);
            }
        });
    }
    private void initDatabase(){

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        String uid = user.getUid();

        mDatabase =FirebaseDatabase.getInstance();
        mReference=mDatabase.getReference("DesBookmark").child(uid);
        //mReference.child("log").setValue("check");
        mChild = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        mReference.addChildEventListener(mChild);
    }
    @Override
    protected  void onDestroy(){
        super.onDestroy();
        mReference.removeEventListener(mChild);
    }
    public void bm_save2 (){
        dataSaver mmm = new dataSaver(uid);
        mmm.saveaddress(selected_item);
    }
}