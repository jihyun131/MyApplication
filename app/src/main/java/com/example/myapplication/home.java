package com.example.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class home extends AppCompatActivity {

    //String input_address;
    //String input_phonenum;
    //String input_numname;

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

        //input_address = getIntent().getStringExtra("input_address");
        //input_phonenum = getIntent().getStringExtra("input_phonenum");
        //input_numname = getIntent().getStringExtra("input_numnamme");

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
                Intent intent3=new Intent(getApplicationContext(), start.class);
                //intent3.putExtra("input_address",input_address);
                //intent3.putExtra("input_phonenum",input_phonenum);
                //intent3.putExtra("input_numname",input_numname);
                /*
                if(input_address==null){
                    if(input_phonenum==null){
                        //둘 다 즐겨찾기에서 선택했는지
                        if(bm_a==null && bm_pn==null){
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
                        else if(bm_a==null){
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
                        if(bm_a==null){
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
                    if(bm_pn==null){
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
                startActivity(intent3);
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