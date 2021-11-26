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


public class EmpAdapter extends BaseAdapter  implements Filterable {
   //    public  SQLiteHelper sqLiteHelper;

    private EditText etSearch;
    private Context context;
    private  int layout;
  //  private ArrayList<Employee> elist;
    public ArrayList<Employee> mOriginalValues; // Original Values
    public ArrayList<Employee> mDisplayedValues;
    public LinearLayout llContainer;
    public EmpAdapter(Context context, int layout, ArrayList<Employee> mProductArrayListw,EditText etSearch) {
        this.context = context;
        this.layout = layout;
      //  this.elist = elist;

        this.etSearch = etSearch;
        this.llContainer = llContainer;
        this.etSearch = etSearch;
        this.mOriginalValues = mProductArrayListw;
        this.mDisplayedValues = mProductArrayListw;



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
        LinearLayout llContainer;
        TextView txtEmpName,txtEmpEmail,txtEmpPassword,txtEmpMobile,txtEmpAddress,txtpass;

    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {

        View row = view;
        EmpAdapter.ViewHolder holder = new EmpAdapter.ViewHolder();

        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            //  LayoutInflater inflater = LayoutInflater.from(mCtx);
            row = inflater.inflate(layout, null);

            holder.txtEmpName = (TextView) row.findViewById(R.id.textViewName);
            holder.txtEmpEmail = (TextView) row.findViewById(R.id.textViewEmpEmail);
            holder.txtpass = (TextView) row.findViewById(R.id.txtpass);


            holder.txtEmpPassword = (TextView) row.findViewById(R.id.textEmpPassword);
            holder.txtEmpMobile = (TextView) row.findViewById(R.id.textViewEmpMobile);
            holder.txtEmpAddress = (TextView) row.findViewById(R.id.textViewEmpAddress);
            holder.llContainer = (LinearLayout) row.findViewById(R.id.llContainer);
            // holder. txtTotalcart = (TextView) row.findViewById(R.id.txtTotalcart);
            row.setTag(holder);
        }
        else {
            holder = (EmpAdapter.ViewHolder) row.getTag();
        }

        final Employee employee = mDisplayedValues.get(position);

       holder.txtEmpName.setText(employee.getEmpname());
        holder.txtEmpEmail.setText(employee.getEmpemail());
        holder.txtpass.setText(employee.getEmppass());
        holder.txtEmpPassword.setText(employee.getEmppassword());
        holder.txtEmpMobile.setText(employee.getEmpmobile());
        holder.txtEmpAddress.setText(employee.getEmpaddress());

        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("main activity", "item clicked");
                // startActivity(new Intent(BinAdapter.this,Binupdate.class).putExtra("items", (Parcelable) mDisplayedValues.get(position)));
                // Intent intentRegister = (new Intent( context.getApplicationContext(),BinList.class));
                //  context.startActivity(intentRegister);

                //  int id;

                updateworka(employee,position);
            }


        });
        return row;
       // Votequest.sqLiteHelper = new SQLiteHelper(this, "myempbook.sqlite", null, 1);


        //TextView txtTotalcart = view.findViewById(R.id.txtTotalcart)
    }





    private void updateworka(final Employee employee, final int position) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(context);

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_update2, null);
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
                //SQLiteHelper sqLiteHelper = new SQLiteHelper(this);
                Cursor c = sqLiteHelper.getData("SELECT id FROM tblempoyee");
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

                Cursor cursor = sqLiteHelper.getData("SELECT id FROM tblempoyee");
                ArrayList<Integer> arrID = new ArrayList<Integer>();
                while (cursor.moveToNext()) {
                    arrID.add(cursor.getInt(0));
                }
                // show dialog update at here
                showDialogstatusUpdate(employee, arrID.get(position));
                // updateb(bin);
            }


        });

        final AlertDialog dialog = builder.create();
        dialog.show();


    }

    private void showDialogstatusUpdate(Employee employee, final int position) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.update_employee, null);
        builder.setView(view);
        final EditText edname = view.findViewById(R.id.edname);
        edname.setText(employee.getEmpname());
        final EditText edemail = (EditText) view.findViewById(R.id.edemail);
        edemail.setText(employee.getEmpemail());

        final Spinner spinnerupdes = (Spinner) view.findViewById(R.id.spinnerupdes);
     //   edpass.setText(employee.getEmppassword());


        final EditText edmob = (EditText) view.findViewById(R.id.edmob);
        edmob.setText(employee.getEmpmobile());

        final EditText edaddress = (EditText) view.findViewById(R.id.edaddress);
        //  edtbinid.setText(bin.getId());
        edaddress.setText(String.valueOf(employee.getEmpaddress()));

        final EditText edpass = (EditText) view.findViewById(R.id.edpass);
        //  edtbinid.setText(bin.getId());
        edpass.setText(String.valueOf(employee.getEmppass()));








        final Button buttonupdate = (Button) view.findViewById(R.id.buttonupdate);
        buttonupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    sqLiteHelper.updateem(
                            edname.getText().toString().trim(),
                            edemail.getText().toString().trim(),
                            spinnerupdes.getSelectedItem().toString().trim(),
                            edmob.getText().toString().trim(),
                            edaddress.getText().toString().trim(),
                            edpass.getText().toString().trim(),
                            position

                    );
                    //  dialog.dismiss();
                    Toast.makeText(context.getApplicationContext(), "Updated Successfully!!!",Toast.LENGTH_SHORT).show();
                }
                catch (Exception error) {
                    Log.e("Update error", error.getMessage());
                }


                Intent intentRegister = (new Intent( context.getApplicationContext(),EmpList.class));
                context.startActivity(intentRegister);

                updateemp();
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
                    sqLiteHelper.deleteDataemp(idwork);
                    Toast.makeText(context.getApplicationContext(), "Delete successfully!!!",Toast.LENGTH_SHORT).show();
                } catch (Exception e){
                    Log.e("error", e.getMessage());
                }
                updateemp();
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

    private void updateemp() {

        // get all data from sqlite
        Cursor cursor = sqLiteHelper.getData("SELECT * FROM tblempoyee");
        mDisplayedValues.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String empname = cursor.getString(1);
            String empemail = cursor.getString(2);
            String emppassword = cursor.getString(3);
            String empmobile = cursor.getString(4);
            String empaddress = cursor.getString(5);
            String emppass = cursor.getString(6);
            mDisplayedValues.add(new Employee(empname, empemail, emppassword, empmobile, empaddress,emppass, id));
        }
       // Collections.reverse(mDisplayedValues);
        notifyDataSetChanged();

    }





    public Filter getFilter() {
        Filter filter = new Filter() {

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                mDisplayedValues = (ArrayList<Employee>) results.values; // has the filtered values
                notifyDataSetChanged();  // notifies the data with new filtered values
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();        // Holds the results of a filtering operation in values
                ArrayList<Employee> FilteredArrList = new ArrayList<Employee>();

                if (mOriginalValues == null) {
                    mOriginalValues = new ArrayList<Employee>(mDisplayedValues); // saves the original data in mOriginalValues
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
                        String data = mOriginalValues.get(i).empname;
                        if (data.toLowerCase().startsWith(constraint.toString())) {


                            FilteredArrList.add(new Employee(mOriginalValues.get(i).empname, mOriginalValues.get(i).empemail, mOriginalValues.get(i).emppassword, mOriginalValues.get(i).empmobile, mOriginalValues.get(i).empaddress,mOriginalValues.get(i).emppass,  mOriginalValues.get(i).id));
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
