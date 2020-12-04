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

public class view_purchase_Adapter extends RecyclerView.Adapter<view_purchase_Adapter.view_purchase_Holder> {

    private ArrayList<Shipment_items> shipment_items_list;

    public class view_purchase_Holder extends RecyclerView.ViewHolder{

        public TextView item_id_tv;
        public TextView product_name_tv;
        public TextView quantity_tv;
        public TextView price_tv;
        public TextView final_price_tv;


        public view_purchase_Holder(@NonNull View itemView) {
            super(itemView);
            item_id_tv = itemView.findViewById(R.id.item_id_tv);
            product_name_tv = itemView.findViewById(R.id.product_name_tv);
            quantity_tv = itemView.findViewById(R.id.quantity_tv);
            price_tv = itemView.findViewById(R.id.price_tv);
            final_price_tv = itemView.findViewById(R.id.final_price_tv);
        }
    }

    public view_purchase_Adapter (ArrayList<Shipment_items> itemsList) {
        this.shipment_items_list = itemsList;
    }

    @NonNull
    @Override
    public view_purchase_Adapter.view_purchase_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_purchase,parent,false);
        view_purchase_Adapter.view_purchase_Holder svh = new view_purchase_Adapter.view_purchase_Holder(v);
        return svh;
    }

    @Override
    public void onBindViewHolder(@NonNull view_purchase_Adapter.view_purchase_Holder holder, int position) {

        Shipment_items curItem = shipment_items_list.get(position);

        holder.item_id_tv.setText(Integer.toString(curItem.getItemId()));
        holder.product_name_tv.setText(curItem.getProduct_name());
        holder.quantity_tv.setText(Integer.toString(curItem.getShipmentQuantity()));
        holder.price_tv.setText(Double.toString(curItem.getProduct_cost()));

        double a=curItem.getProduct_cost()*curItem.getShipmentQuantity();

        holder.final_price_tv.setText(Double.toString(round(a,2)));


    }

    @Override
    public int getItemCount() {
        return shipment_items_list.size();
    }
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
