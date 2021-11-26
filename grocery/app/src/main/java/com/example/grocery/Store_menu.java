package com.example.grocery;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.grocery.activities.UsersListActivity;

import androidx.appcompat.app.AppCompatActivity;

public class Store_menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storemenu);
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //  setSupportActionBar(toolbar);
        TextView txtaddpro = findViewById(R.id.txtaddpro);

        TextView txtwork = findViewById(R.id.txtwork);
        //  fab.setOnClickListener(new View.OnClickListener() {

        txtwork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent aa;
                aa = new Intent(getApplicationContext(), BuyList.class);
                aa.putExtra("some", "some data");
                startActivity(aa);




            }
        });

        txtaddpro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent aa;
                aa = new Intent(getApplicationContext(),Main2Activity.class);
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
