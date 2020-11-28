package com.example.atry;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.CustomerViewHolder> {

    private ArrayList<Customer> purcList;
    public class CustomerViewHolder extends RecyclerView.ViewHolder{
        public TextView mTextView1;
        public TextView mTextView2;
        public TextView mTextView3;

        public CustomerViewHolder(@NonNull View itemView) {
            super(itemView);


            mTextView1 = itemView.findViewById(R.id.fName);
            mTextView2 = itemView.findViewById(R.id.lName);
            mTextView3 = itemView.findViewById(R.id.pNumber);
        }
    }

    public CustomerAdapter(ArrayList<Customer> purcList) {
        this.purcList = purcList;
    }

    @NonNull
    @Override
    public CustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.customer_item,parent,false);
        CustomerViewHolder pvh = new CustomerViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerViewHolder holder, int position) {

        Customer curItem = purcList.get(position);

        holder.mTextView1.setText(curItem.getFirstName());
        holder.mTextView2.setText(curItem.getLastName());
        holder.mTextView3.setText(Integer.toString(curItem.getPhone()));

    }

    @Override
    public int getItemCount() {
        return purcList.size();
    }
}
