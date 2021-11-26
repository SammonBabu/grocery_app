package com.example.grocery;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

import androidx.appcompat.app.AlertDialog;

import static com.example.grocery.Empmain.sqLiteHelper;

public class BuyAdapter extends BaseAdapter  implements Filterable {
    //    public  SQLiteHelper sqLiteHelper;

    private EditText etSearch;
    private Context context;
    private  int layout;
    //  private ArrayList<Employee> elist;
    public ArrayList<Buy> mOriginalValues; // Original Values
    public ArrayList<Buy> mDisplayedValues;
    public LinearLayout llContainer;
    public BuyAdapter(Context context, int layout, ArrayList<Buy> mProductArrayListb) {
        this.context = context;
        this.layout = layout;
        //  this.elist = elist;

        this.etSearch = etSearch;
        this.llContainer = llContainer;
        this.etSearch = etSearch;
        this.mOriginalValues = mProductArrayListb;
        this.mDisplayedValues = mProductArrayListb;



        //  sqLiteHelper = new SQLiteHelper(this, "myempbook.sqlite", null, 1);
    }



    @Override
    public int getCount() {
        return mDisplayedValues.size();
    }

    @Override
    public Object getItem(int position) {
        return mDisplayedValues.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    private class ViewHolder{
        TextView txtvusername,txtvmail,txtvaddress,txtphone,txtcash,txtcashmode,txtvstatus,txtorderId;
        TextView  txtdrlmail,txtvdrlmail;
        Spinner spinnerStatus;
        LinearLayout llContainer;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {

        View row = view;
        BuyAdapter.ViewHolder holder = new BuyAdapter.ViewHolder();

        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            //  LayoutInflater inflater = LayoutInflater.from(mCtx);
            row = inflater.inflate(layout, null);


            holder.txtorderId = (TextView) row.findViewById(R.id.txtorderId);
            holder.txtvusername = (TextView) row.findViewById(R.id.txtvusername);
            holder.txtvmail = (TextView) row.findViewById(R.id.txtvmail);
            holder.txtvaddress = (TextView) row.findViewById(R.id.txtvaddress);
            holder.txtphone = (TextView) row.findViewById(R.id.txtphone);
            holder.txtcash = (TextView) row.findViewById(R.id.txtcash);

            holder.txtvdrlmail = (TextView) row.findViewById(R.id.  txtvdrlmail);
            holder.txtcashmode = (TextView) row.findViewById(R.id.  txtcashmode);

            holder.txtvstatus = (TextView) row.findViewById(R.id.  txtvstatus);

            holder.llContainer = (LinearLayout) row.findViewById(R.id.llContainer);
            // holder. txtTotalcart = (TextView) row.findViewById(R.id.txtTotalcart);
            row.setTag(holder);
        }
        else {
            holder = (BuyAdapter.ViewHolder) row.getTag();
        }

        final Buy buy = mDisplayedValues.get(position);
        holder.txtorderId.setText(String.valueOf(buy.getId()));
        holder.txtvusername.setText(buy.getUsname());
        holder.txtvmail.setText(buy.getUsemail());
        holder.txtvaddress.setText(buy.getUsaddress());
        holder.txtphone.setText(buy.getUsphone());
        holder.txtcash.setText(buy.getCash());
        holder.txtvdrlmail.setText(buy.getDemail());
        holder.txtcashmode.setText(buy.getCashmode());
        holder.txtvstatus.setText(buy.getStatus());
        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("main activity", "item clicked");
                // startActivity(new Intent(BinAdapter.this,Binupdate.class).putExtra("items", (Parcelable) mDisplayedValues.get(position)));
                // Intent intentRegister = (new Intent( context.getApplicationContext(),BinList.class));
                //  context.startActivity(intentRegister);

                //  int id;

                updatebuy(buy,position);
            }


        });
        return row;
        // Votequest.sqLiteHelper = new SQLiteHelper(this, "myempbook.sqlite", null, 1);


        //TextView txtTotalcart = view.findViewById(R.id.txtTotalcart)
    }





    private void updatebuy(final Buy buy, final int position) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(context);

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_update, null);
        builder.setView(view);

        final Button btnup = view.findViewById(R.id.btnup);
        //edarea.setText(bin.getArea());

        final Button btndel = view.findViewById(R.id.btndel);
        // final Button btnwork = view.findViewById(R.id.btnwork);


        //   sqLiteHelper = new SQLiteHelper(this, "myempbook.sqlite", null, 1);




        btndel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Log.e("main activity","item clicked");
                sqLiteHelper = new SQLiteHelper(context, "Grocery.sqlite", null, 1);
                Cursor c = sqLiteHelper.getData("SELECT id FROM paycarts");
                ArrayList<Integer> arrID = new ArrayList<Integer>();
                while (c.moveToNext()) {
                    arrID.add(c.getInt(0));
                }
                // show dialog update at here
                showDialogWorkDelete( arrID.get(position));
                // updateb(bin);
            }


        });
        //sqLiteHelper = new SQLiteHelper(context, "Grocery.sqlite", null, 1);



        btnup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Log.e("main activity","item clicked");
                sqLiteHelper = new SQLiteHelper(context, "Grocery.sqlite", null, 1);

                Cursor cursor = sqLiteHelper.getData("SELECT id FROM paycarts");
                ArrayList<Integer> arrID = new ArrayList<Integer>();
                while (cursor.moveToNext()) {
                    arrID.add(cursor.getInt(0));
                }
                // show dialog update at here
                showDialogstatusUpdatebuy(buy, arrID.get(position));
                // updateb(bin);
            }


        });

        final AlertDialog dialog = builder.create();
        dialog.show();


    }

    private void showDialogstatusUpdatebuy(Buy buy, final int position) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_work,  null);
        builder.setView(view);
        final SQLiteHelper sqLiteHelper = new SQLiteHelper(context, "Grocery.sqlite", null, 1);

        final TextView   txtwemail =view. findViewById(R.id.txtwemail);
        txtwemail.setText(buy.getUsname());
        final String usemail= txtwemail.getText().toString().trim();

        final TextView   txtwuser = view.findViewById(R.id.txtwuser);
        txtwuser.setText(buy.getUsemail());
        //final TextView   txtwemail =view. findViewById(R.id.txtwemail);
        // txtwemail.setText(buy.getUsemail());
        final TextView   txtwadd = view.findViewById(R.id.txtwadd);
        txtwadd.setText(buy.getUsaddress());

        final TextView  txtwphne =view. findViewById(R.id.txtwphne);
        txtwphne.setText(buy.getUsphone());


        final TextView txtwcash = view.findViewById(R.id.txtwcash);
        txtwcash.setText(buy.getCash());

        final TextView   txtwcashmode = view.findViewById(R.id.txtwcashmode);
        txtwcashmode.setText(buy.getCashmode());


        //   txtwcashmode = findViewById(R.id.txtwcashmode);
        final TextView   txtwstatus =view. findViewById(R.id.txtwstatus);
        txtwstatus.setText(buy.getStatus());


        //final EditText   edmail =view. findViewById(R.id.edmail);


        final TextView    txtwdemail = view.findViewById(R.id.txtwdemail);
        txtwdemail.setText(buy.getDemail());

        final Spinner  spinnerStatus = (Spinner)view. findViewById(R.id.spinnerStatus);

        final Button btndeliveryboy = view.findViewById(R.id.btndeliveryboy);

        btndeliveryboy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Log.e("main activity","item clicked");
                btndeliveryboy.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Cursor res = sqLiteHelper.getData("SELECT * FROM tblavalbility  ");
                                if(res.getCount() == 0) {
                                    // show message
                                    showMessage("Error","Nothing found");
                                    return;
                                }

                                StringBuffer buffer = new StringBuffer();
                                while (res.moveToNext()) {
                                    buffer.append("empemail :"+ res.getString(1)+"\n");

                                    //  buffer.append("Marks :"+ res.getString(3)+"\n\n");
                                }

                                // Show all data
                                showMessage("Cart",buffer.toString());
                            }
                        }
                );
            }



        });

        final Button btnwcart = view.findViewById(R.id.btnwcart);

        btnwcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Log.e("main activity","item clicked");
                btnwcart.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Cursor res =sqLiteHelper.getData("SELECT * FROM cartlst where email='"+usemail+"' ");
                                if(res.getCount() == 0) {
                                    // show message
                                    showMessage("Error","Nothing found");
                                    return;
                                }

                                StringBuffer buffer = new StringBuffer();
                                while (res.moveToNext()) {
                                    buffer.append("name :"+ res.getString(1)+"\n");
                                    buffer.append("price :"+ res.getInt(2)+"\n");
                                    buffer.append("quantity :"+ res.getInt(3)+"\n");
                                    //  buffer.append("Marks :"+ res.getString(3)+"\n\n");
                                }

                                // Show all data
                                showMessage("Cart",buffer.toString());
                            }
                        }
                );
            }



        });

        final Button btnUpdate = (Button) view.findViewById(R.id.btnass);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    sqLiteHelper.updatepaycart(

                            txtwuser.getText().toString().trim(),
                            txtwemail.getText().toString().trim(),
                            txtwadd.getText().toString().trim(),
                            txtwphne.getText().toString().trim(),
                            txtwcash.getText().toString().trim(),
                            txtwcashmode.getText().toString().trim(),
                            txtwdemail.getText().toString().trim(),
                            spinnerStatus.getSelectedItem().toString().trim(),
                            position

                    );
                    //  dialog.dismiss();
                    Toast.makeText(context.getApplicationContext(), "Update successfully!!!",Toast.LENGTH_SHORT).show();
                }
                catch (Exception error) {
                    Log.e("Update error", error.getMessage());
                }
                updateworklist();
            }
        });

        final AlertDialog dialog = builder.create();
        dialog.show();

    }

    private void showDialogWorkDelete(final int idwork){
        final android.app.AlertDialog.Builder dialogDelete = new android.app.AlertDialog.Builder(context);

        dialogDelete.setTitle("Warning!!");
        dialogDelete.setMessage("Are you sure you want to this delete?");
        dialogDelete.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    sqLiteHelper.deletepaycart(idwork);
                    Toast.makeText(context.getApplicationContext(), "Delete successfully!!!",Toast.LENGTH_SHORT).show();
                } catch (Exception e){
                    Log.e("error", e.getMessage());
                }
                updateworklist();
            }
        });

        dialogDelete.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialogDelete.show();

    }


    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }













    private void updateworklist() {
        SQLiteHelper sqLiteHelper = new SQLiteHelper(context, "Grocery.sqlite", null, 1);

        // get all data from sqlite
        Cursor cursor = sqLiteHelper.getData("SELECT * FROM paycarts");
        mDisplayedValues.clear();
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


            mDisplayedValues.add(new Buy(usname, usemail, usaddress, usphone, cash, cashmode,demail,status, id));
        }

        //Collections.reverse(mDisplayedValues);
        notifyDataSetChanged();
    }





    public Filter getFilter() {
        Filter filter = new Filter() {

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                mDisplayedValues = (ArrayList<Buy>) results.values; // has the filtered values
                notifyDataSetChanged();  // notifies the data with new filtered values
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();        // Holds the results of a filtering operation in values
                ArrayList<Buy> FilteredArrList = new ArrayList<Buy>();

                if (mOriginalValues == null) {
                    mOriginalValues = new ArrayList<Buy>(mDisplayedValues); // saves the original data in mOriginalValues
                }

                /********
                 *
                 *  If constraint(CharSequence that is received) is null returns the mOriginalValues(Original) values
                 *  else does the Filtering and returns FilteredArrList(Filtered)
                 *
                 ********/
                if (constraint == null || constraint.length() == 0) {

                    // set the Original result to return
                    results.count = mOriginalValues.size();
                    results.values = mOriginalValues;
                } else {
                    constraint = constraint.toString().toLowerCase();
                    for (int i = 0; i < mOriginalValues.size(); i++) {
                        String data = mOriginalValues.get(i).demail;
                        if (data.toLowerCase().startsWith(constraint.toString())) {


                            FilteredArrList.add(new Buy(mOriginalValues.get(i).usname,mOriginalValues.get(i).usemail ,mOriginalValues.get(i).usaddress,mOriginalValues.get(i).usphone,mOriginalValues.get(i).cash,mOriginalValues.get(i).cashmode,mOriginalValues.get(i).demail,mOriginalValues.get(i).status, mOriginalValues.get(i).id));
                        }
                        String data1 = mOriginalValues.get(i).usname;

                        if (data1.toLowerCase().startsWith(constraint.toString())) {


                            FilteredArrList.add(new Buy(mOriginalValues.get(i).usname,mOriginalValues.get(i).usemail ,mOriginalValues.get(i).usaddress,mOriginalValues.get(i).usphone,mOriginalValues.get(i).cash,mOriginalValues.get(i).cashmode,mOriginalValues.get(i).demail,mOriginalValues.get(i).status, mOriginalValues.get(i).id));
                        }

                    }
                    // set the Filtered result to return
                    results.count = FilteredArrList.size();
                    results.values = FilteredArrList;
                }
                return results;
            }
        };
        return filter;
    }


}
