package com.example.atry;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PurchaseAdapter extends RecyclerView.Adapter<PurchaseAdapter.PurchaseViewHolder> {

    private ArrayList<Shipment> purcList;
    public class PurchaseViewHolder extends RecyclerView.ViewHolder{
        public TextView mTextView1;
        public TextView mTextView2;
        public TextView mTextView3;

        public PurchaseViewHolder(@NonNull View itemView) {
            super(itemView);


            mTextView1 = itemView.findViewById(R.id.ship_no);
            mTextView2 = itemView.findViewById(R.id.ship_cost);
            mTextView3 = itemView.findViewById(R.id.ship_q);
        }
    }

    public PurchaseAdapter(ArrayList<Shipment> purcList) {
        this.purcList = purcList;
    }

    @NonNull
    @Override
    public PurchaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.purchase_item,parent,false);
        PurchaseViewHolder pvh = new PurchaseViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull PurchaseViewHolder holder, int position) {

        Shipment curItem = purcList.get(position);

        holder.mTextView1.setText(Integer.toString(curItem.getShipmentNo()));
        holder.mTextView2.setText(Integer.toString(curItem.getShipmentCost()));
        holder.mTextView3.setText(Integer.toString(curItem.getShipmentQuantity()));

    }

    @Override
    public int getItemCount() {
        return purcList.size();
    }
}
