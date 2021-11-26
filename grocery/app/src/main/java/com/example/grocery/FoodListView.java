package com.example.grocery;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.grocery.activities.LoginActivity;

import java.util.ArrayList;
import java.util.Collections;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import static com.example.grocery.Main2Activity.sqLiteHelper;


public class FoodListView extends AppCompatActivity {

    // List<FoodCart> foodcartList;
    ArrayList<FoodCart> foodcartList;
    ListView listView;
    FoodAdapter adapter = null;
    private double subTotal = 0.0;
    TextView txtTotalcart,txtcemail,txtcuser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list);




        txtcemail = (TextView) findViewById(R.id.txtcemail);
        txtcuser = (TextView) findViewById(R.id.txtcuser);

        String emailFromIntent = getIntent().getStringExtra("EMAIL");
        txtcemail.setText(emailFromIntent);

        String usern = getIntent().getStringExtra("username");
        txtcuser.setText(usern);


        listView = (ListView) findViewById(R.id.listViewFC);
        txtTotalcart = (TextView) findViewById(R.id.txtTotalcart);
        foodcartList = new ArrayList<>();
        //  adapter = new FoodListAdapter(this, R.layout.food_items, list);
        adapter = new FoodAdapter(this, R.layout.activity_cart_view, foodcartList);
        listView.setAdapter(adapter);

        Button btncheckout = (Button) findViewById(R.id.btncheckout);


        //the delete operation
        btncheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(FoodListView.this);
                builder.setTitle("Do you want to continue shopping?");
                builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        try {
                            sqLiteHelper.deleteD();

                            Toast.makeText(getApplicationContext(), "*****HAPPY SHOPPING!!!******",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(FoodListView.this, LoginActivity.class);
                            startActivity(intent);



                        } catch (Exception e){
                            Log.e("error", e.getMessage());
                        }
                    }
                });
                builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });











        Cursor cursor = sqLiteHelper.getData("Select id,name,price,quantity,(price*quantity)from cartlst");
        foodcartList.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            int price = cursor.getInt(2);
            int quantity = cursor.getInt(3);
            int totalprice = cursor.getInt(4);
            // int totalcart = cursor.getInt(5);
            foodcartList.add(new FoodCart(name, price, quantity, totalprice, id));
        }
        Collections.reverse(foodcartList);

        adapter.notifyDataSetChanged();


        double subTotal = 0;
        for (FoodCart foodCart : foodcartList) {
            //  int quantity = ShoppingCartHelper.getProductQuantity(p);
            subTotal += foodCart.getTotalprice();
        }

        final TextView productPriceTextView = (TextView) findViewById(R.id.txtTotalcart);
       productPriceTextView.setText("Subtotal: " + subTotal);
        final String prod= productPriceTextView.getText().toString().trim();

        //notificationNumber.setText(String.valueOf(notification_number_counter));


        Button btnpay = findViewById(R.id.btnpay);



        btnpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("main activity", "item clicked");
                // startActivity(new Intent(BinAdapter.this,Binupdate.class).putExtra("items", (Parcelable) mDisplayedValues.get(position)));
                Intent r = (new Intent(getApplicationContext(),BuyMain.class));

                r.putExtra("EMAIL",txtcemail.getText().toString().trim());
                r.putExtra("username",txtcuser.getText().toString().trim());
                r.putExtra("Totalprice", prod);

             startActivity(r);


            }
        });

    }



}











