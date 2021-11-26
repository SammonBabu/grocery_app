package com.example.grocery;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


import com.example.grocery.activities.LoginActivity;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class navhome extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TextView txtemail,txtuser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navhome);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        txtemail = (TextView) findViewById(R.id.txtemail);
        txtuser = (TextView) findViewById(R.id.txtuser);
        //   tvemail.setText(getIntent().getExtras().getString("Email"));

        String emailFromIntent = getIntent().getStringExtra("EMAIL");
        txtemail.setText(emailFromIntent);

        String usern = getIntent().getStringExtra("username");
        txtuser.setText(usern);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navhome, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if(id == R.id.nav_home){
            Intent ina = new Intent(getApplicationContext(), navhome.class);
            ina.putExtra("EMAIL",txtemail.getText().toString().trim());
            ina.putExtra("username",txtuser.getText().toString().trim());

            startActivity(ina);
        }
        else if (id == R.id.nav_camera) {
            Intent inb = new Intent(getApplicationContext(), ReportMain.class);
            inb.putExtra("EMAIL",txtemail.getText().toString().trim());
            inb.putExtra("username",txtuser.getText().toString().trim());

            startActivity(inb);

        } else if (id == R.id.nav_gallery) {
            Intent inc = new Intent(getApplicationContext(), UserProfileList.class);
            inc.putExtra("EMAIL",txtemail.getText().toString().trim());
            inc.putExtra("username",txtuser.getText().toString().trim());

            startActivity(inc);

        } else if (id == R.id.nav_slideshow) {
            Intent ind = new Intent(getApplicationContext(), LoginActivity.class);
            ind.putExtra("EMAIL",txtemail.getText().toString().trim());
            ind.putExtra("username",txtuser.getText().toString().trim());

            startActivity(ind);
        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }





    public void veggies(View view){
        Intent g = new Intent(this,FoodListv.class);



        g.putExtra("EMAIL",txtemail.getText().toString().trim());
        g.putExtra("username",txtuser.getText().toString().trim());

        startActivity(g);
    }
    public void fruit(View view){
        Intent l = new Intent(this,FoodList.class);
        l.putExtra("EMAIL",txtemail.getText().toString().trim());
        l.putExtra("username",txtuser.getText().toString().trim());
        startActivity(l);
    }



    public void bakery(View view){
        Intent b = new Intent(this,FoodListbak.class);
        b.putExtra("EMAIL",txtemail.getText().toString().trim());
        b.putExtra("username",txtuser.getText().toString().trim());
        startActivity(b);
    }

    public void foodgrain(View view){
        Intent f = new Intent(this,FoodListfg.class);
        f.putExtra("EMAIL",txtemail.getText().toString().trim());
        f.putExtra("username",txtuser.getText().toString().trim());
        startActivity(f);
    }
}
