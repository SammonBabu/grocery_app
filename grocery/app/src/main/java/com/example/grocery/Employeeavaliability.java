package com.example.grocery;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.grocery.activities.UsersListActivity;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.grocery.Empmain.sqLiteHelper;

public class Employeeavaliability extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avaliability);
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //  setSupportActionBar(toolbar);
        final TextView txtavaemail = findViewById(R.id.txtavaemail);
        //final TextView txtusname = findViewById(R.id.txtusname);

        String emailFromIntent = getIntent().getStringExtra("EMAIL");
        txtavaemail.setText(emailFromIntent);

       // String usern = getIntent().getStringExtra("username");
      //  txtuser.setText(usern);



        Button btn_add = findViewById(R.id.btn_add);


        Button btnlist = findViewById(R.id.btnlist);


        //  fab.setOnClickListener(new View.OnClickListener() {
        sqLiteHelper = new SQLiteHelper(this, "Grocery.sqlite", null, 1);

        sqLiteHelper.queryData("CREATE TABLE IF NOT EXISTS tblavalbility(Id INTEGER PRIMARY KEY AUTOINCREMENT,empemail VARCHAR)");


        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    sqLiteHelper.insertava(

                            txtavaemail.getText().toString().trim()



                    );
                    Toast.makeText(getApplicationContext(), "Added successfully!", Toast.LENGTH_SHORT).show();

                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });


        btnlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Employeeavaliability.this, Empavalist.class);
                startActivity(intent);
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
