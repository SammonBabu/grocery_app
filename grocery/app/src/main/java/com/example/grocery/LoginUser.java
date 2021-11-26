package com.example.grocery;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.grocery.activities.LoginActivity;

import androidx.appcompat.app.AppCompatActivity;

public class LoginUser extends AppCompatActivity {
    TextView adlogin;
    TextView userlogin,drlogin,stlogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginb);
        userlogin = (TextView) findViewById(R.id.userlogin);
        adlogin = (TextView) findViewById(R.id.adlogin);
        drlogin = (TextView) findViewById(R.id.drlogin);
        stlogin = (TextView) findViewById(R.id.stlogin);



        adlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginUser.this, Adminlogin.class);
                startActivity(i);
            }
        });
        userlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginUser.this, LoginActivity.class);
                startActivity(i);
            }
        });
        stlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginUser.this, StoreLogin.class);
                startActivity(i);
            }
        });

        drlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginUser.this, EmpLogin.class);
                startActivity(i);
            }
        });
    }
}
