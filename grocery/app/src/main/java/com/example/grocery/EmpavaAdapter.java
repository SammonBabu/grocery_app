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

public class EmpavaAdapter extends BaseAdapter implements Filterable {
    //    public  SQLiteHelper sqLiteHelper;

    private EditText etSearch;
    private Context context;
    private int layout;
    //  private ArrayList<Employee> elist;
    public ArrayList<EmpAvaliability> mOriginalValues; // Original Values
    public ArrayList<EmpAvaliability> mDisplayedValues1;
    public LinearLayout llContainer;

    public EmpavaAdapter(Context context, int layout, ArrayList<EmpAvaliability> mProductArrayListw, EditText etSearch) {
        this.context = context;
        this.layout = layout;
        //  this.elist = elist;

        this.etSearch = etSearch;
        this.llContainer = llContainer;
        this.etSearch = etSearch;
        this.mOriginalValues = mProductArrayListw;
        this.mDisplayedValues1 = mProductArrayListw;


        //  sqLiteHelper = new SQLiteHelper(this, "myempbook.sqlite", null, 1);
    }


    @Override
    public int getCount() {
        return mDisplayedValues1.size();
    }

    @Override
    public Object getItem(int position) {
        return mDisplayedValues1.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    private class ViewHolder {
        LinearLayout llContainer;
        TextView txtavmail, txtEmpEmail, txtEmpPassword, txtEmpMobile, txtEmpAddress, txtpass;

    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {

        View row = view;
        EmpavaAdapter.ViewHolder holder = new EmpavaAdapter.ViewHolder();

        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            //  LayoutInflater inflater = LayoutInflater.from(mCtx);
            row = inflater.inflate(layout, null);

            holder.txtavmail = (TextView) row.findViewById(R.id.txtavmail);

            holder.llContainer = (LinearLayout) row.findViewById(R.id.llContainer);
            // holder. txtTotalcart = (TextView) row.findViewById(R.id.txtTotalcart);
            row.setTag(holder);
        } else {
            holder = (EmpavaAdapter.ViewHolder) row.getTag();
        }

        final EmpAvaliability empAvaliability = mDisplayedValues1.get(position);

        holder.txtavmail.setText(empAvaliability.getEmpemail());


        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("main activity", "item clicked");
                // startActivity(new Intent(BinAdapter.this,Binupdate.class).putExtra("items", (Parcelable) mDisplayedValues.get(position)));
                // Intent intentRegister = (new Intent( context.getApplicationContext(),BinList.class));
                //  context.startActivity(intentRegister);

                //  int id;

                updateemail(empAvaliability, position);
            }


        });
        return row;
        // Votequest.sqLiteHelper = new SQLiteHelper(this, "myempbook.sqlite", null, 1);


        //TextView txtTotalcart = view.findViewById(R.id.txtTotalcart)
    }


    private void updateemail(final EmpAvaliability empAvaliability, final int position) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(context);

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_avaliability, null);
        builder.setView(view);


        //edarea.setText(bin.getArea());

        final Button btn_adel = view.findViewById(R.id.btn_adel);
        // final Button btnwork = view.findViewById(R.id.btnwork);


        //   sqLiteHelper = new SQLiteHelper(this, "myempbook.sqlite", null, 1);


        btn_adel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Log.e("main activity","item clicked");
                //SQLiteHelper sqLiteHelper = new SQLiteHelper(this);
                Cursor c = sqLiteHelper.getData("SELECT id FROM tblavalbility");
                ArrayList<Integer> arrID = new ArrayList<Integer>();
                while (c.moveToNext()) {
                    arrID.add(c.getInt(0));
                }
                // show dialog update at here
                showDialogWorkDelete(arrID.get(position));
                // updateb(bin);
            }


        });
        //sqLiteHelper = new SQLiteHelper(context, "Grocery.sqlite", null, 1);


        final AlertDialog dialog = builder.create();
        dialog.show();


    }


    private void showDialogWorkDelete(final int idwork) {
        final android.app.AlertDialog.Builder dialogDelete = new android.app.AlertDialog.Builder(context);

        dialogDelete.setTitle("Warning!!");
        dialogDelete.setMessage("Are you sure you want to this delete?");
        dialogDelete.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    sqLiteHelper.deleteava(idwork);
                    Toast.makeText(context.getApplicationContext(), "Delete successfully!!!", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Log.e("error", e.getMessage());
                }
                updateavalia();
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

    private void updateavalia() {

        // get all data from sqlite
        Cursor cursor = sqLiteHelper.getData("SELECT * FROM tblavalbility");
        mDisplayedValues1.clear();
        while (cursor.moveToNext()) {
        int id = cursor.getInt(0);

        String empemail = cursor.getString(1);

            mDisplayedValues1.add(new EmpAvaliability(empemail, id));
    }
    notifyDataSetChanged();
}

    public Filter getFilter() {
        Filter filter = new Filter() {

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                mDisplayedValues1 = (ArrayList<EmpAvaliability>) results.values; // has the filtered values
                notifyDataSetChanged();  // notifies the data with new filtered values
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();        // Holds the results of a filtering operation in values
                ArrayList<EmpAvaliability> FilteredArrList = new ArrayList<EmpAvaliability>();

                if (mOriginalValues == null) {
                    mOriginalValues = new ArrayList<EmpAvaliability>(mDisplayedValues1); // saves the original data in mOriginalValues
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
                        String data = mOriginalValues.get(i).empemail;
                        if (data.toLowerCase().startsWith(constraint.toString())) {


                            FilteredArrList.add(new EmpAvaliability(mOriginalValues.get(i).empemail,   mOriginalValues.get(i).id));
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
