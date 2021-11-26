package com.example.grocery;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.grocery.activities.UsersListActivity;

import androidx.appcompat.app.AppCompatActivity;

public class Admin_Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminmenu);
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //  setSupportActionBar(toolbar);
        TextView txtempdetail = findViewById(R.id.txtempdetail);

         TextView txtuserdetail = findViewById(R.id.txtuserdetail);
        //  fab.setOnClickListener(new View.OnClickListener() {

        txtuserdetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent aa;
                aa = new Intent(getApplicationContext(), UsersListActivity.class);
                aa.putExtra("some", "some data");
                startActivity(aa);




            }
        });

        txtempdetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent aa;
                aa = new Intent(getApplicationContext(),EmpList.class);
                aa.putExtra("some", "some data");
                startActivity(aa);




            }
        });
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            if (bundle.getString("some") != null) {
                Toast.makeText(getApplicationContext(), "data" + bundle.getString("some"), Toast.LENGTH_SHORT).show();
            }
        }

    }

}
