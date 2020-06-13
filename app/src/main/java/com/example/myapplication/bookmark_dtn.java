package com.example.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.myapplication.firebase.desbookmark;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class bookmark_dtn extends AppCompatActivity {
    private ListView listView2;
    List fileList1 = new ArrayList<>();
    ArrayAdapter adapter2;
    Button dbm6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark_dtn);

        listView2 = (ListView)findViewById(R.id.listdbm);

        adapter2 = new ArrayAdapter<String>(this, R.layout.dbmsave, fileList1);
        listView2.setAdapter(adapter2);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseRef = database.getReference("DesBookmark");
        DatabaseReference dbmRef = database.getReference("userid");
        dbmRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                adapter2.clear();
                // 클래스 모델이 필요?
                for (DataSnapshot fileSnapshot : dataSnapshot.getChildren()) {
                    //MyFiles filename = (MyFiles) fileSnapshot.getValue(MyFiles.class);
                    //하위키들의 value를 어떻게 가져오느냐???
                    String str = fileSnapshot.child("sdbm_info").getValue().toString();
                    Log.i("TAG: value is ", str);
                    fileList1.add(str);
                    adapter2.add(str);
                }
                adapter2.notifyDataSetChanged();
                listView2.setSelection(adapter2.getCount()-1);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("TAG: ", "Failed to read value", databaseError.toException());
            }
        });
        setContentView(R.layout.activity_bookmark_dtn);
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
}