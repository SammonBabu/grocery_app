package com.example.grocery.activities;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import com.example.grocery.R;
import com.example.grocery.SQLiteHelper;


import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;


/**
 * Created by lalit on 10/10/2016.
 */

public class UsersListActivity extends AppCompatActivity {

    ArrayList<User> userlist;
    UserAdapter adapter = null;
    ListView listempView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlist);
        listempView=(ListView)findViewById(R.id.listViewuser);
        userlist = new ArrayList<>();
        adapter = new UserAdapter(this, R.layout.activity_userview, userlist);
        listempView.setAdapter(adapter);
        SQLiteHelper sqLiteHelper = new SQLiteHelper(this, "Grocery.sqlite", null, 1);

        Cursor cursor = sqLiteHelper.getData("SELECT * FROM  registerus");
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
