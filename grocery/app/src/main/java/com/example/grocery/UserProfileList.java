package com.example.grocery;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;


import com.example.grocery.activities.User;
import com.example.grocery.activities.UserAdapter;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.grocery.Empmain.sqLiteHelper;

public class UserProfileList extends AppCompatActivity {

    ArrayList<com.example.grocery.activities.User> userlist;
    UserAdapter adapter = null;
    ListView listempView;
    TextView   txtususername,txtusemail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlist);
        listempView=(ListView)findViewById(R.id.listViewuser);
        userlist = new ArrayList<>();
        adapter = new UserAdapter(this, R.layout.activity_userview, userlist);
        listempView.setAdapter(adapter);




        txtususername=(TextView) findViewById(R.id.txtususername);
        txtusemail=(TextView)findViewById(R.id.txtusemail);



        String emailFromIntents = getIntent().getStringExtra("EMAIL");
        txtusemail.setText(emailFromIntents);
        String usemail= txtusemail.getText().toString().trim();

        String userns = getIntent().getStringExtra("username");
        txtususername.setText(userns);
        SQLiteHelper sqLiteHelper = new SQLiteHelper(this, "Grocery.sqlite", null, 1);

        Cursor cursor = sqLiteHelper.getData("SELECT * FROM  registerus where user_email='"+usemail+"'");
        userlist.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String email = cursor.getString(2);
            String password = cursor.getString(3);
            userlist.add(new User(id,name, email, password));
        }

        adapter.notifyDataSetChanged();

    }


}
