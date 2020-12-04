package com.example.atry;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PurchaseAdapter extends RecyclerView.Adapter<PurchaseAdapter.PurchaseViewHolder> {

    private ArrayList<Shipment> shipmentList;

    public class PurchaseViewHolder extends RecyclerView.ViewHolder{
        public TextView mTextView1;
        public TextView mTextView2;
        public TextView mTextView3;

        public Button b;

        public PurchaseViewHolder(@NonNull View itemView) {
            super(itemView);

            mTextView1 = itemView.findViewById(R.id.shipment_id);
            mTextView2 = itemView.findViewById(R.id.shipment_date);
            mTextView3 = itemView.findViewById(R.id.total_price);
            b=itemView.findViewById(R.id.view_button);
        }
    }

    public PurchaseAdapter (ArrayList<Shipment> shipmentList) {
        this.shipmentList = shipmentList;
    }

    @NonNull
    @Override
    public PurchaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.purchase_item,parent,false);
        PurchaseViewHolder svh = new PurchaseViewHolder(v);
        return svh;
    }

    @Override
    public void onBindViewHolder(@NonNull PurchaseViewHolder holder, int position) {



        Shipment curItem = shipmentList.get(position);

        holder.mTextView1.setText(Integer.toString(curItem.getShipmentNo()));
        holder.mTextView2.setText(curItem.getShipmentDate());




        holder.mTextView3.setText(Double.toString(curItem.getTotalCost()));
        holder.b.setOnClickListener((v)->{
            Intent i=new Intent(v.getContext(), view_purchase.class);
            i.putExtra("shipment_id",Integer.parseInt(holder.mTextView1.getText().toString()));
            v.getContext().startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return shipmentList.size();
    }
}
