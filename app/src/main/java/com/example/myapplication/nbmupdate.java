package com.example.myapplication;


import com.example.myapplication.firebase.numbookmark;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class nbmupdate extends AppCompatActivity {
    String uid;
    String snbm;
    String snbm_info;
    Button numdata_up;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        uid = mAuth.getCurrentUser().getUid();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.nbmlist);
        ActionBar ab = getSupportActionBar();
        ab.setIcon(R.drawable.pocketpolice_icon);
        ab.setDisplayUseLogoEnabled(true);
        ab.setDisplayShowHomeEnabled(true);

        final EditText num_info = (EditText) findViewById(R.id.newname);
        final EditText num = (EditText) findViewById(R.id.pl_Phone);

        numdata_up = (Button) findViewById(R.id.numdata_up);
        numdata_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                snbm_info = num_info.getText().toString();
                snbm = num.getText().toString();
                savendm();
                Intent svnbm = new Intent(nbmupdate.this, bookmark_num.class);
                startActivity(svnbm);
            }
        });
    }

    public void savendm (){
        numbookmark sss = new numbookmark(uid);
        sss.savenbm(snbm_info,snbm);
    }
}
