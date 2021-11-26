package com.example.grocery;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class Adminlog2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addproduct);
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //  setSupportActionBar(toolbar);

        //  FloatingActionButton fab = findViewById(R.id.fab);
        //  fab.setOnClickListener(new View.OnClickListener() {

        // public void onClick(View view) {
                //         Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //              .setAction("Action", null).show();
                //  }
                // });


                Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            if (bundle.getString("some") != null) {
                Toast.makeText(getApplicationContext(), "data" + bundle.getString("some"), Toast.LENGTH_SHORT).show();
            }
        }

    }

}
