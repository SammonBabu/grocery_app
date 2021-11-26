package com.example.grocery;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.grocery.activities.UsersListActivity;

import androidx.appcompat.app.AppCompatActivity;

public class Empmenu  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empmenu);
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //  setSupportActionBar(toolbar);
        TextView txtavaadd = findViewById(R.id.txtavaadd);
        TextView txtWork = findViewById(R.id.txtWork);
        TextView txtempdet = findViewById(R.id.txtempdet);
        final TextView txtemail = findViewById(R.id.txtemail);
        final TextView txtusname = findViewById(R.id.txtusname);

        //TextView txtuserdetail = findViewById(R.id.txtuserdetail);
        //  fab.setOnClickListener(new View.OnClickListener() {
        String emailFromIntent = getIntent().getStringExtra("EMAIL");
        txtemail.setText(emailFromIntent);

        String usern = getIntent().getStringExtra("username");
        txtusname.setText(usern);

        txtavaadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent g = new Intent(Empmenu.this,Employeeavaliability.class);
                g.putExtra("EMAIL",txtemail.getText().toString().trim());
                g.putExtra("username",txtusname.getText().toString().trim());
                startActivity(g);




            }
        });


        txtWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent aa;
                aa = new Intent(getApplicationContext(), BuyList.class);
                aa.putExtra("some", "some data");
                startActivity(aa);




            }
        });

        txtempdet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent aa;
                aa = new Intent(getApplicationContext(), EmpList.class);
                aa.putExtra("some", "some data");
                startActivity(aa);




            }
        });



    }

}
