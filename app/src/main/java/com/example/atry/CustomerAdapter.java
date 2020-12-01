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
        public TextView customer_id_tv;
        public TextView firstNameTV;
        public TextView lastNameTV;
        public TextView phoneTV;
        public TextView emailTV;

        public CustomerViewHolder(@NonNull View itemView) {
            super(itemView);

            customer_id_tv = itemView.findViewById(R.id.customerID);
            firstNameTV = itemView.findViewById(R.id.firstName);
            lastNameTV = itemView.findViewById(R.id.lastName);
            phoneTV = itemView.findViewById(R.id.phone);
            emailTV = itemView.findViewById(R.id.email);
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

        holder.customer_id_tv.setText(Integer.toString(curItem.getCustomerId()));
        holder.firstNameTV.setText(curItem.getFirstName());
        holder.lastNameTV.setText(curItem.getLastName());
        holder.phoneTV.setText(Integer.toString(curItem.getPhone()));
        holder.emailTV.setText(curItem.getEmail());

    }

    @Override
    public int getItemCount() {
        return purcList.size();
    }
}
