package com.example.grocery;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;

public class ReportMain extends AppCompatActivity {
    public static SQLiteHelper sqLiteHelper;
    TextView txtemail,txtassigndate,txtstatus,txtcomplaint,txtviewcom,txtusername;
    EditText editBinID,editbinArea,editUserEmail,editLocality,editComplaint;
    Spinner spinnercyc,spinnerLoadType;

    Button btn_complaintadd,btnsubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // View RootView = inflater.inflate(R.layout.activity_votequest, container, false);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        txtusername = (TextView) findViewById(R.id.txtusername);
        //  textViewViewDbindetail = (TextView) findViewById(R.id.textViewViewDbindetail);
        txtemail = (TextView) findViewById(R.id.txtemail);
        txtcomplaint = (TextView) findViewById(R.id.txtcomplaint);
      //  editLocality = (EditText) findViewById(R.id.txtassigndate);
        btnsubmit = (Button) findViewById(R.id.btnsubmit);
        txtviewcom = (TextView) findViewById(R.id.txtviewcom);
       // btn_complaintadd = (Button) findViewById(R.id.btn_complaintadd);

        String emailFromIntent = getIntent().getStringExtra("EMAIL");
        txtemail.setText(emailFromIntent);
        String usern = getIntent().getStringExtra("username");
        txtusername.setText(usern);

        sqLiteHelper = new SQLiteHelper(this, "myempbook.sqlite", null, 1);


        sqLiteHelper.queryData("CREATE TABLE IF NOT EXISTS complainttbl(Id INTEGER PRIMARY KEY AUTOINCREMENT,username varchar,usemail varchar,complaint varchar,complaintdate varchar,status varchar)");

       // textViewViewComplaint = (TextView) findViewById(R.id.textViewViewComplaint);

        txtstatus = (TextView) findViewById(R.id.txtstatus);

        final String status ="pending";
        txtstatus.setText(status);


        //   sqLiteHelper = new SQLiteHelper(this, "easy.sqlite", null, 1);
        // String status ="pending";
        // final EditText  edassigndate = (EditText)view. findViewById(R.id.edassigndate);
        txtassigndate = (TextView) findViewById(R.id.txtassigndate);


        //getting the current time for joining date
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String assignDate = sdf.format(cal.getTime());
        txtassigndate.setText(assignDate);
        // notificationCounter=new NotificationCounter(findViewById(R.id.bell));
        // final String  nott=  notificationCounter.increaseNumber();
        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                try {
                    sqLiteHelper.insertcomplaint(

                            txtusername.getText().toString().trim(),
                            txtemail.getText().toString().trim(),
                            txtcomplaint.getText().toString().trim(),
                            txtassigndate.getText().toString().trim(),
                            txtstatus.getText().toString().trim()


                    );


                    Toast.makeText(getApplicationContext(), "Added successfully!", Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });




    }

}



