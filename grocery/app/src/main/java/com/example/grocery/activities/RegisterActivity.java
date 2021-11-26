package com.example.grocery.activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import com.example.grocery.R;
import com.example.grocery.SQLiteHelper;
import com.example.grocery.helpers.InputValidation;
import com.example.grocery.model.User;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.widget.NestedScrollView;




/**
 * Created by lalit on 8/27/2016.
 */
public class RegisterActivity extends AppCompatActivity  {

    private final AppCompatActivity activity = RegisterActivity.this;

    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutName;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;
    private TextInputLayout textInputLayoutConfirmPassword;

    private TextInputEditText textInputEditTextName;
    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;
    private TextInputEditText textInputEditTextConfirmPassword;

    private AppCompatButton appCompatButtonRegister;
    private AppCompatTextView appCompatTextViewLoginLink;

    private InputValidation inputValidation;

    private User user;
    private SQLiteHelper sqLiteHelper;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();
        sqLiteHelper = new SQLiteHelper(this, "Grocery.sqlite", null, 1);
        user = new User();
        sqLiteHelper.queryData("CREATE TABLE IF NOT EXISTS registerus(Id INTEGER PRIMARY KEY AUTOINCREMENT,user_name VARCHAR, user_email VARCHAR, user_password VARCHAR)");
        inputValidation = new InputValidation(activity);
        initViews();

        appCompatButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=textInputEditTextEmail.getText().toString().trim();

                Cursor cursor=sqLiteHelper.getData("SELECT * from registerus where user_email='"+email+"'");

                if(cursor.moveToFirst())
                {
                    Toast.makeText(getApplicationContext(),"user ex",Toast.LENGTH_LONG).show();

                }
                else
                {
                    addUser();
                }
            }
        });



        appCompatTextViewLoginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inewsfeed = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(inewsfeed);
            }
        });


    }
    private void addUser() {

        try{
            sqLiteHelper.insertuser(
                    textInputEditTextName.getText().toString().trim(),
                    textInputEditTextEmail.getText().toString().trim(),
                    textInputEditTextPassword.getText().toString().trim()
                    //textInputEditTextPassword.getText().toString().trim()
                   // rbd2.getText().toString().trim()
                    //   Integer.parseInt(edtPrice.getText().toString().trim()),


                    // imageViewToByte(imageView)
            );
            Toast.makeText(getApplicationContext(), "Added successfully!", Toast.LENGTH_SHORT).show();
            // edtName.setText("");
            //  edtPrice.setText("");
            //  imageView.setImageResource(R.mipmap.ic_launcher);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * This method is to initialize views
     */
    private void initViews() {
       // nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

        textInputLayoutName = (TextInputLayout) findViewById(R.id.textInputLayoutName);
        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
     //   textInputLayoutConfirmPassword = (TextInputLayout) findViewById(R.id.textInputLayoutConfirmPassword);

        textInputEditTextName = (TextInputEditText) findViewById(R.id.textInputEditTextName);
        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword = (TextInputEditText) findViewById(R.id.textInputEditTextPassword);
     //   textInputEditTextConfirmPassword = (TextInputEditText) findViewById(R.id.textInputEditTextConfirmPassword);

        appCompatButtonRegister = (AppCompatButton) findViewById(R.id.appCompatButtonRegister);

        appCompatTextViewLoginLink = (AppCompatTextView) findViewById(R.id.appCompatTextViewLoginLink);

    }

    private void postDataToSQLite() {
        if (!inputValidation.isInputEditTextFilled(textInputEditTextName, textInputLayoutName, getString(R.string.error_message_name))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_password))) {
            return;
        }
        if (!inputValidation.isInputEditTextMatches(textInputEditTextPassword, textInputEditTextConfirmPassword,
                textInputLayoutConfirmPassword, getString(R.string.error_password_match))) {
            return;
        }




    }

    /**
     * This method is to empty all input edit text
     */

}
