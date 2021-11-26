package com.example.grocery;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class FoodAdapter extends BaseAdapter {
    //   public static SQLiteHelper sqLiteHelper;

    private Context context;
    private  int layout;
    private ArrayList<FoodCart> foodcartList;
    private int total = 0;

    public FoodAdapter(Context context, int layout, ArrayList<FoodCart> foodcartList) {
        this.context = context;
        this.layout = layout;
        this.foodcartList = foodcartList;
    }



    @Override
    public int getCount() {
        return foodcartList.size();
    }

    @Override
    public Object getItem(int position) {
        return foodcartList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{
       // ImageView imageView;
        TextView txtNameView, txtPriceview,txtQuantityview,txtPriceTotal,txtTotalcart;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View row = view;
        FoodAdapter.ViewHolder holder = new FoodAdapter.ViewHolder();

        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            //  LayoutInflater inflater = LayoutInflater.from(mCtx);
            row = inflater.inflate(layout, null);

            holder.txtNameView = (TextView) row.findViewById(R.id.txtNameView);
            holder.txtPriceview = (TextView) row.findViewById(R.id.txtPriceview);
            holder.txtQuantityview = (TextView) row.findViewById(R.id.txtQuantityview);
            holder.txtPriceTotal = (TextView) row.findViewById(R.id.txtPriceTotal);
           // holder. txtTotalcart = (TextView) row.findViewById(R.id.txtTotalcart);
            row.setTag(holder);
        }
        else {
            holder = (FoodAdapter.ViewHolder) row.getTag();
        }

        final FoodCart foodcart = foodcartList.get(position);

        holder.txtNameView.setText(foodcart.getName());
        holder.txtPriceview.setText(String.valueOf(foodcart.getPrice()));
        holder.txtQuantityview.setText(String.valueOf(foodcart.getQuantity()));
        holder.txtPriceTotal.setText(String.valueOf(foodcart.getTotalprice()));
      //  holder.txtTotalcart.setText(foodcart.getTotalprice());
       // holder.txtPrice.setText(String.valueOf(food.getPrice()));



        return row;


      //TextView txtTotalcart = view.findViewById(R.id.txtTotalcart);

    }









}
