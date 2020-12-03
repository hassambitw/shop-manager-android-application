package com.example.atry;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class View_items_Adapter extends RecyclerView.Adapter<View_items_Adapter.View_Items_Holder> {

    private ArrayList<Order_Items>  itemsList;
    public class View_Items_Holder extends RecyclerView.ViewHolder{

        public TextView item_id_tv;
        public TextView product_id_tv;
        public TextView quantity_tv;
        public TextView price_tv;
        public TextView discount_tv;
        public TextView final_price_tv;


        public View_Items_Holder(@NonNull View itemView) {
            super(itemView);
            item_id_tv = itemView.findViewById(R.id.item_id_tv);
            product_id_tv = itemView.findViewById(R.id.product_id_tv);
            quantity_tv = itemView.findViewById(R.id.quantity_tv);
            price_tv = itemView.findViewById(R.id.price_tv);
            discount_tv = itemView.findViewById(R.id.discount_tv);
            final_price_tv = itemView.findViewById(R.id.final_price_tv);
        }
    }

    public View_items_Adapter (ArrayList<Order_Items> itemsList) {
        this.itemsList = itemsList;
    }

    @NonNull
    @Override
    public View_items_Adapter.View_Items_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item,parent,false);
        View_items_Adapter.View_Items_Holder svh = new View_items_Adapter.View_Items_Holder(v);
        return svh;
    }

    @Override
    public void onBindViewHolder(@NonNull View_items_Adapter.View_Items_Holder holder, int position) {

        Order_Items curItem = itemsList.get(position);

        holder.item_id_tv.setText(Integer.toString(curItem.getItemId()));
        holder.product_id_tv.setText(Integer.toString(curItem.getProductId()));
        holder.quantity_tv.setText(Integer.toString(curItem.getQuantity()));
        holder.price_tv.setText(Double.toString(curItem.getPrice()));
        holder.discount_tv.setText(Double.toString(curItem.viewDiscount()));
        double a=curItem.getPrice()*curItem.getDiscount()*curItem.getQuantity();

        holder.final_price_tv.setText(Double.toString(round(a,2)));


    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
