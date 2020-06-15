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
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class home extends AppCompatActivity {

    String input_address;
    String input_phonenum;
    String namename;

    final FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference ref=database.getReference();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    final String uid = mAuth.getCurrentUser().getUid();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ActionBar ab = getSupportActionBar() ;
        ab.setIcon(R.drawable.pocketpolice_icon) ;
        ab.setDisplayUseLogoEnabled(true) ;
        ab.setDisplayShowHomeEnabled(true) ;

        final TextView username = (TextView)findViewById(R.id.textView3);
        Button button1=(Button)findViewById(R.id.btn_config_dtn);
        Button button2=(Button)findViewById(R.id.btn_config_num);
        Button button3=(Button)findViewById(R.id.btn_start);


        DatabaseReference mDatabase;
        mDatabase= FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Users").child(uid).child("name").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                namename= (String) dataSnapshot.getValue();
                Log.i("TEST",namename);
                username.setText(namename+"님");

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });


        mDatabase.child("Selected").child(uid).child("주소").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue()!=null) {
                    input_address = dataSnapshot.getValue().toString();
                    Log.i("TESTTEST", input_address);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mDatabase.child("Selected").child(uid).child("번호").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue()!=null) {
                    input_phonenum = dataSnapshot.getValue().toString();
                    Log.i("TESTTEST", input_phonenum);
                }
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

                if(input_address!=null && input_address!=null) {
                    new AlertDialog.Builder(home.this)
                            .setTitle("목적지, 연락처 확인")
                            .setMessage(input_address + "\n" + input_phonenum + " 가 맞습니까?")
                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    intent3.putExtra("input_address", input_address);
                                    intent3.putExtra("input_phonenum", input_phonenum);
                                    startActivity(intent3);
                                }
                            })
                            .setNegativeButton("다시 설정하기", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();

                                }
                            })
                            .show();

                }
                else{
                    new AlertDialog.Builder(home.this)
                            .setTitle("잠깐!")
                            .setMessage("목적지 혹은 연락처가 설정되지 않았습니다.")
                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            })
                            .show();
                }
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