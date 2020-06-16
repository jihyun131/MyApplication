package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.util.regex.Pattern;

import static com.google.android.gms.common.internal.safeparcel.SafeParcelable.NULL;


public class MainActivity extends AppCompatActivity {
    Button mLoginBtn;
    TextView mResigettxt;
    EditText mEmailText, mPasswordText;
    private FirebaseAuth firebaseAuth;
    InputMethodManager imm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar ab = getSupportActionBar();
        ab.setIcon(R.drawable.pocketpolice_icon);
        ab.setDisplayUseLogoEnabled(true);
        ab.setDisplayShowHomeEnabled(true);

        //Button button1 = (Button) findViewById(R.id.btn_login);
        //Button button2 = (Button) findViewById(R.id.btn_signup1);

        firebaseAuth = FirebaseAuth.getInstance();
        //버튼 등록하기
        mResigettxt = findViewById(R.id.btn_signup1);
        mLoginBtn = findViewById(R.id.btn_login);
        mEmailText = findViewById(R.id.et_id);
        mPasswordText = findViewById(R.id.et_pass);

        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);


        mResigettxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(getApplicationContext(), signup.class);
                startActivity(intent2);
            }
        });

        //로그인 버튼이 눌리면
        mLoginBtn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                imm.hideSoftInputFromWindow(mEmailText.getWindowToken(), 0);
                imm.hideSoftInputFromWindow(mPasswordText.getWindowToken(), 0);
                String email = mEmailText.getText().toString().trim();
                String pwd = mPasswordText.getText().toString().trim();
                if (email.isEmpty() || pwd.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Fill the blanks", Toast.LENGTH_SHORT).show();
                } else {
                    firebaseAuth.signInWithEmailAndPassword(email, pwd)
                            .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    //로그인 성공
                                    if (task.isSuccessful()) {
                                        Toast.makeText(MainActivity.this, "로그인에 성공하셨습니다.", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(MainActivity.this, home.class);
                                        startActivity(intent);
                                    }
                                    // 로그인 실패
                                    else {
                                        Toast.makeText(MainActivity.this, "로그인 오류", Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });
                }
            }
        });
    }


}