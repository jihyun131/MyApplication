package com.example.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.myapplication.firebase.dataSaver;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class config_num extends AppCompatActivity {
    String sosname;
    String sosnum;
    private DatabaseReference mReference;
    private FirebaseDatabase mDatabase;
    private FirebaseAuth firebaseAuth;
    String data3;

    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    final String uid = mAuth.getCurrentUser().getUid();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_num);
        ActionBar ab = getSupportActionBar() ;
        ab.setIcon(R.drawable.pocketpolice_icon) ;
        ab.setDisplayUseLogoEnabled(true) ;
        ab.setDisplayShowHomeEnabled(true) ;

        //data3 = mDatabase.getReference("Selected").getKey();

        Button button1=(Button)findViewById(R.id.btn_complete);
        Button button2=(Button)findViewById(R.id.cpn8);
        final EditText nametxt=(EditText)findViewById(R.id.newname);
        final EditText numtxt=(EditText)findViewById(R.id.pl_Phone);
        //sosname=nametxt.getText().toString();
        //sosnum=numtxt.getText().toString();
        //sosname = String.format("%s", nametxt.getText());
        //Log.i("tag", sosname);
        //sosnum = String.format("%s", numtxt.getText());

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sosname = nametxt.getText().toString();
                sosnum = numtxt.getText().toString();
                save1();
                Intent intent1=new Intent(getApplicationContext(), home.class);
                //intent1.putExtra("input_numnamme",sosname);
                //intent1.putExtra("input_phonenum",sosnum);
                startActivity(intent1);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2=new Intent(getApplicationContext(), bookmark_num.class);
                startActivity(intent2);
            }
        });
    }

    public void save1 (){
        dataSaver ppp = new dataSaver(uid);
        ppp.savenum(sosnum);
    }
}