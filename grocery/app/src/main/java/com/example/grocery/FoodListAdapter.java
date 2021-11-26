package com.example.grocery;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.appcompat.app.AlertDialog;

import static com.example.grocery.Main2Activity.sqLiteHelper;



/**
 * Created by Quoc Nguyen on 13-Dec-16.
 */

public class FoodListAdapter extends BaseAdapter {
    //  public static SQLiteHelper sqLiteHelper;

    //   public static SQLiteHelper sqLiteHelper;

    public Context context;
    public  int layout;
    public ArrayList<Food> foodsList;
    public String emailFromIntent;
    public String usernme;
    int counter=0;
    private SharedPreferences preferences;
    public FoodListAdapter(Context context, int layout, ArrayList<Food> foodsList, String usernme, String emailFromIntent) {
        this.context = context;
        this.layout = layout;
        this.foodsList = foodsList;
        this.emailFromIntent = emailFromIntent;

        this.usernme = usernme;


    }



    @Override
    public int getCount() {
        return foodsList.size();
    }

    @Override
    public Object getItem(int position) {
        return foodsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{
        ImageView imageView;
        TextView txtName, txtPrice,txtitem;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View row = view;
        ViewHolder holder = new ViewHolder();

        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            //  LayoutInflater inflater = LayoutInflater.from(mCtx);
            row = inflater.inflate(layout, null);
            holder.txtitem = (TextView) row.findViewById(R.id.txtitem);
            holder.txtName = (TextView) row.findViewById(R.id.txtName);
            holder.txtPrice = (TextView) row.findViewById(R.id.txtPrice);
            holder.imageView = (ImageView) row.findViewById(R.id.imgFood);
            row.setTag(holder);
        }
        else {
            holder = (ViewHolder) row.getTag();
        }

        final Food food = foodsList.get(position);
        holder.txtitem.setText(food.getCatg());
        holder.txtName.setText(food.getName());
        holder.txtPrice.setText(String.valueOf(food.getPrice()));
        // editTextSalary.setText(String.valueOf(employee.getSalary()));
        byte[] foodImage = food.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(foodImage, 0, foodImage.length);
        holder.imageView.setImageBitmap(bitmap);
        TextView buttonEdit = row.findViewById(R.id.buttonEditFood);
        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFood(food);
            }
        });


        return row;
    }

    private void addFood(final Food food) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_cart, null);
        builder.setView(view);
        Button btn_ListView = (Button) view.findViewById(R.id.btn_ListView);
        Button btn_ADDFOOD = (Button) view.findViewById(R.id.btn_ADDFOOD);
          final EditText edtName = view.findViewById(R.id.edtName);
          final EditText edtPrice  = view.findViewById(R.id.edtPrice);
          final EditText editquant = view.findViewById(R.id.editquant);
      // final EditText e = view.findViewById(emailFromIntent);
         final TextView txtmail = view.findViewById(R.id.txtmail);
          final Button btnminu = view.findViewById(R.id.btnminu);
        final Button btnplus = view.findViewById(R.id.btnplus);
        //  final EditText editstatus = view.findViewById(R.id.editStatus);
       // final Spinner spinnerStatus = view.findViewById(R.id.spinnerStatus);
        txtmail.setText(usernme);
         //edtName.setText(String.valueOf(food.getBin()));
        edtName.setText(food.getName());
       edtPrice.setText(String.valueOf(food.getPrice()));
       // editquant.setText(String.valueOf(food.getQuantity()));
        //editTextSalary.setText(String.valueOf(employee.getSalary()));
        // editComplaint.setText(complaint.getComplaint());
      //  editquant.setText(+score);
        // editstatus.setText(complaint.getStatus());

        btnminu.setOnClickListener(new View.OnClickListener() {

           // int counter=0;
            public void onClick(View view) {

                counter--;
                editquant.setText(String.valueOf(+counter));
            }
        });


        btnplus.setOnClickListener(new View.OnClickListener() {
          //  int counter = 0;
            public void onClick(View v) {
                counter ++;
                editquant.setText(String.valueOf(+counter));
            }
        });

        editquant.setText(String.valueOf(+counter));

        final AlertDialog dialog = builder.create();
        dialog.show();


       // sqLiteHelper = new SQLiteHelper(this, "FoodSDB.sqlite", null, 1);
        sqLiteHelper.queryData("CREATE TABLE IF NOT EXISTS cartlst(Id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, price VARCHAR ,quantity VARCHAR,email varchar)");
        btn_ADDFOOD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


               // Intent a = new Intent(context, FoodList.class);
              //  context.startActivity(a);




                try{
                    sqLiteHelper.insertDatafcart(
                          edtName.getText().toString().trim(),
                            Integer.parseInt(edtPrice.getText().toString().trim()),
                            Integer.parseInt(editquant.getText().toString().trim()),
                            txtmail.getText().toString().trim()

                    );
                    Toast.makeText(context.getApplicationContext(), "Added successfully!", Toast.LENGTH_SHORT).show();
                    edtName.setText("");
                  // edtPrice.setText("");
                 //  editquant.setText("");

                }
                catch (Exception e){
                    e.printStackTrace();
                }


                int counter=0;
                preferences = PreferenceManager.getDefaultSharedPreferences(context);
                counter = preferences.getInt("image_data", 0);
                counter++;

                // store the value of counter after incrementing it by 1
                SharedPreferences.Editor edit = preferences.edit();
                edit.putInt("image_data", counter);
                edit.commit();





            }
        });


        btn_ListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  Intent intent = new Intent(packageContext:FoodListAdapter, FoodListView.class);
                //   context.startActivity(intent);
                 Intent a =(new Intent( context.getApplicationContext(),FoodListView.class));
                a.putExtra("EMAIL",emailFromIntent);
                a.putExtra("username",usernme);

                 context.startActivity(a);



            }
        });







    }




}
