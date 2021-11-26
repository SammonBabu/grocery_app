package com.example.grocery;

import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import static com.example.grocery.Main2Activity.sqLiteHelper;

public class BuyList extends AppCompatActivity {

    ArrayList<Buy> buylist;
    BuyAdapter adapter = null;
    ListView listbuyview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_list);
        listbuyview = (ListView) findViewById(R.id.buylist);
        buylist = new ArrayList<>();
        adapter = new BuyAdapter(this, R.layout.activity_buy_view, buylist);
        listbuyview.setAdapter(adapter);


       SQLiteHelper sqLiteHelper = new SQLiteHelper(this, "Grocery.sqlite", null, 1);

        Cursor cursor = sqLiteHelper.getData("SELECT * FROM paycarts");
        buylist.clear();


        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String usname = cursor.getString(1);
            String usemail = cursor.getString(2);
            String usaddress = cursor.getString(3);
            String usphone = cursor.getString(4);
            String cash = cursor.getString(5);
            String cashmode = cursor.getString(6);
            String demail = cursor.getString(7);

            String status = cursor.getString(8);


            buylist.add(new Buy(usname, usemail, usaddress, usphone, cash, cashmode,demail,status, id));
        }
       // Collections.reverse(buylist);
        adapter.notifyDataSetChanged();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        getMenuInflater().inflate(R.menu.search_menu,menu);

        MenuItem menuItem = menu.findItem(R.id.searchView);

        SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                Log.e("Main"," data search"+newText);

                adapter.getFilter().filter(newText);



                return true;
            }
        });


        return true;

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();


        if(id == R.id.searchView){

            return true;
        }
        return super.onOptionsItemSelected(item);
    }



}
