package com.example.atry;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class shipmentAdapter extends RecyclerView.Adapter<shipmentAdapter.shipmentViewHolder> {

    private ArrayList<ShipmentDetails> shipmentList;
    //private ArrayList<Orders> _transactionList;

    public class shipmentViewHolder extends RecyclerView.ViewHolder{
        public TextView shipment_date;
        public TextView total_price_tv;
        public TextView shipment_id_tv;
        public CardView item_shipment;


        public shipmentViewHolder(@NonNull View itemView) {
            super(itemView);

            shipment_date = itemView.findViewById(R.id.date);
            total_price_tv = itemView.findViewById(R.id.totalPrice);
            shipment_id_tv =itemView.findViewById(R.id.shipmentid);
            item_shipment = itemView.findViewById(R.id.shipment_item);

        }
    }

    public shipmentAdapter (ArrayList<ShipmentDetails> shipmentList) {
        this.shipmentList = shipmentList;
    }

    @NonNull
    @Override
    public shipmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.shipment_item,parent,false);
        shipmentViewHolder svh = new shipmentViewHolder(v);
        return svh;
    }

    @Override
    public void onBindViewHolder(@NonNull shipmentViewHolder holder, int position) {

//        Orders curItem = orders_transactionList.get(position);
//        Customer curItem_cust = customers_transactionList.get(position);
 //       TransactionDetails curTransaction = shipmentList.get(position);

        ShipmentDetails curShipment = shipmentList.get(position);

        holder.shipment_date.setText(curShipment.getShipmentDate());
        holder.total_price_tv.setText(Double.toString(round(curShipment.getPrice(), 2)));
        holder.shipment_id_tv.setText(Integer.toString(curShipment.getShipmentID()));

        holder.item_shipment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), view_purchase.class);
                i.putExtra("shipment_id", Integer.parseInt(holder.shipment_id_tv.getText().toString()));
                v.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return shipmentList.size();
    }

    public static double round (double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
