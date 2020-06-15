package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class home extends AppCompatActivity {

    String input_address;
    String input_phonenum;
    //String input_numname;
    String bm_des;
    String bm_num;
    //Intent intent3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ActionBar ab = getSupportActionBar() ;
        ab.setIcon(R.drawable.pocketpolice_icon) ;
        ab.setDisplayUseLogoEnabled(true) ;
        ab.setDisplayShowHomeEnabled(true) ;

        Button button1=(Button)findViewById(R.id.btn_config_dtn);
        Button button2=(Button)findViewById(R.id.btn_config_num);
        Button button3=(Button)findViewById(R.id.btn_start);

        DatabaseReference mDatabase;
        mDatabase= FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Selected").child("주소").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                input_address=dataSnapshot.getValue().toString();
                Log.i("TEST",input_address);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mDatabase.child("Selected").child("번호").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                input_phonenum=dataSnapshot.getValue().toString();
                Log.i("TEST",input_phonenum);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(getApplicationContext(), config_dtn.class);
                startActivity(intent1);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2=new Intent(getApplicationContext(), config_num.class);
                startActivity(intent2);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intent3=new Intent(getApplicationContext(), start.class);
                new AlertDialog.Builder(home.this)
                        .setTitle("목적지, 연락처 확인")
                        .setMessage(input_address+"\n"+input_phonenum+"가 맞습니까?")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which){
                                startActivity(intent3);
                                //intent3 =new Intent(getApplicationContext(), start.class);
                            }
                        })
                        .setNegativeButton("설정 다시하기", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();

                            }
                        })
                        .show();
                //startActivity(intent3);
                //Intent intent3=new Intent(getApplicationContext(), start.class);


      /*          if(input_address==null){
                    if(input_phonenum==null){
                        //둘 다 즐겨찾기에서 선택했는지
                        if(bm_des==null && bm_num==null){
                            new AlertDialog.Builder(home.this)
                            .setTitle("잠깐!")
                            .setMessage("목적지와 사전 연락처를 설정해 주세요")
                            .setNeutralButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            })
                                    .show();
                        }
                        else if(bm_des==null){
                            new AlertDialog.Builder(home.this)
                                    .setTitle("잠깐!")
                                    .setMessage("목적지를 설정해 주세요")
                                    .setNeutralButton("확인", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {

                                        }
                                    })
                                    .show();
                        }
                        else{
                            new AlertDialog.Builder(home.this)
                                    .setTitle("잠깐!")
                                    .setMessage("사전 연락처를 설정해 주세요")
                                    .setNeutralButton("확인", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {

                                        }
                                    })
                                    .show();
                        }
                    }
                    else {
                        //주소 즐겨찾기에서 선택했는지
                        if(bm_des==null){
                            new AlertDialog.Builder(home.this)
                                    .setTitle("잠깐!")
                                    .setMessage("목적지를 설정해 주세요")
                                    .setNeutralButton("확인", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {

                                        }
                                    })
                                    .show();
                        }
                        else startActivity(intent3);
                    }
                }
                else{
                    if(input_phonenum!=null) startActivity(intent3);
                    else if(bm_num==null){
                        new AlertDialog.Builder(home.this)
                                .setTitle("잠깐!")
                                .setMessage("사전 연락처를 설정해 주세요")
                                .setNeutralButton("확인", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                })
                                .show();
                    }
                    else startActivity(intent3);
                } */
                //startActivity(intent3);
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_button){
            Intent intent = new Intent(getApplicationContext(), setting.class);
            startActivity(intent);
        }
        return true;
    }
}