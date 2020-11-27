package com.example.atry;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Sales_Adapter extends RecyclerView.Adapter<Sales_Adapter.SalesViewHolder> {

    private ArrayList<Orders> salesList;
    public class SalesViewHolder extends RecyclerView.ViewHolder{
        public TextView mTextView1;
        public TextView mTextView2;
        public TextView mTextView3;
        public Button b;

        public SalesViewHolder(@NonNull View itemView) {
            super(itemView);


            mTextView1 = itemView.findViewById(R.id.category);
            mTextView2 = itemView.findViewById(R.id.brandName);
            mTextView3 = itemView.findViewById(R.id.productName);
            b=itemView.findViewById(R.id.view_button);
        }
    }

    public Sales_Adapter (ArrayList<Orders> salesList) {
        this.salesList = salesList;
    }

    @NonNull
    @Override
    public SalesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sales_item,parent,false);
        SalesViewHolder svh = new SalesViewHolder(v);
        return svh;
    }

    @Override
    public void onBindViewHolder(@NonNull SalesViewHolder holder, int position) {

        Orders curItem = salesList.get(position);

        holder.mTextView1.setText(Integer.toString(curItem.getOrderId()));
        holder.mTextView2.setText(Integer.toString(curItem.getCustomerId()));
        holder.mTextView3.setText(curItem.getOrderDate());


    }

    @Override
    public int getItemCount() {
        return salesList.size();
    }
}
