package com.example.grocery;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class Empmain extends AppCompatActivity {
   // public static SQLiteHelper sqLiteHelper;
    Button btnadd;
    TextView empname;
    TextView empemail;
    TextView emppassword;
    TextView empmobile;
    TextView empaddress,editemppass;
    TextView empview;


    public static SQLiteHelper sqLiteHelper;
    //  FirebaseDatabase firebaseDatabase;
    //  DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);
        empname = (TextView) findViewById(R.id.editempName);
        empemail = (TextView) findViewById(R.id.editempEmail);
        final Spinner spinnerdes = (Spinner) findViewById(R.id.spinnerdes);
        empmobile = (TextView) findViewById(R.id.editempMobile);
        empaddress = (TextView) findViewById(R.id.editempAddress);
        btnadd = (Button) findViewById(R.id.buttonAddEmp);
        empview = (TextView) findViewById(R.id.textViewViewEmp);
        editemppass = (TextView) findViewById(R.id.editemppass);



        sqLiteHelper = new SQLiteHelper(this, "Grocery.sqlite", null, 1);

        sqLiteHelper.queryData("CREATE TABLE IF NOT EXISTS tblempoyee(Id INTEGER PRIMARY KEY AUTOINCREMENT,empname VARCHAR, empemail VARCHAR, empdes VARCHAR,empmobile varchar,empaddress varchar,emppass varchar)");


        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    sqLiteHelper.insertdataemp(

                            empname.getText().toString().trim(),
                            empemail.getText().toString().trim(),
                            spinnerdes.getSelectedItem().toString().trim(),
                            empmobile.getText().toString().trim(),
                            empaddress.getText().toString().trim(),
                            editemppass.getText().toString().trim()


                            );
                    Toast.makeText(getApplicationContext(), "Added successfully!", Toast.LENGTH_SHORT).show();
                    empname.setText("");
                    empemail.setText("");

                    empmobile.setText("");
                    empaddress.setText("");
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        empview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Empmain.this, EmpList.class);
                startActivity(intent);
            }
        });



    }


}
