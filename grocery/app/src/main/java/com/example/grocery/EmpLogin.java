package com.example.grocery;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.grocery.activities.LoginActivity;
import com.example.grocery.activities.RegisterActivity;
import com.example.grocery.helpers.InputValidation;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.widget.NestedScrollView;
import static com.example.grocery.Empmain.sqLiteHelper;
public class EmpLogin extends AppCompatActivity {
    private final AppCompatActivity activity = EmpLogin.this;

    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;
    private TextInputLayout textInputLayoutUserName;
    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;
    private TextInputEditText textInputEditTextUserName;
    private AppCompatButton appCompatButtonLogin;

    private AppCompatTextView textViewLinkRegister;
   // private SQLiteHelper sqLiteHelper;
    private InputValidation inputValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_login);
        // getSupportActionBar().hide();

        initViews();
        sqLiteHelper = new SQLiteHelper(this, "Grocery.sqlite", null, 1);

        inputValidation = new InputValidation(activity);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            if (bundle.getString("some") != null) {
                Toast.makeText(getApplicationContext(), "data" + bundle.getString("some"), Toast.LENGTH_SHORT).show();
            }
        }
        //  appCompatButtonLogin
        appCompatButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=textInputEditTextEmail.getText().toString().trim();
                String pass=textInputEditTextPassword.getText().toString().trim();
                String name=   textInputEditTextUserName.getText().toString().trim();
                Cursor cursor=sqLiteHelper.getData("SELECT * from tblempoyee WHERE empemail='"+email+"' AND emppass='"+pass+"' AND empname='"+name+"'");


                if(cursor.moveToFirst())
                {
                   // Intent inewsfeed = new Intent(getApplicationContext(), navhome.class);
                    //startActivity(inewsfeed);
                    Intent accountsIntent = new Intent(activity, Empmenu.class);
                    accountsIntent.putExtra("EMAIL",textInputEditTextEmail.getText().toString().trim());
                    accountsIntent.putExtra("username",textInputEditTextUserName.getText().toString().trim());

                    startActivity(accountsIntent);

                }
                else
                { Toast.makeText(getApplicationContext(),"Invalid user and password",Toast.LENGTH_LONG).show();

                }
            }
        });








        textViewLinkRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent inewsfeed = new Intent(getApplicationContext(), Empmain.class);
                startActivity(inewsfeed);

            }
        });
    }

    /**
     * This method is to initialize views
     */
    private void initViews() {

      //  nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
        textInputEditTextUserName = (TextInputEditText) findViewById(R.id.textInputEditTextUserName);

        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword = (TextInputEditText) findViewById(R.id.textInputEditTextPassword);

        appCompatButtonLogin = (AppCompatButton) findViewById(R.id.appCompatButtonLogin);

        textViewLinkRegister = (AppCompatTextView) findViewById(R.id.textViewLinkRegister);

    }

}
