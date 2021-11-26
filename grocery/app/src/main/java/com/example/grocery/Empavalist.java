package com.example.grocery;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.Collections;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import static com.example.grocery.Empmain.sqLiteHelper;

public class Empavalist extends AppCompatActivity {

    ArrayList<EmpAvaliability> empavalist;
    EmpavaAdapter adapter = null;
    ListView listempviewava;


    public EditText etSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avaliabilitylist);
        listempviewava=(ListView)findViewById(R.id.listempviewava);
        empavalist = new ArrayList<>();
        adapter = new EmpavaAdapter(this, R.layout.activity_avaview, empavalist,etSearch);
        listempviewava.setAdapter(adapter);
        //   sqLiteHelper = new SQLiteHelper(this, "easy.sqlite", null, 1);
        //  sqLiteHelper = new SQLiteHelper(this, "easy.sqlite", null, 1);
        // sqLiteHelper = new SQLiteHelper(context.this, "easy.sqlite", null, 1);

        sqLiteHelper = new SQLiteHelper(this, "Grocery.sqlite", null, 1);
        Cursor cursor = sqLiteHelper.getData("SELECT * FROM  tblavalbility");
        empavalist.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);

            String empemail = cursor.getString(1);

            empavalist.add(new EmpAvaliability( empemail, id));
        }
        Collections.reverse(empavalist);
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
