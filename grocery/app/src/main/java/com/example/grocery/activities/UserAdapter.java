package com.example.grocery.activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.grocery.R;

import java.util.ArrayList;

public class UserAdapter extends BaseAdapter {
    //   public static SQLiteHelper sqLiteHelper;

    private Context context;
    private  int layout;
    private ArrayList<User> elist;


    public UserAdapter(Context context, int layout, ArrayList<User> elist) {
        this.context = context;
        this.layout = layout;
        this.elist = elist;
    }



    @Override
    public int getCount() {
        return elist.size();
    }

    @Override
    public Object getItem(int position) {
        return elist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{

        TextView textusViewName,textViewusEmail,textusPassword;

    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View row = view;
        UserAdapter.ViewHolder holder = new UserAdapter.ViewHolder();

        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            //  LayoutInflater inflater = LayoutInflater.from(mCtx);
            row = inflater.inflate(layout, null);

            holder.textusViewName = (TextView) row.findViewById(R.id.textusViewName);
            holder.textViewusEmail = (TextView) row.findViewById(R.id.textViewusEmail);
            holder.textusPassword = (TextView) row.findViewById(R.id.textusPassword);

            // holder. txtTotalcart = (TextView) row.findViewById(R.id.txtTotalcart);
            row.setTag(holder);
        }
        else {
            holder = (UserAdapter.ViewHolder) row.getTag();
        }

        final User user = elist.get(position);

        holder.textusViewName.setText(user.getName());
        holder.textViewusEmail.setText(user.getEmail());
        holder.textusPassword.setText(user.getPassword());


        return row;


        //TextView txtTotalcart = view.findViewById(R.id.txtTotalcart)
    }
}
