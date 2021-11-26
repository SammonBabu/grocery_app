package com.example.grocery;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import static com.example.grocery.ReportMain.sqLiteHelper;

public class MyReportList extends AppCompatActivity {

    public ArrayList<Report> listreport;
    public ReportAdapter adapter = null;

    public LinearLayout llContainer;

    public ListView listviewreport;
    public TextView quesview;
    public  TextView textviewname1;
    public  TextView txtusnamecom,txtemailcom;

    public EditText etSearch;

    public static void clear() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportlist);

        listviewreport = (ListView) findViewById(R.id.listviewreport);
        listreport = new ArrayList<>();
        adapter = new ReportAdapter(this, R.layout.activity_reportview, listreport,llContainer,etSearch);
        listviewreport.setAdapter(adapter);



        txtusnamecom=(TextView) findViewById(R.id.txtusnamecom);
        txtemailcom=(TextView)findViewById(R.id.txtemailcom);



        String emailFromIntents = getIntent().getStringExtra("EMAIL");
        txtemailcom.setText(emailFromIntents);
        String uscmail= txtemailcom.getText().toString().trim();

        String userns = getIntent().getStringExtra("username");
        txtusnamecom.setText(userns);
        sqLiteHelper = new SQLiteHelper(this, "Grocery.sqlite", null, 1);

        Cursor cursor = sqLiteHelper.getData("SELECT * FROM  complainttbl where usemail='"+uscmail+"'");
        listreport.clear();


        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String username = cursor.getString(1);
            String email = cursor.getString(2);
            String complaint = cursor.getString(3);
            String compdate = cursor.getString(4);
            String status = cursor.getString(5);

            listreport.add(new Report(id, username,email, complaint, compdate, status));
        }
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