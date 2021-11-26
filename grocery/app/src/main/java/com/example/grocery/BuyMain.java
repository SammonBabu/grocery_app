package com.example.grocery;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class BuyMain extends AppCompatActivity {
TextView txtbemail,txtbuser,txtcash,result_textt,txtstatus,txtdrmail;
    EditText txtaddress,txtphone;
  Button btndone;
    private String res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);

        final SQLiteHelper sqLiteHelper = new SQLiteHelper(this, "Grocery.sqlite", null, 1);

        sqLiteHelper.queryData("CREATE TABLE IF NOT EXISTS paycarts(Id INTEGER PRIMARY KEY AUTOINCREMENT,usname VARCHAR, usemail VARCHAR, address VARCHAR,phone VARCHAR,cash VARCHAR,cashmode varchar,demail varchar,status varchar)");


        txtbemail = (TextView) findViewById(R.id.txtbemail);
        txtbuser = (TextView) findViewById(R.id.txtbuser);
        txtcash= (TextView) findViewById(R.id.txtcash);
        txtaddress= (EditText) findViewById(R.id.txtaddress);
        txtphone= (EditText) findViewById(R.id.txtphone);

        txtdrmail= (TextView) findViewById(R.id.txtdrmail);
        final String demail ="employeemail";
        txtdrmail.setText(demail);

        String emailFromIntent = getIntent().getStringExtra("EMAIL");
        txtbemail.setText(emailFromIntent);

        String usern = getIntent().getStringExtra("username");
        txtbuser.setText(usern);
        String cash = getIntent().getStringExtra("Totalprice");
        txtcash.setText(cash);


        txtstatus = (TextView) findViewById(R.id.txtstatus);
        final String status ="pending";
        txtstatus.setText(status);

        result_textt = (TextView) findViewById(R.id.result_text);

        RadioGroup RGroup = (RadioGroup) findViewById(R.id.RGroup);
        RGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                RadioButton rbd1 = (RadioButton) findViewById(R.id.rbd1);
                if (rbd1.isChecked()) {

                    result_textt.setText("COD");

                    Toast.makeText(getApplicationContext(), "option 1", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(getApplicationContext(), "option 2", Toast.LENGTH_LONG).show();
                    result_textt.setText("PAYTM");

                }
                res=result_textt.getText().toString().trim();

            }
        });

      //  final String[] status = {pending};

        btndone= (Button) findViewById(R.id.btndone);


        btndone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                Cursor cursor= sqLiteHelper.getData("SELECT * FROM cartlst  where  email='"+txtbemail+"'");
                if(cursor.moveToFirst()) {
                    String optacountb = cursor.getString(0);
                    Toast.makeText(getApplicationContext(), "Cart List" + optacountb, Toast.LENGTH_LONG).show();

                }

                try{

                    sqLiteHelper.insertpaycart(

                            txtbuser.getText().toString().trim(),
                            txtbemail.getText().toString().trim(),
                            txtaddress.getText().toString().trim(),
                            txtphone.getText().toString().trim(),
                            txtcash.getText().toString().trim(),
                            result_textt.getText().toString().trim(),
                            txtdrmail.getText().toString().trim(),
                            txtstatus.getText().toString().trim()




                    );
                    Toast.makeText(getApplicationContext(), "Added successfully!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(BuyMain.this, FoodListView.class);
                    startActivity(intent);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });



    }



}
