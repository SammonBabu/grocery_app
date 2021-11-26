package com.example.grocery;

import android.content.Context;
import android.content.DialogInterface;
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

import androidx.appcompat.app.AlertDialog;

import static com.example.grocery.ReportMain.sqLiteHelper;


public class ReportAdapter extends BaseAdapter implements Filterable {
    public ArrayList<Report> mOriginalValues; // Original Values
    public ArrayList<Report> mDisplayedValues;    // Values to be displayed
    public Context context;
    public int layout;
    //  private ArrayList<Question> rstlist;
    public LinearLayout llContainer;

    private EditText etSearch;
    public String res;
    // private Object BinAdapter;
    //  private String emailFromIntent;
    //   private Activity row;
    // private SharedPreferences preferences;

    public ReportAdapter(Context context, int layout, ArrayList<Report> mProductArrayList, LinearLayout llContainer, EditText etSearch) {
        this.context = context;
        this.layout = layout;
        // this.rstlist = rstlist;
        this.mOriginalValues = mProductArrayList;
        this.mDisplayedValues = mProductArrayList;
        //   this.emailFromIntent = emailFromIntent;
        this.llContainer = llContainer;
        this.etSearch = etSearch;
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

    private class ViewHolder {
        LinearLayout llContainer;
        TextView txtvusername, txtvemail, txtvcomplaint, txtvassigndate, txtvstatus;
        // RadioButton rbd1,rbd2;
        //LinearLayout  llContainer;
    }

    @Override
    public View getView(final int position, final View view, ViewGroup viewGroup) {
        //   View view = getLayoutInflater().inflate(R.layout.row_items,null);

        View row = view;
        ReportAdapter.ViewHolder holder = new ReportAdapter.ViewHolder();

        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            //  LayoutInflater inflater = LayoutInflater.from(mCtx);
            row = inflater.inflate(layout, null);
            holder.txtvusername = (TextView) row.findViewById(R.id.txtvusername);
            holder.txtvemail = (TextView) row.findViewById(R.id.txtvemail);
            holder.txtvcomplaint = (TextView) row.findViewById(R.id.txtvcomplaint);
            holder.txtvassigndate = (TextView) row.findViewById(R.id.txtvassigndate);
            holder.txtvstatus = (TextView) row.findViewById(R.id.txtvstatus);
          // // holder.txtviewdate = (TextView) row.findViewById(R.id.txtviewdate);
         //   holder.TextViewStatus = (TextView) row.findViewById(R.id.TextViewStatus);
            // holder.txtlocation = (TextView) row.findViewById(R.id.txtlocation);

            // holder.email = (TextView) row.findViewById(R.id.txtlocation);


            holder.llContainer = (LinearLayout) row.findViewById(R.id.llContainer);
            row.setTag(holder);
        } else {
            holder = (ReportAdapter.ViewHolder) row.getTag();
        }

        final Report report = mDisplayedValues.get(position);

        holder.txtvusername.setText(report.getUsername());
        holder.txtvemail.setText(report.getEmail());
        holder.txtvcomplaint.setText(report.getComplaint());
        holder.txtvassigndate.setText(report.getCompdate());
        holder.txtvstatus.setText(report.getStatus());

        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("main activity", "item clicked");
                // startActivity(new Intent(BinAdapter.this,Binupdate.class).putExtra("items", (Parcelable) mDisplayedValues.get(position)));
                // Intent intentRegister = (new Intent( context.getApplicationContext(),BinList.class));
                //  context.startActivity(intentRegister);

                //  int id;

                updatecomplaint(report,position);
            }


        });


        return row;
    }

    private void updatecomplaint(final Report report, final int position) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(context);

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_reportbtn, null);
        builder.setView(view);

        final Button btnupdate = view.findViewById(R.id.btnupdate);

        final Button btndel = view.findViewById(R.id.btndel);


        btndel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Log.e("main activity","item clicked");

                Cursor c = sqLiteHelper.getData("SELECT id FROM complainttbl");
                ArrayList<Integer> arrID = new ArrayList<Integer>();
                while (c.moveToNext()) {
                    arrID.add(c.getInt(0));
                }
                // show dialog update at here
                showDialogDelete( arrID.get(position));
                // updateb(bin);
            }


        });



        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Log.e("main activity","item clicked");
                sqLiteHelper = new SQLiteHelper(context, "Grocery.sqlite", null, 1);

                Cursor c = sqLiteHelper.getData("SELECT id FROM complainttbl");
                ArrayList<Integer> arrID = new ArrayList<Integer>();
                while (c.moveToNext()) {
                    arrID.add(c.getInt(0));
                }
                // show dialog update at here
                showDialogcomUpdate(report, arrID.get(position));
                // updateb(bin);
            }


        });

        final AlertDialog dialog = builder.create();
        dialog.show();


    }



    private void showDialogDelete(final int idBin){
        final android.app.AlertDialog.Builder dialogDelete = new android.app.AlertDialog.Builder(context);

        dialogDelete.setTitle("Warning!!");
        dialogDelete.setMessage("Are you sure you want to this delete?");
        dialogDelete.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    sqLiteHelper.deletecomplaint(idBin);
                    Toast.makeText(context.getApplicationContext(), "Delete successfully!!!",Toast.LENGTH_SHORT).show();
                } catch (Exception e){
                    Log.e("error", e.getMessage());
                }
                updatebincre();
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
    private void showDialogcomUpdate(Report report, final int position) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_reportupdate, null);
        builder.setView(view);


        final TextView   txtupusername = view.findViewById(R.id.txtupusername);
        txtupusername.setText(String.valueOf(report.getUsername()));

        final TextView txtupemail = (TextView) view.findViewById(R.id.txtupemail);
        txtupemail.setText(report.getEmail());

        final TextView txtviewupcom = (TextView) view.findViewById(R.id.txtviewupcom);
        txtviewupcom.setText(report.getComplaint());


        final TextView txtupassigndate = (TextView) view.findViewById(R.id.txtupassigndate);
        txtupassigndate.setText(report.getCompdate());

        final TextView txtupstatus = (TextView) view.findViewById(R.id.txtupstatus);
        txtupstatus.setText(report.getStatus());

        final Spinner spinnertxtupstatus = (Spinner) view.findViewById(R.id.spinnertxtupstatus);




        final Button   bUpdate = view.findViewById(R.id.Update);
        bUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try {
                    sqLiteHelper.updatecomplaint(

                            txtupusername.getText().toString().trim(),
                            txtupemail.getText().toString().trim(),
                            txtviewupcom.getText().toString().trim(),
                            txtupassigndate.getText().toString().trim(),
                            spinnertxtupstatus.getSelectedItem().toString().trim(),
                            position

                    );
                    //  dialog.dismiss();
                    Toast.makeText(context.getApplicationContext(), "Update successfully!!!",Toast.LENGTH_SHORT).show();
                }
                catch (Exception error) {
                    Log.e("Update error", error.getMessage());
                }
                updatebincre();
            }
        });

        final AlertDialog dialog = builder.create();
        dialog.show();


    }

    private void updatebincre() {

        // get all data from sqlite
        Cursor cursor = sqLiteHelper.getData("SELECT * FROM complainttbl");
        mDisplayedValues.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String username = cursor.getString(1);
            String email = cursor.getString(2);
            String complaint = cursor.getString(3);
            String compdate = cursor.getString(4);
            String status = cursor.getString(5);
            mDisplayedValues.add(new Report(id, username,email, complaint, compdate, status));

        }
        notifyDataSetChanged();
    }




    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                mDisplayedValues = (ArrayList<Report>) results.values; // has the filtered values
                notifyDataSetChanged();  // notifies the data with new filtered values
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();        // Holds the results of a filtering operation in values
                ArrayList<Report> FilteredArrList = new ArrayList<Report>();

                if (mOriginalValues == null) {
                    mOriginalValues = new ArrayList<Report>(mDisplayedValues); // saves the original data in mOriginalValues
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
                        String data = mOriginalValues.get(i).email;





                        if (data.toLowerCase().startsWith(constraint.toString())) {

                            FilteredArrList.add(new Report(mOriginalValues.get(i).id,mOriginalValues.get(i).username ,mOriginalValues.get(i).email,mOriginalValues.get(i).complaint, mOriginalValues.get(i).compdate, mOriginalValues.get(i).status));
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







